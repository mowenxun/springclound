package com.xiaomo.common.test;

import com.xiaomo.common.util.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @ClassName RedisTest
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/3/18
 * @Version V1.0
 **/
public class RedisTest extends BasicTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void existsKey() {
        boolean f = redisUtil.hasKey("12");
        System.out.println("f==" + f);
    }

    @Test
    public void getSeqNo() {
        String s = redisUtil.getSeqNoByRedis("meituan", 3);
        System.out.println("s==" + s);
        s = redisUtil.getSeqNoByRedis("meituan", 3);
        System.out.println("s==" + s);
        s = redisUtil.getSeqNoByRedis("eleme", 5);
        System.out.println("s==" + s);
        s = redisUtil.getSeqNoByRedis("eleme", 5);
        System.out.println("s==" + s);

    }

    @Test
    public void testLock() {
        String key = "test:lock:key";
        String requestId = UUID.randomUUID().toString().replaceAll("-", "");
        int expireTime = 60 * 1000;//1s
        if (redisUtil.tryGetDistributedLock(key, requestId, expireTime)) {
            System.out.println("获得锁执行=========");
            try {
                Integer c = null;
                int a = c / 133;
            } catch (Exception e) {
                redisUtil.releaseDistributedLock(key, requestId);
            }
        }
    }
}
