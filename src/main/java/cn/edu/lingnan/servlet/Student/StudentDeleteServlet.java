package cn.edu.lingnan.servlet.Student;

import cn.edu.lingnan.service.StudentService;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteStu")
public class StudentDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 从页面获取表单请求参数
        String sid = req.getParameter("sid");
        String flag = req.getParameter("flag");
        System.out.println("删除的学生id为" + sid);
        //2. 调用业务逻辑层完成数据库的增删查改操作
        StudentService stuService = new StudentServiceImpMysql();
        //批量删除和单个删除
        if(flag!=null)
        {
            String sids [] = sid.split(",");
            for(String sid1:sids)
            {
                stuService.deleteStudentById(sid1);
            }
        }else{
            stuService.deleteStudentById(sid);//删除
        }
        //3. 依据业务逻辑层的返回结果，跳转到相应的页面
        //要先查询并更新页面数据，再跳转页面
        //因为在另一个请求中查询并更新页面数据，所以还可以直接跳到另一个servlet请求
        resp.sendRedirect(req.getContextPath()+"/queryStuAll?flag=1");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
