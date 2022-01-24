package com.industrial.service;

import com.industrial.domin.AppImageFile;

/**
 * @author zhu
 * @date 2022年01月17日 17:38
 */
public interface AppImageFileService {
    /**
     * 图片信息保存
     * @param imageFile
     * @return
     */
    Integer insert(AppImageFile imageFile);

}
