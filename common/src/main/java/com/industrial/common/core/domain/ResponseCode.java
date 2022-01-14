package com.industrial.common.core.domain;

import lombok.Getter;

/**
 * @author zhu
 * @date 2021年12月22日 10:28
 */
@Getter
public enum ResponseCode {
    /**
     * 返回成功
     */
    SUCCESS(200,"success"),
    /**
     * 服务层返回失败
     */
    SERVER_ERROR(40400,"Service is busy"),
    PERMISSION_ERROR(40401,"insufficient privileges"),
    /**
     * 返回失败
     */
    ERROR(404,"error");
    /**
     * status:返回码
     * msg:返回文字
     */
    private int status;
    private String msg;

    ResponseCode(int status, String msg) {
        this.status = status;

        this.msg = msg;
    }
}
