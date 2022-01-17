package com.industrial.common.vo;

import com.industrial.entity.AppActivity;
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
    private List<Integer> userIdList;
}
