package cn.edu.lingnan.servlet.Job;

import cn.edu.lingnan.Dto.JobDto;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.service.imp.JobServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/All")
public class All extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取页面参数
        //2.调用业务逻辑层完成数据库的查询操作
        List<JobDto> list = new JobServiceImpMysql().queryJobDtoAll();
        req.getSession().setAttribute("JobDto",list);
        //3.页面跳转
        resp.sendRedirect(req.getContextPath()+"/admin/all.jsp");
    }
}
