package com.outsider;

import com.outsider.file.FileUtils;
import java.io.IOException;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

@SpringBootTest
public class NioTest {

    private static final String GERALt_PATH = "/Users/outsider/Downloads/geralt.jpg";

    @Test
    void fileTest() throws IOException {
        String path = ResourceUtils.getFile("classpath:base64.txt").getPath();
        System.out.println("bas464Txt path is  :" + path);
        // 读取文本文件
        String cameraBase64Str = FileUtils.readTextFile(path);
        // System.out.println("cameraBase64Str: "+cameraBase64Str);
        // 图片转base64
        Map<Boolean, String> img2StrRes1 = FileUtils.imageConvertBase64(GERALt_PATH);
        Map<Boolean, String> img2StrRes2 = FileUtils.imageConvertBase64ByNio(GERALt_PATH);
        System.out.println("img2StrRes1 error message is :" + img2StrRes1.get(false));
        System.out.println("img2StrRes2 error message is :" + img2StrRes2.get(false));
        // String str1 = img2StrRes1.get(true);
        // String str2 = img2StrRes2.get(true);
        // System.out.println(str1 + "\n" + str2);
        // 保存图片
        String pathname = "/Users/outsider/Downloads/plateNo_identify.jpg";
        Map<Boolean, String> res = FileUtils.saveImageBinaryArray(cameraBase64Str, pathname, true);
        System.out.println("failed ? :"+res.get(false));
        System.out.println("success ?:"+res.get(true));
    }
}
