package com.industrial.common.vo;

import com.industrial.domin.AppActivity;
import lombok.Data;

import java.util.List;

/**
 * @author zhu
 * @date 2022年01月14日 11:54
 */
@Data
public class ActivityVo extends AppActivity {
    /**
     * 参与人员ID
     */
    //public List<Integer> userIdList;
    public Integer[] userIds;
    //public String[] userNames;
    /**
     * 图片外键（关联app_image_file表ID）
     */
    private Integer[] ids;
}
