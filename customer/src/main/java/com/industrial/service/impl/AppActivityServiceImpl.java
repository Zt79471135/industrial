package com.industrial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.common.dto.ActivityDto;
import com.industrial.common.dto.ProductDto;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.vo.ActivityVo;
import com.industrial.common.vo.ActivityFileVo;
import com.industrial.controller.AppProductController;
import com.industrial.domin.*;
import com.industrial.mapper.*;
import com.industrial.mapper.AppProductFileMapper;
import com.industrial.mapper.DictDataMapper;
import com.industrial.service.AppActivityService;
import com.industrial.system.mapper.SysUserMapper;
import com.industrial.system.service.ISysUserService;
import com.industrial.service.IAppActivityUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhu
 * @date 2022年01月14日 11:50
 */
@Service
public class AppActivityServiceImpl implements AppActivityService {
    @Resource
    private AppActivityMapper activityMapper;
    @Resource
    private AppActivityUserMapper activityUserMapper;
    @Resource
    private AppActivityLogMapper activityLogMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IAppActivityUserService appActivityUserService;
    @Resource
    private DictDataMapper dictDataMapper;

    @Resource
    private AppActivityFileMapper activityFileMapper;

    /**
     * 活动类型
     */
    public static final String dict_activity_type = "dict_activity_type";
    /**
     * 活动状态
     */
    public static final String dict_activity_status = "dict_activity_status";

    /**
     * 查询活动列表
     *
     * @param appActivity 活动
     * @return 活动
     */
    @Override
    public List<ActivityDto> selectAppActivityList(AppActivity appActivity) {
        //return activityMapper.selectAppActivityList(appActivity);
        Integer activityType = 0;
        Integer activityStatus = 0;
        Integer headUser = 0;
        Date beginTime =appActivity.getBeginTime();
        Date endTime =appActivity.getEndTime();
        java.sql.Date begin;
        java.sql.Date end;

        activityType = appActivity.getActivityType();
        if (activityType == null) activityType = 0;
        activityStatus = appActivity.getActivityStatus();
        if (activityStatus == null) activityStatus = 0;
        headUser = appActivity.getHeadUser();
        if (headUser == null) headUser = 0;



        QueryWrapper<com.industrial.domin.AppActivity> qw = new QueryWrapper<>();

        //存在的记录
        qw.lambda().eq(com.industrial.domin.AppActivity::getDeleted, 0);
        //活动类型
        if (activityType != 0) {
            qw.lambda().eq(com.industrial.domin.AppActivity::getActivityType, activityType);
        }
        //活动状态
        if (activityStatus != 0) {
            qw.lambda().eq(com.industrial.domin.AppActivity::getActivityStatus, activityStatus);
        }
        //负责人员
        if (headUser != 0) {
            qw.lambda().eq(com.industrial.domin.AppActivity::getHeadUser, headUser);
        }
        //发布时间
        if (beginTime != null) {
            begin = new java.sql.Date(beginTime.getTime());
            qw.lambda().last("AND date_format(create_time,'%y%m%d') &lt;= date_format("+begin+",'%y%m%d')");
        }
        if (endTime != null) {
            begin = new java.sql.Date(endTime.getTime());
            qw.lambda().last("AND date_format(create_time,'%y%m%d') &gt;= date_format("+endTime+",'%y%m%d')");
        }
//            if (!"".equals(productName)) {
//                qw.lambda().like(com.industrial.domin.AppProduct::getName, productName);
//            }

        //qw.lambda().eq(AppProduct::getStatus, status);
        List<com.industrial.domin.AppActivity> activityList = activityMapper.selectList(qw);
        if (activityList.size() == 0) {

            List<ActivityDto> ActivityDtoList = activityList.stream().map(this::apply).collect(Collectors.toList());
            return ActivityDtoList;
        } else {
            List<ActivityDto> ActivityDtoList = activityList.stream().map(this::apply).collect(Collectors.toList());
            return ActivityDtoList;
        }
    }

    private ActivityDto apply(AppActivity activity) {
        ActivityDto activityDto = new ActivityDto();
        QueryWrapper<DictData> queryWrapper = null;
        QueryWrapper<DictData> queryWrapper_status = null;
        /**
         *查询负责人
         */
        SysUser user = sysUserMapper.selectUserById(Long.valueOf(activity.getHeadUser()));
        /**
         * 查询活动类型
         */
        queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DictData::getDictType, dict_activity_type);
        queryWrapper.lambda().eq(DictData::getDictValue, activity.getActivityType());
        DictData dictData = dictDataMapper.selectOne(queryWrapper);
        String dictLabel = dictData.getDictLabel();
        activityDto.setActTypeName(dictLabel);

        /**
         * 查询活动状态
         */
        queryWrapper_status = new QueryWrapper<>();
        queryWrapper_status.lambda().eq(DictData::getDictType, dict_activity_status);
        queryWrapper_status.lambda().eq(DictData::getDictValue, activity.getActivityType());
        DictData dictData_status = dictDataMapper.selectOne(queryWrapper_status);
        String dictLabel_status = dictData_status.getDictLabel();
        activityDto.setActStatuName(dictLabel_status);

