package com.bjpowernode.dataservice.service;

import com.bjpowernode.api.model.User;
import com.bjpowernode.api.service.UserService;
import com.bjpowernode.common.util.CommonUtil;
import com.bjpowernode.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(interfaceClass = UserService.class,version = "1.0")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User queryByPhone(String phone) {
        User user = null;
        if (CommonUtil.checkPhone(phone)) {
            user = userMapper.selectByPhone(phone);
        }
        return user;
    }
}
