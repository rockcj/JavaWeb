package cn.edu.lingnan.servlet.Student;

import cn.edu.lingnan.Dto.JobDto;
import cn.edu.lingnan.service.imp.JobServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/details")
public class details extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取session
        HttpSession session = req.getSession();
        if(session!=null)
        {
            String sid = (String) session.getAttribute("sid");
            //2.调用业务逻辑层完成数据库的查询操作
            List<JobDto> list = new JobServiceImpMysql().queryJobDtoStuBySid(sid);
            req.getSession().setAttribute("StuJobDto",list);
            //3.页面跳转
            resp.sendRedirect(req.getContextPath()+"/USER/Details.jsp");
        }
    }
}
