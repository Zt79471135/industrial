package com.industrial.common.dto;

import com.industrial.domin.AppActivityFile;
import com.industrial.domin.AppActivityUser;
import com.industrial.domin.AppImageFile;
import lombok.Data;

import java.util.List;

/**
 * 描述:
 * 日期：2022-02-10
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */
@Data
public class ActivityFileDto extends AppActivityFile {
    private List<AppImageFile> imageFileList;
}
