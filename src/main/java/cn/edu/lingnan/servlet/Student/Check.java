package cn.edu.lingnan.servlet.Student;

import cn.edu.lingnan.service.StudentService;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


//批量通过审核
@WebServlet("/check")
public class Check extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 从页面获取表单请求参数
        String sid = req.getParameter("sid");
        String flag = req.getParameter("count");
        //2. 调用业务逻辑层完成数据库的增删查改操作
        StudentService stuService = new StudentServiceImpMysql();
        if(flag!=null)
        {
            String sids [] = sid.split(",");
            for(String sid1:sids)
            {
                stuService.editStudentFlag(sid1,1);
            }
        }else{
            stuService.editStudentFlag(sid,1);//通过
        }
        //3. 依据业务逻辑层的返回结果，跳转到相应的页面
        resp.sendRedirect(req.getContextPath()+"/queryStuAll?flag=1");
    }
}
