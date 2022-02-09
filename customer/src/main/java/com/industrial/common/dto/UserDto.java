package com.industrial.common.dto;

import com.industrial.domin.User;
import lombok.Data;

/**
 * @author zhu
 * @date 2022年02月09日 15:20
 */
@Data
public class UserDto extends User {
    private String deptName;
}
