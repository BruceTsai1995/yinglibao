package com.bjpowernode.front.controller;

import com.bjpowernode.api.service.InvestService;
import com.bjpowernode.api.service.PlatBaseInfoService;
import com.bjpowernode.api.service.ProductService;
import com.bjpowernode.api.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

public class BaseController {
    //声明公共的方法，属性等等
    @Resource
    protected StringRedisTemplate stringRedisTemplate;
    //声明远程服务
    //平台信息服务
    @DubboReference(interfaceClass = PlatBaseInfoService.class,version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;

    //产品服务
    @DubboReference(interfaceClass = ProductService.class,version = "1.0")
    protected ProductService productService;

    //投资服务
    @DubboReference(interfaceClass = InvestService.class,version = "1.0")
    protected InvestService investService;

    //用户服务
    @DubboReference(interfaceClass = UserService.class,version = "1.0")
    protected UserService userService;
}
