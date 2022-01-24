package com.industrial.service.impl;

import com.industrial.domin.AppImageFile;
import com.industrial.mapper.AppImageFileMapper;
import com.industrial.service.AppImageFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhu
 * @date 2022年01月17日 17:39
 */
@Service
public class AppImageFileServiceImpl implements AppImageFileService {
    @Resource
    private AppImageFileMapper imageFileMapper;

    @Override
    public Integer insert(AppImageFile imageFile) {
        imageFileMapper.insert(imageFile);
        return imageFile.getId();
    }
}
