package cn.edu.lingnan.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//对admin目录下的资源访问进行权限控制，只有管理员才能访问该目录下的资源（包括页面和请求）
@WebFilter("/admin/*")
public class AuthorityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //首先要判断是不是管理员，其实就是拿到session中的用户信息（登录用户的权限）（string的值）
        //ServletRequest是HttpServletRequest的父接口，所以可以强转为HttpServletRequest，相当于HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object sright = request.getSession().getAttribute("sright");//获取session中的用户信息（标志位string的值 判断是否为管理员）
//        Integer sright = (Integer) allStu;
        System.out.println("用户权限allStu:"+sright);
//        System.out.println("用户权限sright:"+sright);
        if(sright == null)
        {
            System.out.println("用户权限为空");
            response.sendRedirect(request.getContextPath()+"/Login.html");
        } else if(sright.equals(1)) {
            filterChain.doFilter(servletRequest, servletResponse);//进行往下走
        } else {
            response.sendRedirect(request.getContextPath()+"/authority.jsp");//重定向页面
            //request.getRequestDispatcher("/authority.jsp").forward(servletRequest, servletResponse);//重定向页面
        }
    }
}


//        if (allStu != null && sright.equals(1)) {
//            filterChain.doFilter(servletRequest, servletResponse);//进行往下走
//        } else {
//            //request.getRequestDispatcher("/authority.jsp").forward(servletRequest, servletResponse);
//            response.sendRedirect(request.getContextPath()+"/authority.jsp");//重定向页面
//        }
// 如果是管理员，就放行，
// 如果不是管理员，就重定向到登录页面
