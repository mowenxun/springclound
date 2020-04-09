package com.xiaomo.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cpb
 * @version 1.0
 * @date 2019/9/29 13:53
 */
@Component
@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {
    //@Autowired
    // private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
/*        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //有注解并且value为true通过
        HandlerMethod hm = (HandlerMethod) handler;
        PassToken passToken = hm.getMethodAnnotation(PassToken.class);
        if (passToken != null && passToken.value()) {
            return true;
        }
        String token = request.getHeader("token");
        String originId = request.getHeader("originId");
        if (StringUtils.isBlank(token) && StringUtils.isBlank(originId)) {
            throw new ApplicationException("token或者组织id为空！");
        }
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        if (null == method) {
            throw new ApplicationException("没有请求方法类型");
        }
        //get请求校验tenantId
        if ("GET".equals(method)) {
            String tenantId = getParam(request, "tenantId");
            log.info(requestURI + ":tenantId:{}", tenantId);
        }
        //post请求校验tenantId

        //put请求校验tenantId

        //delete请求校验tenantId
        if (!loginService.verify(token)) {
            throw new ApplicationException("token错误或者已经过期！");
        }
        CheckPermissionReqVO checkPermissionReqVO = new CheckPermissionReqVO();
        checkPermissionReqVO.setMethod(method);
        checkPermissionReqVO.setOriginId(Long.valueOf(originId));
        checkPermissionReqVO.setToken(token);
        checkPermissionReqVO.setUrl(requestURI);
        if (!loginService.checkPermission(checkPermissionReqVO)) {
            throw new ApplicationException("用户未含有该权限！");
        }*/
        return true;
    }

    /**
     * 获取业务参数
     *
     * @param request
     * @param param
     * @throws Exception
     */
    private String getParam(HttpServletRequest request, String param) {
        String[] reqParam = request.getParameterValues(param);
        return (reqParam == null || reqParam.length < 1 ? null : reqParam[0]);
    }
}
