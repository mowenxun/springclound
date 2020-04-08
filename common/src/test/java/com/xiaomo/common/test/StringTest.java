package com.xiaomo.common.test;

import com.xiaomo.common.entity.query.UserQuery;

/**
 * @ClassName StringTest
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/4/1
 * @Version V1.0
 **/
public class StringTest {
    public static void main(String[] args) {
        Integer a = 10;
        Integer b = 10;
        Integer c = 169;
        Integer d = 169;
        Integer e = 172;
        System.out.println((a.equals(b)) + ";" + a.equals(c) + ";" + c.equals(d));
        System.out.println((a == b) + ";" + (c == d) + ";" + (e > d));

        UserQuery userQuery=new UserQuery();
        System.out.println(userQuery.getAppId());

    }
}
