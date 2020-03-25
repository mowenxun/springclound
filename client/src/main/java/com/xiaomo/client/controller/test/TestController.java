package com.xiaomo.client.controller.test;

import com.alibaba.fastjson.JSONObject;
import com.xiaomo.common.entity.constant.ResultEnum;
import com.xiaomo.common.entity.exception.ApplicationException;
import com.xiaomo.common.entity.response.ResponseCode;
import com.xiaomo.common.entity.response.ResultResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName TestController
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/3/25
 * @Version V1.0
 **/
@RestController
public class TestController {

    @RequestMapping("/hello")
    public ResultResponse testRemote(@RequestBody Map<String, Object> para) {
        System.out.println("入参para==" + JSONObject.toJSONString(para));
        if (para != null) {
            throw new ApplicationException(ResultEnum.TOKEN_NOT_FOUND);
        }
        return new ResultResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), para);
    }

}
