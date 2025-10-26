package cn.edu.lingnan.servlet.Student;

import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/updateStu")
public class StudentUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 从页面获取表单请求参数
        String sid = req.getParameter("sid");
        String sname = req.getParameter("sname");
        String spassword = req.getParameter("spassword");
        int sright =  Integer.parseInt(req.getParameter("sright"));
        int stflag = Integer.parseInt(req.getParameter("stflag"));
        //2. 调用业务逻辑层完成数据库的增删查改操作
        Student student = new Student(sid, sname, spassword, sright, stflag);
        int result = new StudentServiceImpMysql().updateStudent(student);
        //3. 依据业务逻辑层的返回结果，跳转到相应的页面
        //要先查询并更新页面数据，再跳转页面
//        List<Student> students = new StudentServiceImpMysql().queryAllStudentAll();
//        req.getSession().setAttribute("allStu", students);//更新session数据
//        resp.sendRedirect(req.getContextPath()+"/admin/allStu.jsp");//跳转页面
        //因为在另一个请求中查询并更新页面数据，所以还可以直接跳到另一个servlet请求
        resp.sendRedirect(req.getContextPath()+"/queryStuAll");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
