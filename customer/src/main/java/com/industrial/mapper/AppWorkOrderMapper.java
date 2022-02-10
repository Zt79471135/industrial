package com.industrial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industrial.domin.AppWorkOrder;

import java.util.List;

/**
 * @author zhu
 * @date 2022年02月10日 10:04
 */
public interface AppWorkOrderMapper extends BaseMapper<AppWorkOrder> {
    List<AppWorkOrder> selectAppWorkOrderList(AppWorkOrder appWorkOrder);

    int deleteAppWorkOrderByIds(Long[] ids);

    int turnWorkOrder(AppWorkOrder appWorkOrder);
}