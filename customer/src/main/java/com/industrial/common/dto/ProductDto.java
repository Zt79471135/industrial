package com.industrial.common.dto;


import com.industrial.domin.AppImageFile;
import com.industrial.domin.AppProduct;
import com.industrial.domin.AppCategory;
import lombok.Data;

import java.util.List;

/**
 * @author zhu
 * @date 2022年01月14日 9:57
 */
@Data
public class ProductDto extends AppProduct {
    private AppCategory category;
    private String unitName;
    private List<AppImageFile> imgUrls;
}
