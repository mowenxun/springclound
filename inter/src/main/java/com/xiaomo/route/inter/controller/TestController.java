package com.xiaomo.route.inter.controller;

import com.xiaomo.common.entity.response.ResultResponse;
import com.xiaomo.route.inter.remote.test.TestRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestController
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/3/25
 * @Version V1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestRemote testRemote;

    @RequestMapping("/hello")
    public ResultResponse testRemote() {
        Map<String, Object> para = new HashMap<>();
        para.put("aaaa", "12121");
        return testRemote.remoteHello(para);
    }
}
