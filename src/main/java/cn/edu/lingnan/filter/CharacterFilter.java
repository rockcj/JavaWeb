package cn.edu.lingnan.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

//  字符集过滤器
//需求：对传入和传出的请求设置字符编码格式
@WebFilter(urlPatterns = {"/*"},initParams = {@WebInitParam(name = "newChar",value = "UTF-8")})
public class CharacterFilter implements Filter {
    String newChar = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        newChar = filterConfig.getInitParameter("newChar");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest req = servletRequest;
        ServletResponse resp = servletResponse;
        System.out.println("字符集:" + newChar);
        req.setCharacterEncoding(newChar);// *解决中文乱码
        resp.setCharacterEncoding(newChar);
        //放行
        filterChain.doFilter(servletRequest, servletResponse);//进行往下走
    }
}
