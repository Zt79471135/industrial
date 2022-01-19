package com.industrial.controller;

import com.industrial.common.config.IndustrialConfig;
import com.industrial.common.core.domain.AjaxResult;
import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.utils.file.FileUploadUtils;
import com.industrial.common.utils.file.MimeTypeUtils;
import com.industrial.entity.AppImageFile;
import com.industrial.framework.config.ServerConfig;
import com.industrial.service.AppImageFileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


/**
 * @author zhu
 * @date 2022年01月17日 17:41
 */
@RestController
@RequestMapping("/upload")
public class AppImageFileController {
    @Resource
    private AppImageFileService imageFileService;
    @Resource
    private ServerConfig serverConfig;

    /**
     * 商品管理  上传的图片，生成的文件格式是“goods+日期（精确到秒）+或随机数”，
     * 活动管理增加的文件格式：activity+日期（精确到秒）+或随机数”
     * @param img
     * @param fileLabel
     * @return
     */
    @PostMapping(value = "/imgUpload")
    public AjaxResult fileUpload(@RequestParam(value = "img") MultipartFile img,String fileLabel){
        try
        {
            // 上传图片路径
            String filePath = IndustrialConfig.getUploadPath();
            // 上传并返回新图片名称
            String imgName = FileUploadUtils.upload(filePath, img, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION,fileLabel);
            //获取图片完整的请求路径
            String url = serverConfig.getUrl() + imgName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgName", imgName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }

    }
    @PostMapping("/save")
    public ResponseResult save(@RequestBody AppImageFile imageFile){
        ResponseResult result = null;
        if (imageFileService.insert(imageFile)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

}
