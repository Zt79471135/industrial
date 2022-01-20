package com.industrial.common.dto;


import com.industrial.domin.AppProduct;
import com.industrial.domin.AppProductCategory;
import lombok.Data;

/**
 * @author zhu
 * @date 2022年01月14日 9:57
 */
@Data
public class ProductDto extends AppProduct {
    private AppProductCategory category;
}
