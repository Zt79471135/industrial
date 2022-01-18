package com.industrial.common.dto;

import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.entity.AppProductCategory;
import com.industrial.common.core.domain.entity.SysDictData;
import lombok.Data;
import java.util.List;

/**
 * 描述:
 * 日期：2022-01-18
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */
public class CategoryDto extends AppProductCategory {
    private SysDictData dictLabel;
}
