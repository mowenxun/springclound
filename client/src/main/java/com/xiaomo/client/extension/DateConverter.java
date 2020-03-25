package com.xiaomo.client.extension;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ffq
 * @Date 2019/6/13 20:47
 * @see [相关类/方法]
 * @since 1.0
 */
@Component
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        return new Date(Long.parseLong(source));
    }

}