package com.outsider.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Base64;

public class FileUtils {

    static String RAIN_PATH = "/Users/outsider/Downloads/Rainbow_on_Mountain_HD_Image.jpg";
    static String A_PATH = "./src/main/resources/a.txt";
    static Integer count = 1;

    public FileUtils() throws UnsupportedEncodingException {
    }

    /**
     * 通过buffer保存文件
     * @param src
     * @param filename
     */
    public void saveFileByBuffer(ByteBuffer src, String filename){
        FileChannel foChannel;
        try {
            foChannel = new FileOutputStream(filename).getChannel();
            foChannel.write(src);
            foChannel.close();
            foChannel = null;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 通过二进制流保存图片
     * @param bytes
     * @return
     */
    public static boolean saveImageBinaryArray(byte[] bytes) {
        InputStream in = new ByteArrayInputStream(bytes);

        File file = new File("/Users/outsider/Downloads", "save.jpg");// 可以是任何图片格式.jpg,.png等
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = in.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
        }  catch (IOException e) {
            // throw new RuntimeException(e);
            return false;
        } finally {
            try {
                fos.flush();
                fos.close();
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return true;
    }

    public static byte[] oldImageHandler(String imgaddress) {
        System.out.println("imagePath is :" + imgaddress);
        // 图片转化为二进制
        byte[] imageBytes = null;
        try (FileInputStream fileInputStream = new FileInputStream(new File(imgaddress));) {
            imageBytes = new byte[fileInputStream.available()];
            fileInputStream.read(imageBytes);
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
        String str = encodeByteToBase64(imageBytes);
        System.out.println("io hashCode: " + str.hashCode());

        return imageBytes;
    }

    public static byte[] NioImageHandler() throws IOException {
        System.out.println("imagePath is :" + RAIN_PATH);
        RandomAccessFile accessFile = new RandomAccessFile(RAIN_PATH, "rw");
        FileChannel fileChannel = accessFile.getChannel();
        // 创建 buffer
        int size = (int) fileChannel.size();
        byte[] bytes = new byte[size];
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            bytes = byteBuffer.array();
            byteBuffer.clear();
        }
        fileChannel.close();
        String str = encodeByteToBase64(bytes);
        System.out.println("nio hashCode: " + str.hashCode());

        return bytes;
    }

    /**
     * 二进制转Base64
     */
    public static String encodeByteToBase64(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * Base64转二进制
     */
    public static byte[] decodeBase64ToStr(String str) {
        return Base64.getDecoder().decode(str);
    }


    public static void main(String[] args) throws IOException {

        Long start1 = System.currentTimeMillis();
        byte[] byte1 = oldImageHandler(RAIN_PATH);
        Long end1 = System.currentTimeMillis();
        System.out.println("io 耗时:" + (end1 - start1));

        Long start = System.currentTimeMillis();
        byte[] byte2 = NioImageHandler();
        Long end = System.currentTimeMillis();
        System.out.println("nio 耗时:" + (end - start));

        System.out.println(saveImageBinaryArray(byte2));
    }


}
