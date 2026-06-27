package org.lollipop.controller;

import org.lollipop.exception.BusinessException;
import org.lollipop.exception.ExceptionCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileUploadController extends BaseController {

    // 文件存储路径（服务器目录）
    private static final String UPLOAD_DIR = "/data/files/";

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("keyword") String keyword) {
        if (!keyword.equals("cwhcwh")) {
            throw new BusinessException(ExceptionCode.用户未登录);
        }

        // 判断文件是否为空
        if (file.isEmpty()) {
            return "上传失败，文件为空";
        }

        try {

            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();

            // 获取文件后缀
            String suffix = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            // 生成新文件名（防止重复）
            String fileName = UUID.randomUUID().toString() + suffix;

            // 创建目录
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 保存文件
            File dest = new File(UPLOAD_DIR + fileName);
            file.transferTo(dest);

            return "上传成功，文件路径：" + dest.getAbsolutePath();

        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败：" + e.getMessage();
        }
    }
}