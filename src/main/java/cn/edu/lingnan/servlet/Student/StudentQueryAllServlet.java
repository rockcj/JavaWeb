package cn.edu.lingnan.servlet.Student;

import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/queryStuAll")
public class StudentQueryAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取页面参数
        String flag = req.getParameter("flag");
        HttpSession session = req.getSession();
        //2.调用业务逻辑层完成数据库的查询操作
        List<Student> list;
        if(flag == null)
        {
            list = new StudentServiceImpMysql().queryAllStudentAll();
        } else{
            int Flag = Integer.valueOf(flag);
            list = new StudentServiceImpMysql().queryAllStudentAll(Flag);
            session.setAttribute("ok",Flag);
        }
//        if(req.getSession()==null)
//        {
//            System.out.println("session为空");
//            resp.sendRedirect(req.getContextPath()+"Login.html");
//        }
        req.getSession().setAttribute("allStu",list);
        //3.页面跳转
        resp.sendRedirect(req.getContextPath()+"/admin/allStu.jsp");
    }
}
