package cn.edu.lingnan.servlet;

import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 从页面获取表单请求参数
        String userId = req.getParameter("userId");
        String username = req.getParameter("username");
        String password = req.getParameter("OKPassword");
        System.out.println("userId:" + userId + " username:" + username + "  password:" + password);

        // 验证参数是否为空
        if (userId == null || userId.trim().isEmpty()) {
            System.out.println("用户ID不能为空");
            req.setAttribute("errorId", "用户ID不能为空");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }
        if (username == null || username.trim().isEmpty()) {
            System.out.println("用户名不能为空");
            req.setAttribute("errorName", "用户名不能为空");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }
        if (password == null || password.trim().isEmpty()) {
            System.out.println("密码不能为空");
            req.setAttribute("errorName", "密码不能为空");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        //2. 调用业务逻辑层完成数据库的增删查改操作
        Student stu = new StudentServiceImpMysql().queryStudentById(userId);
        //3. 依据业务逻辑层的返回结果，跳转到相应的页面
        //(或往客户端写数据)、(或从一个servlet到另一个servlet不一定是页面跳转)
        if (stu!=null) {
            System.out.println("该id以被注册，请重新输入");
            req.setAttribute("errorId", "该id以被注册，请重新输入"); // 存储错误信息到 request
            req.getRequestDispatcher("register.jsp").forward(req, resp); // 转发到 register.jsp
        } else {
            List<Student> stu1 = new StudentServiceImpMysql().queryStudentByName(username);
            if (stu1.size()==0) {
                Student student = new Student(userId.trim(), username.trim(), password.trim(), 0, 0);
                new StudentServiceImpMysql().insertStudent(student);
                resp.sendRedirect("Login.html");
            }else {
                System.out.println("该用户名已存在，请重新输入");
                req.setAttribute("errorName", "该用户名已存在，请重新输入"); // 存储错误信息到 request
                req.getRequestDispatcher("register.jsp").forward(req, resp); // 转发到 register.jsp
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
