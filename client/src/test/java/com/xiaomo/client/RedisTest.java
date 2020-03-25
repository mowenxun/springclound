package com.xiaomo.client;

import com.xiaomo.client.util.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName RedisTest
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/3/25
 * @Version V1.0
 **/
public class RedisTest extends BasicTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void add() {
        redisUtil.set("yuanfen", "yaner");
    }

}
