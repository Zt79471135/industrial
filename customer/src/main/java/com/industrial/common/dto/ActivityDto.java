package com.industrial.common.dto;

import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.domin.AppActivity;
import com.industrial.domin.AppActivityUser;
import com.industrial.domin.AppActivityLog;
import com.industrial.domin.AppImageFile;
import lombok.Data;

import java.util.List;

/**
 * @author chenjh
 * @date 2022年01月14日 11:54
 */
@Data
public class ActivityDto extends AppActivity {
    //private List<SysUser> userList;
    private String ActTypeName;
    private String ActStatuName;
    private String HeadUserName;
    private List<AppActivityUser> activityUserList;
    private List<AppActivityLog> activityLogList;
    private SysUser user;
    //private List<AppImageFile> imgUrls;
}
