package com.industrial.common.dto;

import com.industrial.domin.AppCheckMainConfig;
import com.industrial.domin.AppSubCheckConfig;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CheckConfigDto extends AppCheckMainConfig {
    public List<AppSubCheckConfig> subList;
}
