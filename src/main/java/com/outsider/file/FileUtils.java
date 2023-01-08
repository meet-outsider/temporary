package com.outsider.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Map;

/**
 * 文件操作工具类
 */
public class FileUtils {


    private FileUtils() {
    }

    /**
     * 通过buffer保存文件
     *
     * @param src
     * @param filename
     */
    public void saveFileByBuffer(ByteBuffer src, String filename) {
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
     * 将base64字符串解码，然后保存到指定路径名
     *
     * @param base64
     * @param pathname
     * @return
     */
    public static Map<Boolean, String> saveImageBinaryArray(String base64, String pathname,
        boolean isCover) {
        if (!isCover && Files.exists(Path.of(pathname), LinkOption.NOFOLLOW_LINKS)) {
            return Map.of(Boolean.FALSE, "文件已存在");
        }
        byte[] bytes = decodeBase64ToStr(base64);
        InputStream in = new ByteArrayInputStream(bytes);
        if (pathname.isEmpty()) {
            pathname = "/Users/outsider/Downloads/camera.jpg";
        }
        File file = new File(pathname);// 可以是任何图片格式.jpg,.png等
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = in.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
        } catch (IOException e) {
            return Map.of(Boolean.FALSE, "io error");
        } finally {
            try {
                fos.flush();
                fos.close();
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return Map.of(Boolean.TRUE, "success");
    }

    /**
     * 图片转base64编码
     *
     * @param path
     * @return
     */
    public static Map<Boolean, String> imageConvertBase64(String pathname) {
        if (Files.notExists(Path.of(pathname), LinkOption.NOFOLLOW_LINKS)) {
            return Map.of(Boolean.FALSE, "文件不存在!");
        }
        // 图片转化为二进制
        byte[] imageBytes = null;
        try (FileInputStream fileInputStream = new FileInputStream(pathname)) {
            imageBytes = new byte[fileInputStream.available()];
            fileInputStream.read(imageBytes);
        } catch (IOException e) {
            return Map.of(Boolean.FALSE, e.getMessage());
        }
        return Map.of(Boolean.TRUE, encodeByteToBase64(imageBytes));
    }

    /**
     * nio 图片转base64编码字符
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static Map<Boolean, String> imageConvertBase64ByNio(String pathname) throws IOException {
        if (Files.notExists(Path.of(pathname), LinkOption.NOFOLLOW_LINKS)) {
            return Map.of(Boolean.FALSE, "文件不存在!");
        }
        RandomAccessFile accessFile = new RandomAccessFile(pathname, "rw");
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
        return Map.of(Boolean.TRUE, encodeByteToBase64(bytes));
    }

    /**
     * 读取文本文件
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String readTextFile(String path) throws IOException {
        RandomAccessFile file = new RandomAccessFile(path, "r");
        String str;
        StringBuilder builder = new StringBuilder();
        while ((str = file.readLine()) != null) {
            builder.append(str);
        }
        file.close();
        return builder.toString();

    }

    /**
     * 二进制转Base64
     */
    public static String encodeByteToBase64(byte[] b) {
        String base64 = Base64.getEncoder().encodeToString(b);
        return base64;
    }

    /**
     * Base64转二进制
     */
    public static byte[] decodeBase64ToStr(String str) {
        return Base64.getDecoder().decode(str);
    }


}
