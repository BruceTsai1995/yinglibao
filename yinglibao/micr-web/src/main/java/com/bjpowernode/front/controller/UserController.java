package com.bjpowernode.front.controller;

import com.bjpowernode.api.model.User;
import com.bjpowernode.common.enums.RCode;
import com.bjpowernode.common.util.CommonUtil;
import com.bjpowernode.front.service.SmsService;
import com.bjpowernode.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户功能")
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController {

    @Resource
    private SmsService smsService;

    /**
     * 手机号注册用户
     */
    @ApiOperation(value = "手机号的注册用户")
    @PostMapping("/register")
    public RespResult userRegister(@RequestParam String phone,
                                   @RequestParam String pword,
                                   @RequestParam String scode) {
        RespResult result = RespResult.fail();
        //1.检查参数
        if (CommonUtil.checkPhone(phone)) {
            if (pword != null && pword.length() == 32) {
                //检查短信验证码
                if (smsService.checkSmsCode(phone, scode)) {
                    //可以注册

                } else {
                    //短信验证码无效
                    result.setRCode(RCode.SMS_CODE_INVALID);
                }


            } else {
                //实际上时密码不正确，但是提示的比较模糊
                result.setRCode(RCode.REQUEST_PARAM_ERR);
            }

        } else {
            //手机号格式不正确
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }
        return result;
    }


    /**
     * 手机号是否存在
     */
    @ApiOperation(value = "手机号是否注册过", notes = "在注册功能中，判断手机号是否可以注册")
    @ApiImplicitParam(name = "phone", value = "手机号")
    @GetMapping("/phone/exists")
    public RespResult phoneExists(@RequestParam("phone") String phone) {
        RespResult result = new RespResult();
        result.setRCode(RCode.PHONE_EXISTS);

        //1.检查前端传过来的请求参数是否符合要求
        if (CommonUtil.checkPhone(phone)) {
            //执行逻辑,查询数据库，调用数据服务
            User user = userService.queryByPhone(phone);
            if (user == null) {
                //说明可以注册
                result = RespResult.ok();

            }

        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }
        return result;
    }
}
