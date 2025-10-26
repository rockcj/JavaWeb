package cn.edu.lingnan.servlet.Job;
import cn.edu.lingnan.service.imp.JobServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteJob")
public class JobDeleteServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取表单数据
        String sid = req.getParameter("sid");
        String iid = req.getParameter("iid");
        String flag = req.getParameter("flag");
        //调用业务逻辑层完成数据库的增删查改操作
        JobServiceImpMysql jobSer = new JobServiceImpMysql();
        //批量删除和单个删除
        if(flag!=null)//多
        {
            String sids [] = sid.split(",");
            String iids [] = iid.split(",");
            for (int i = 0; i < sids.length; i++) {
                jobSer.deleteJob(sids[i],iids[i]);
            }
        }else{
            jobSer.deleteJob(sid,iid);
        }
        resp.sendRedirect(req.getContextPath()+"/queryJobAll");
    }
}
