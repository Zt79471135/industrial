package com.industrial.common.dto;

import com.industrial.domin.AppCheckMainConfig;

import java.util.Date;

public class CheckDto extends AppCheckMainConfig {
    public Integer cid;
    public Integer ctype;
    public Integer clevel;
    public Date subAddTime;
    public Date subUpdateTime;
}
