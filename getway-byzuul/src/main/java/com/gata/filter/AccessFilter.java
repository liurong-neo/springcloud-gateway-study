package com.gata.filter;

import com.gata.requestWrapper.AddHeaderRequestWrapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 访问过滤器
 */
@Component
public class AccessFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器类型选择：
     * pre 为路由前
     * route 为路由过程中
     * post 为路由过程后
     * error 为出现错误的时候
     * 同时也支持static ，返回静态的响应，详情见StaticResponseFilter的实现
     * 以上类型在会创建或添加或运行在FilterProcessor.runFilters(type)
     */
    @Override
    public String filterType() {
        return "pre"; //ZuulFilter源码中注释"pre"为在路由前过滤
    }

    /**
     * 用来过滤器排序执行的
     * @return 排序的序号
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否通过这个过滤器，默认为true，改成false则不启用
     */
    @Override
    public boolean shouldFilter() {
        return true; //返回true表示执行这个过滤器
    }

    /**
     * 过滤器的逻辑
     */
//    @Override
//    public Object run() {
//        //获取当前请求上下文
//        RequestContext ctx = RequestContext.getCurrentContext();
//        //取出当前请求
//        HttpServletRequest request = ctx.getRequest();
//        logger.info("进入访问过滤器，访问的url:{}，访问的方法：{}",request.getRequestURL(),request.getMethod());
//        String accessToken = request.getParameter("token");
//        if(StringUtils.isEmpty(accessToken)) {
//            logger.info("当前请求没有accessToken");
//            //使用Zuul来过滤这次请求
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            return null;
//        }
//        logger.info("请求通过过滤器");
//        return null;
//    }


    @Override
    public Object run() {
        try {
            //获取当前请求上下文
            RequestContext ctx = RequestContext.getCurrentContext();
            //取出当前请求
            HttpServletRequest request = ctx.getRequest();
            logger.info("进入访问过滤器，访问的url:{}，访问的方法：{}",request.getRequestURL(),request.getMethod());
            String userArea = request.getParameter("userArea");
            String userName = "neoTest";
            HttpServletResponse response = ctx.getResponse();
            RequestContext requestContext = RequestContext.getCurrentContext();
            requestContext.addZuulRequestHeader("userName", userName);
            ctx.setResponse(response);
            Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
            if(request.getRequestURI().contains("/index")) {
                List oneParamList = new ArrayList<String>(){{
                    add(userArea);
                }};
                requestQueryParams.put("boxArea",oneParamList);
            }
            if(null != requestQueryParams){
                ctx.setRequestQueryParams(requestQueryParams);
            }
            return null;
        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
            //  ReflectionUtils.
        }
        return null;
    }
}
