package com.industrial.common.dto;


import com.industrial.entity.AppProduct;
import com.industrial.entity.AppProductCategory;
import lombok.Data;

/**
 * @author zhu
 * @date 2022年01月14日 9:57
 */
@Data
public class ProductDto extends AppProduct {
    private AppProductCategory category;
}
