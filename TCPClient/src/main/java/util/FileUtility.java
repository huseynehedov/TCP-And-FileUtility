package util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtility {
    public static void writeIntoFile(String fileName, String text, boolean append) throws Exception{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, append));){
            bw.write(text);
        }
    }

    public static void writeIntoFile(String fileName, String text) throws Exception{
        writeIntoFile(fileName, text, false);
    }

    public static void appendIntoFile(String fileName, String text) throws Exception{
        writeIntoFile(fileName, text, true);
    }

    public static void writeBytes(String fileName, byte[] data) throws Exception{
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(data);
        fos.flush();
        fos.close();
        System.out.println("Done.");
    }

    public static String read(String fileName) throws Exception{
        try (InputStream is = new FileInputStream(fileName);
             InputStreamReader r = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(r);){
            String line = null;
            String result = "";
            while ( (line = br.readLine()) != null){
                result += line + "\n";
            }

            return result;
        }
    }

    public static byte[] readBytes(String fileName) throws Exception{
        File file = new File(fileName);
        try (FileInputStream fis = new FileInputStream(file);){
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);

            return bytes;
        }
    }

    public static Object readFileDeserialize(String name) throws Exception{
        Object ob = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(name))){
            ob = in.readObject();
        }finally {
            return ob;
        }
    }

    public static void writeObjectToFile(Object object, String name) throws Exception{
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(name))){
            outputStream.writeObject(object);
        }
    }

    public static void writeBytes(byte[] data, String fileName) throws Exception{
        Path filePath = Paths.get(fileName);
        Files.write(filePath, data);
    }

    public static byte[] readBytesNio(String fileName) throws Exception{
        Path filePath = Paths.get(fileName);
        byte[] bytes = Files.readAllBytes(filePath);

        return bytes;
    }

    private static void download(String fromFile, String toFile) throws Exception{
        URL website = new URL(fromFile);
        URL url = new URL(fromFile);
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        InputStream in = connection.getInputStream();

        ReadableByteChannel rbc = Channels.newChannel(in);
        FileOutputStream fos = new FileOutputStream(toFile);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
}
