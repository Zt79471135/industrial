package com.industrial.common.vo;

import com.industrial.domin.AppActivityFile;
import lombok.Data;

import java.util.List;
/**
 * 描述:
 * 日期：2022-02-09
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */
@Data
public class ActivityFileVo extends AppActivityFile {
    /**
     * 图片外键（关联app_image_file表ID）
     */
    public Integer[] FileIds;
}
