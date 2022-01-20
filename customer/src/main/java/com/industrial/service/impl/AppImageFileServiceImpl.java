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
    public boolean insert(AppImageFile imageFile) {
        return imageFileMapper.insert(imageFile) == 1;
    }
}
