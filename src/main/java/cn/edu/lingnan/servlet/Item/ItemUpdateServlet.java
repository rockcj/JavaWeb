package cn.edu.lingnan.servlet.Item;

import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.ItemService;
import cn.edu.lingnan.service.imp.ItemServiceImpMysql;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateItem")
public class ItemUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 从页面获取表单请求参数
        String iid = req.getParameter("iid");
        String iname = req.getParameter("iname");
        int iflag = Integer.parseInt(req.getParameter("iflag"));
        //2. 调用业务逻辑层完成数据库的增删查改操作
        new ItemServiceImpMysql().editItem(iid,iname,iflag);
        //3. 依据业务逻辑层的返回结果，跳转到相应的页面
        //要先查询并更新页面数据，再跳转页面
        //因为在另一个请求中查询并更新页面数据，所以还可以直接跳到另一个servlet请求
        resp.sendRedirect(req.getContextPath()+"/queryItemAll");
    }
}
