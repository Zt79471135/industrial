package com.industrial.common.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhu
 * @date 2021年12月22日 10:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T>{
    /**
     * 返回的数据
     */
    private T data;
    private ResponseCode responseCode;

    /**
     *返回
     * @param date
     * @param responseCode
     * @return ResultReturn
     */
    public static<T> ResponseResult<T> result(T date, ResponseCode responseCode) {
        return new ResponseResultBuilder<T>().responseCode(responseCode).data(date).build();
    }

    /**
     * 成功返回数据
     * @param date
     * @param <T>
     * @return
     */
    public static<T> ResponseResult<T> success(T date) {
        return result(date,ResponseCode.SUCCESS);
    }
    /**
     * 成功无返回数据
     * @return
     */
    public static<T> ResponseResult<T> success() {
        return result(null,ResponseCode.SUCCESS);
    }
    /**
     * 带数据的失败返回
     * @param date
     * @param responseCode
     * @param <T>
     * @return
     */
    public static<T> ResponseResult<T> error(T date,ResponseCode responseCode) {
        return result(date,ResponseCode.ERROR);
    }
    public static<T> ResponseResult<T> error() {
        return error(ResponseCode.ERROR);
    }
    /**
     * 不带数据的参数返回
     * @param responseCode
     * @param <T>
     * @return
     */
    public static<T> ResponseResult<T> error(ResponseCode responseCode) {
        return result(null,ResponseCode.ERROR);
    }
}
