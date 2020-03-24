package com.xiaomo.client;

import com.alibaba.fastjson.JSONObject;
import com.xiaomo.client.mapper.test.TestMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DbTest
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/3/24
 * @Version V1.0
 **/
public class DbTest extends BasicTest {
    @Autowired
    private TestMapper testMapper;

    @Test
    public void test() {
        List<Map<String, Object>> list = testMapper.getAll();
        System.out.println(JSONObject.toJSONString(list));
    }
}
