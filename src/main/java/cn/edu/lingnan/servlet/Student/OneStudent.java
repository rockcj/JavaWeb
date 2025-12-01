package cn.edu.lingnan.servlet.Student;

import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;
import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet; // 已注释，不再使用
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
// import java.util.List; // 未使用

// 已迁移到 LoginController，此Servlet已废弃
// @WebServlet("/oneStu")
public class OneStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取session
        HttpSession session = req.getSession();
        if(session!=null)
        {
            String sid = (String) session.getAttribute("sid");
            //2.调用业务逻辑层完成数据库的查询操作
            Student stu = new StudentServiceImpMysql().queryStudentById( sid);
            req.getSession().setAttribute("stu",stu);
            //3.页面跳转
            resp.sendRedirect(req.getContextPath()+"/USER/Stu.jsp");
        }
    }
}
