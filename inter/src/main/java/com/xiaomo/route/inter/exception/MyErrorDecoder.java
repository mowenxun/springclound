package com.xiaomo.route.inter.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaomo.common.entity.exception.ApplicationException;
import com.xiaomo.common.entity.response.ResultResponse;
import feign.Response;
import feign.RetryableException;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.CharBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import static feign.Util.*;
import static java.lang.String.format;
import static java.util.Locale.US;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 自定义ErrorDecoder，用来解决feign调用默认抛出的是FeignExcetpion异常的问题
 *
 * @author yangxi
 */
@Component
public class MyErrorDecoder implements ErrorDecoder {

    private final RetryAfterDecoder retryAfterDecoder = new RetryAfterDecoder();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        ApplicationException exception = this.errorStatus(methodKey, response, objectMapper);
        Date retryAfter = retryAfterDecoder.apply(firstOrNull(response.headers(), RETRY_AFTER));
        if (retryAfter != null) {
            //int status, String message, Request.HttpMethod httpMethod, Date retryAfter, Request request
            return new RetryableException(response.status(), exception.getMessage(), response.request().httpMethod(), retryAfter, response.request());
        }
        return exception;
    }

    private ApplicationException errorStatus(String methodKey, Response response, ObjectMapper objectMapper) {
        String message = format("status %s reading %s", response.status(), methodKey);
        String code = response.status() + "";
        try {
            if (response.body() != null) {
                //int a = response.body().length();
               // InputStream io = response.body().asInputStream();
              //  String s=toConvertString(io);
                String body = Util.toString(response.body().asReader());
                //toString11(response.body().asReader());
                //Util.toString(response.body().asReader());
                ResultResponse<?> resultResponse = objectMapper.readValue(body, ResultResponse.class);
                code = resultResponse.getStatus();
                message = resultResponse.getMsg();
            }
        } catch (IOException ignored) { // NOPMD
            ignored.printStackTrace();
        }
        ApplicationException applicationException = new ApplicationException(message);
        applicationException.setCode(code);
        return applicationException;
    }

    private <T> T firstOrNull(Map<String, Collection<T>> map, String key) {
        if (map.containsKey(key) && !map.get(key).isEmpty()) {
            return map.get(key).iterator().next();
        }
        return null;
    }

    /**
     * Decodes a {@link Util#RETRY_AFTER} header into an absolute date, if possible. <br> See <a
     * href="https://tools.ietf.org/html/rfc2616#section-14.37">Retry-After format</a>
     */
    static class RetryAfterDecoder {

        static final DateFormat
                RFC822_FORMAT =
                new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", US);
        private final DateFormat rfc822Format;

        RetryAfterDecoder() {
            this(RFC822_FORMAT);
        }

        RetryAfterDecoder(DateFormat rfc822Format) {
            this.rfc822Format = checkNotNull(rfc822Format, "rfc822Format");
        }

        protected long currentTimeMillis() {
            return System.currentTimeMillis();
        }

        /**
         * returns a date that corresponds to the first time a request can be retried.
         *
         * @param retryAfter String in <a href="https://tools.ietf.org/html/rfc2616#section-14.37"
         *                   >Retry-After format</a>
         */
        public Date apply(String retryAfter) {
            if (retryAfter == null) {
                return null;
            }
            if (retryAfter.matches("^[0-9]+$")) {
                long deltaMillis = SECONDS.toMillis(Long.parseLong(retryAfter));
                return new Date(currentTimeMillis() + deltaMillis);
            }
            synchronized (rfc822Format) {
                try {
                    return rfc822Format.parse(retryAfter);
                } catch (ParseException ignored) {
                    return null;
                }
            }
        }
    }

    public static String toString11(Reader reader) throws IOException {
        if (reader == null) {
            return null;
        } else {
            try {
                StringBuilder to = new StringBuilder();
                CharBuffer charBuf = CharBuffer.allocate(2048);
                CharBuffer buf = charBuf;
                try {

                    while (reader.read(charBuf) != -1) {
                        buf.flip();
                        to.append(charBuf);
                        buf.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String var4 = to.toString();
                return var4;
            } finally {
                ensureClosed(reader);
            }
        }
    }

    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);
        try {
            String line;
            line = read.readLine();
            while (line != null) {
                res.append(line + "");
                        line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != isr) {
                    isr.close();
                    isr.close();
                }
                if (null != read) {
                    read.close();
                    read = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
            }
        }
        return res.toString();
    }

}