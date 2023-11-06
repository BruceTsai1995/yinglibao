package com.bjpowernode.api.service;

import com.bjpowernode.api.pojo.BaseInfo;

//平台的基本信息
public interface PlatBaseInfoService {
    /*计算利率，注册人数，累计成交金额*/
    BaseInfo queryPlatBaseInfo();

}