        //负责人名称
        activityDto.setHeadUserName(user.getNickName());
        activityDto.setUser(user);
        BeanUtils.copyProperties(activity, activityDto);
        return activityDto;
    }

    /**
     * 查询活动详情
     *
     * @param activityId 活动主键
     * @return 活动
     */
    @Override
    public ActivityDto selectActivityById(Integer activityId) {
        //活动信息
        AppActivity appActivity = activityMapper.selectById(activityId);
        if (appActivity != null) {
            //查询负责人
            SysUser user = sysUserMapper.selectUserById(Long.valueOf(appActivity.getHeadUser()));

            //活动用户列表
            QueryWrapper<AppActivityUser> qw = new QueryWrapper<>();
            qw.lambda().eq(AppActivityUser::getActivityId, activityId);
            List<AppActivityUser> activityUserList = activityUserMapper.selectList(qw);
            //活动日志
            QueryWrapper<AppActivityLog> qw_AppActivityLog = new QueryWrapper<>();
            qw_AppActivityLog.lambda().eq(AppActivityLog::getActivityId, activityId);
            List<AppActivityLog> activityLogList = activityLogMapper.selectList(qw_AppActivityLog);

            ActivityDto activityDto = new ActivityDto();
            activityDto.setActivityUserList(activityUserList);//活动用户列表
            activityDto.setActivityLogList(activityLogList); //活动日志
            //负责人名称
            activityDto.setHeadUserName(user.getNickName());

            BeanUtils.copyProperties(appActivity, activityDto);


            return activityDto;
        } else {
            throw new ServiceException("活动详情为空");
        }
    }
    //添加活动信息和参与用户
    @Override
    public boolean insertActivity(ActivityVo activityVo) {
        AppActivity activity = new AppActivity();
        List<Integer> integerList = new ArrayList<>();
        BeanUtils.copyProperties(activityVo, activity);
        int ret = activityMapper.insert(activity);  //添加活动基本信息
        if (ret > 0) {

            AppActivityUser activityUser = new AppActivityUser();
            activityUser.setActivityId(ret);
            Integer[] ids = activityVo.getUserIds();
            // 循环添加活动参与用户
            for (Integer userId : ids) {
                activityUser.setUserId(userId);
                SysUser sysUser = userService.selectUserById(Long.parseLong(userId.toString()));
                activityUser.setUserName(sysUser.getUserName());
                activityUserMapper.insert(activityUser);  //添加活动参与用户
            }
            //添加活动附件
            Integer[] imgIds = activityVo.getIds();
            List<Integer> imgList = null;
            if (imgList != null) {
                AppActivityFile activityFile = new AppActivityFile();
                activityFile.setActivityId(activity.getId());
                List<Integer> intList = imgList.stream().map(id -> {
                    activityFile.setFileId(id);
                    return activityFileMapper.insert(activityFile);
                }).collect(Collectors.toList());
                return imgList.size() == intList.size();
            } else {
                return true;
            }
        } else {
            throw new ServiceException();
        }
    }

    /**
     * 修改活动
     *
     * @param activityVo 活动
     * @return 结果
     */
    @Override
    public int updateAppActivity(ActivityVo activityVo)
    {
        int ret = 0;
        AppActivity activity = new AppActivity();
        BeanUtils.copyProperties(activityVo, activity);
        ret = activityMapper.updateAppActivity(activity);
        List<Integer> integerList = new ArrayList<>();
        if (ret == 1) {
            Integer[] ids = activityVo.getUserIds();
            AppActivityUser activityUser = new AppActivityUser();
            for (Integer userId : ids) {
                appActivityUserService.deleteActivityUserById(Long.parseLong(activity.getId().toString()));
                activityUser.setActivityId(activity.getId());
                activityUser.setUserId(userId);
                SysUser sysUser = userService.selectUserById(Long.parseLong(userId.toString()));
                activityUser.setUserName(sysUser.getUserName());
                activityUserMapper.insert(activityUser);  //添加活动参与用户
            }
        }

        return  ret;
    }

    /**
     * 更新活动信息
     *
     * @param appActivity 活动
     * @return 结果
     */
    @Override
    public boolean updateActivityById(AppActivity appActivity) {
        int ret = 0;
        //ret = activityMapper.updateActivityStatus(appActivity);
        ret = activityMapper.updateById(appActivity);
        if(ret == 1) {
            return true;
        }
        else {
            throw new ServiceException();
        }
    }

    /**
     * 添加活动日志
     *
     * @param activityLog 活动
     * @return 结果
     */
    @Override
    public boolean insertActivityLog(AppActivityLog activityLog) {
        int ret = 0;
        if (activityLog.getCreateId() != null) {
            SysUser sysUser = userService.selectUserById(Long.parseLong(activityLog.getCreateId().toString()));
            activityLog.setCreateName(sysUser.getUserName());
            ret = activityLogMapper.insert(activityLog);
        }

        if(ret == 1) {
            return true;
        }
        else {
            throw new ServiceException();
        }
    }

    /**
     * 根据活动ID删除活动
     * @param activityId
     * @return
     */
    @Override
    public boolean deleteActivityById(Integer activityId) {

        //return activityMapper.deleteById(activityId) == 1;
        int ret = 0;
        AppActivity appActivity = new AppActivity();
        appActivity.setId(activityId);
        Byte Deleted = 1;
        appActivity.setDeleted(Deleted);//逻辑删除状态
        ret = activityMapper.deleteById(activityId);
        if(ret == 1) {
            return true;
        }
        else {
            throw new ServiceException();
        }
    }

    //添加活动文件
    @Override
    public boolean insertActivityFile(ActivityFileVo activityFileVo)
    {
        //添加活动附件
        Integer[] imgIds = activityFileVo.getFileIds();
        List<Integer> imgList = null;
        if (imgIds != null) {
            imgList = Arrays.stream(imgIds).collect(Collectors.toList());
        }
        if (imgList != null) {
            AppActivityFile activityFile = new AppActivityFile();
            activityFile.setActivityId(activityFileVo.getId());
            activityFile.setRemark(activityFileVo.getRemark());
            List<Integer> intList = imgList.stream().map(id -> {
                activityFile.setFileId(id);
                return activityFileMapper.insert(activityFile);
            }).collect(Collectors.toList());
            return imgList.size() == intList.size();
        } else {
            return true;
        }

    }

}
