package com.industrial.common.dto;

import com.industrial.domin.AppUser;
import com.industrial.domin.AppUserAddress;
import com.industrial.domin.AppUserSalesman;

import java.util.List;

public class AppUserDto{
    public AppUser appUser;
    public List<AppUserAddress> customList;
    public List<AppUserSalesman> saleManList;
}
