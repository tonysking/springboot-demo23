package com.example.demo23.controller;

import com.example.demo23.common.HttpClientUtil;
import com.example.demo23.common.IMoocJSONResult;
import com.example.demo23.common.JsonUtils;
import com.example.demo23.common.RedisOperator;
import com.example.demo23.model.WXSessionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WXLoginController {

    @Autowired
    private RedisOperator redis;
    public static final String USER_REDIS_SESSION = "user-redis-session";

    @PostMapping("/wxLogin")
    public IMoocJSONResult wxLogin(String code) {
        System.out.println("wxLogin-code: "+code);

        //https://api.weixin.qq.com/sns/jscode2session?
        //                              appid=APPID&
        //                              secret=SECRET&
        //                              js_code=JSCODE&
        //                              grant_type=authorization_code
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> param = new HashMap<>();
        param.put("appid","wx68a522d3964051da");
        param.put("secret","fd964f98cc605ad910b800cf828390fc");
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
        //获取{"session_key":"","openid":""}
        String wxResult = HttpClientUtil.doGet(url, param);
        System.out.println(wxResult);
        //将获得的json字符串转换为对象
        WXSessionModel wxSessionModel = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);
        //存入session到redis
        redis.set(USER_REDIS_SESSION + ":" + wxSessionModel.getOpenid(),
                                          wxSessionModel.getSession_key(),
                                          1000 * 60 * 30);
        return IMoocJSONResult.ok();
    }
}
