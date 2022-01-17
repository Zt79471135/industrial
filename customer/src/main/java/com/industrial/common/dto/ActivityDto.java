package com.industrial.common.dto;

import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.entity.AppActivity;
import lombok.Data;

import java.util.List;

/**
 * @author zhu
 * @date 2022年01月14日 11:54
 */
@Data
public class ActivityDto extends AppActivity {
    private List<SysUser> userList;
    private SysUser user;
}
