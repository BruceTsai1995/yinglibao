package com.bjpowernode.front.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.common.constants.RedisKey;
import com.bjpowernode.front.config.JdwxSmsConfig;
import com.bjpowernode.front.service.SmsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * 注册发送短信验证码,这个功能是独立的，只是发送短信的业务
 */
@Service
public class SmsCodeRegisterImpl implements SmsService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private JdwxSmsConfig smsConfig;

    @Override
    public boolean sendSms(String phone) {
        boolean send = false;
        //设置短信的内容
        String random = RandomStringUtils.randomNumeric(4);
        System.out.println("注册验证码的随机数 random = " + random);

        //更新content中的 %s 【大富科技】你的验证码是 :%s，3分钟内有效，请勿泄露给他人
        String content = String.format(smsConfig.getContent(), random);

        //使用HttpCliet发送请求给第三方
        CloseableHttpClient client = HttpClients.createDefault();

        String url = smsConfig.getUrl() + "?mobile=" + phone
                + "&content=" + content
                + "&appkey=" + smsConfig.getAppkey();

        HttpGet get = new HttpGet(url);

        try {
            CloseableHttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //得到返回的json数据,text是json数据
                String text = EntityUtils.toString(response.getEntity());

                //解析json
                if (StringUtils.isNotBlank(text)) {
                    //使用fastJson
                    JSONObject jsonObject = JSONObject.parseObject(text);
                    //以下与第三方的API有关
                    if ("10000".equals(jsonObject.getString("code"))) {//第三方接口调用成功
                        //读取result中的key ： ReturnStatus
                        if ("Success".equalsIgnoreCase(jsonObject.getJSONObject("result").getString("ReturnStatus"))) {
                            //短信发送成功
                            send = true;

                            //将短信的验证码存储到redis中
                            String key = RedisKey.KEY_SMS_CODE_REG + phone;
                            stringRedisTemplate.boundValueOps(key).set(random,3, TimeUnit.MINUTES);

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return send;
    }

    @Override
    public boolean checkSmsCode(String phone, String code) {
        String key = RedisKey.KEY_SMS_CODE_REG + phone;
        if (stringRedisTemplate.hasKey(key)){
            String querySmsCode = stringRedisTemplate.boundValueOps(key).get();
            if(code.equals(querySmsCode)){
                return true;
            }
        }
        return false;
    }
}
