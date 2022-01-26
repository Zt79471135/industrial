package com.industrial.common.vo;

import lombok.Data;

/**
 * @author zhu
 * @date 2022年01月14日 15:05
 */
@Data
public class CheckVo {
    /**
     * 审核人
     * 审核结果
     * 审核的物品ID
     */
    private Integer checkId;
    private Integer uid;
    private Boolean check;
    private Integer type;
    private Integer id;
}
