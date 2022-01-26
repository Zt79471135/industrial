package com.industrial.service;

import com.industrial.common.vo.CheckVo;

/**
 * @author zhu
 * @date 2022年01月26日 9:25
 */
public interface AppCheckService {
    /**
     * 审核
     * @param checkVo
     * @return
     */
    boolean updateStatus(CheckVo checkVo);

}
