package cn.edu.lingnan.servlet;

import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//使用注解
@WebServlet({"/login","/error"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);//调用了super.doGet(req, resp)。这里的问题可能在于父类的doGet方法（即HttpServlet的doGet）可能已经向响应中写入了内容，或者设置了响应状态，导致响应已经被提交。例如，如果父类的doGet方法输出了内容，那么响应的commit状态会被触发，之后调用sendRedirect就会失败。
        System.out.println("doget被调用\n");

        //1. 从页面获取表单请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username:" + username + "  password:" + password);

        //2. 调用业务逻辑层完成数据库的增删查改操作
        Student stu = new StudentServiceImpMysql().queryStudentByNameAndPassword(username, password);
        //3. 依据业务逻辑层的返回结果，跳转到相应的页面
        //(或往客户端写数据)、(或从一个servlet到另一个servlet不一定是页面跳转)

        if (stu!=null) {
            HttpSession session = req.getSession();//获取session，记录会话
            session.setAttribute("sright", stu.getSright());//设置session，标记登录用户的属性,做权限过滤
            session.setAttribute("stflag", stu.getStflag());
            session.setAttribute("user", stu.getSright() == 1 ? username+"管理员" : username+"用户");//设置session，标记登录用户的属性
            session.setAttribute("flag",  stu.getStflag()==1? "正常" : "审核未通过");
            System.out.println("登录成功");
            if(stu.getSright()==1||stu.getStflag()!=1)
            {
                resp.sendRedirect("main.jsp");
            }
            else{
                session.setAttribute("sid", stu.getSid());
                resp.sendRedirect("/oneStu");
            }
        } else {
            System.out.println("登录失败");
            resp.sendRedirect("loginError.html");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        System.out.println("dopost被调用\n");
        doGet(req, resp);
    }
}
