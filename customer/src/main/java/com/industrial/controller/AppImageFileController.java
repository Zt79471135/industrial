package com.industrial.controller;

import com.industrial.service.AppImageFileService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhu
 * @date 2022年01月17日 17:41
 */
@RestController
public class AppImageFileController {
    @Resource
    private AppImageFileService imageFileService;

    /**
     * 商品管理  上传的图片，生成的文件格式是“goods+日期（精确到秒）+或随机数”，
     * 活动管理增加的文件格式：activity+日期（精确到秒）+或随机数”
     * @param img
     * @param fileLabel
     * @return
     */
    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile img,String fileLabel){
        FileOutputStream fos = null;
        // 文件名
        String fileName = img.getOriginalFilename();
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 4位随机数
        long round = Math.round((Math.random() + 1) * 1000);
        //日期(精确到秒)
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        //新文件名
        fileName = fileLabel+format+round + suffixName;
        // 项目路径
        String comPath = this.getClass().getResource("/").getPath()+"images"+ "/";
        //文件路径加文件名
        String filePath = comPath + fileName;
        //创建文件
        File file = new File(filePath);
        // 获取父文件
        File parent = file.getParentFile();
        // 若不存在创建父文件夹
        if (!parent.exists()) {
           parent.mkdirs();
          }
        try {
            fos = new FileOutputStream(file);
            InputStream is = img.getInputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }
}
