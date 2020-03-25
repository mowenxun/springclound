package com.xiaomo.route.inter.remote.test;

import com.xiaomo.common.entity.response.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 通过controller类型的调用
 * 测试远程调用接口
 */
@FeignClient(name = "eureka-client")
public interface TestRemote {

    @RequestMapping("/hello")
    ResultResponse remoteHello(Map<String, Object> para);
}
