package com.industrial.common.vo;

import com.industrial.domin.AppOrder;
import com.industrial.domin.AppProduct;
import lombok.Data;

import java.util.List;

/**
 * @author zhu
 * @date 2022年01月19日 10:58
 */
@Data
public class OrderVo extends AppOrder {
    /**
     * 商品ID集合
     */
    List<Integer> productIdList;
    /**
     * 通知人员ID集合
     */
    List<Integer> sysUserIdList;

}
