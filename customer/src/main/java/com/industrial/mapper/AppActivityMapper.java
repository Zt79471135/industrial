package com.industrial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industrial.domin.AppActivity;
import java.util.List;

/**活动Mapper接口
 * @author zhu
 * @date 2022年01月14日 16:47
 */
public interface AppActivityMapper extends BaseMapper<AppActivity> {

        /**
         * 查询活动
         *
         * @param id 活动主键
         * @return 活动
         */
        public AppActivity selectAppActivityById(Long id);

        /**
         * 查询活动列表
         *
         * @param appActivity 活动
         * @return 活动集合
         */
        public List<AppActivity> selectAppActivityList(AppActivity appActivity);

        /**
         * 新增活动
         *
         * @param appActivity 活动
         * @return 结果
         */
        public int insertAppActivity(AppActivity appActivity);

        /**
         * 修改活动
         *
         * @param appActivity 活动
         * @return 结果
         */
        public int updateAppActivity(AppActivity appActivity);

        /**
         * 删除活动
         *
         * @param id 活动主键
         * @return 结果
         */
        public int deleteAppActivityById(Long id);

        /**
         * 批量删除活动
         *
         * @param ids 需要删除的数据主键集合
         * @return 结果
         */
        public int deleteAppActivityByIds(Long[] ids);

}