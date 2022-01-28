package com.industrial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industrial.domin.AppCheckUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhu
 * @date 2022年01月26日 10:45
 */
public interface AppCheckUserMapper extends BaseMapper<AppCheckUser> {
    Integer deleteByMainId(Long id);

    Integer insertAppCheckUser(@Param("temp") AppCheckUser temp);
}