package cn.edu.lingnan.servlet.Job;

import cn.edu.lingnan.service.imp.JobServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateJob")
public class JobUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面参数
        String sid = req.getParameter("sid");
        String iid = req.getParameter("iid");
        String job = req.getParameter("job");
        int scflag = Integer.parseInt(req.getParameter("scflag"));
        //2. 调用业务逻辑层完成数据库的增删查改操作
        new JobServiceImpMysql().editJob(sid,iid,job,scflag);
        //3. 依据业务逻辑层的返回结果，跳转到相应的页面
        resp.sendRedirect(req.getContextPath()+"/queryJobAll");
    }
}
