package com.industrial.common.vo;
import lombok.Data;
import com.industrial.domin.AppCategory;
/**
 * 描述:
 * 日期：2022-01-20
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */
@Data
public class updateTypeVo {
    /**
     * 所属分类
     * ID数组
     *
     */
    private Integer productType;
    private Long[] ids;
}

