package cn.net.sybt.springboot.utils;

import cn.net.sybt.springboot.consts.Parameters;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static Boolean uploadFile(MultipartFile file, String path, String uuid) {
        if (file.isEmpty()) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        String filePath = new File("").getAbsolutePath() + "/public/" + path + uuid;
        System.out.println("上传类：" + filePath + fileName);
        File dest = new File(filePath + fileName);
        try {
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            if (!dest.exists()) {
                dest.createNewFile();
            }
            file.transferTo(dest);
            System.out.println("上传成功");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
