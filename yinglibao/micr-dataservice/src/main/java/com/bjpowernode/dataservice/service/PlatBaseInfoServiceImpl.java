package com.bjpowernode.dataservice.service;

import com.bjpowernode.api.pojo.BaseInfo;
import com.bjpowernode.api.service.PlatBaseInfoService;
import com.bjpowernode.dataservice.mapper.BidInfoMapper;
import com.bjpowernode.dataservice.mapper.ProductInfoMapper;
import com.bjpowernode.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.math.BigDecimal;

@DubboService(interfaceClass = PlatBaseInfoService.class,version = "1.0")
public class PlatBaseInfoServiceImpl implements PlatBaseInfoService {
    @Autowired
    //@Resource
    private UserMapper userMapper;

    @Autowired
    //@Resource
    private ProductInfoMapper productInfoMapper;

    @Autowired
    //@Resource
    private BidInfoMapper bidInfoMapper;

    /**平台的基本信息，这是一个dubbo服务者的实现类，需要暴漏服务信息*/
    @Override
    public BaseInfo queryPlatBaseInfo() {
        /*需要获取注册人数，收益率平均值，累计成交金额*/
        //注册人数
        int RegisterUser = userMapper.selectCountUsers();

        //收益率平均值
        BigDecimal avgRate = productInfoMapper.selectAvgRate();

        //累计成交金额
        BigDecimal sumBidMoney = bidInfoMapper.selectSumBidMoney();

        BaseInfo baseInfo = new BaseInfo(avgRate, sumBidMoney, RegisterUser);

        return baseInfo;
    }
}
