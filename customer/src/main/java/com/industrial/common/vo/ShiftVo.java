package com.industrial.common.vo;

import lombok.Data;

/**
 * @author zhu
 * @date 2022年01月26日 14:14
 */
@Data
public class ShiftVo {
    /**
     * objId 将要转交的对象的id
     * uid 目标的人的id
     * uname 目标的人的名字
     * fromId 转交的人的id
     * fromId 转交的人的名字
     * msg 备注信息
     */
    Integer objId;
    Integer uid;
    String uname;
    Integer fromId;
    String fromName;
    String msg;
}
