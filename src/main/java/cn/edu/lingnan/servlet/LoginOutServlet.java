package cn.edu.lingnan.servlet;

import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet; // 已注释，不再使用
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 已迁移到 LoginController，此Servlet已废弃
// @WebServlet("/loginout")
public class LoginOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.注销
        req.getSession().invalidate();//销毁session
        //2.重定向到登录页面
        resp.sendRedirect(req.getContextPath()+"/Login.html");//重定向到登录页面(最好先定位到根目录)
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
