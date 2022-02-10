package com.industrial.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zhu
 * @date 2022年01月20日 16:01
 */
@Data
public class FollowVo {
    /**
     * 跟进任务表ID
     */
    private Integer id;
    /**
     * 跟进人
     */
    private List<Integer> followUser;
    /**
     * 下次跟进时间
     */
    private Date followTime;
    /**
     * 1,系统消息,2,邮件通知,3,短信通知
     */
    private Byte reminderType;
    /**
     * 提前通知时间(毫秒)
     */
    private Long aheadTime;

    /**
     * 跟进id
     */
    private Integer followId;
    /**
     * 跟进方式
     */
    private String followWay;

}
