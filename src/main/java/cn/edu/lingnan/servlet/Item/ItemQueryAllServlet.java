package cn.edu.lingnan.servlet.Item;

import cn.edu.lingnan.pojo.Item;
import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.imp.ItemServiceImpMysql;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/queryItemAll")
public class ItemQueryAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取页面参数
        //2.调用业务逻辑层完成数据库的查询操作
        List<Item> list = new ItemServiceImpMysql().queryAllItem();
        req.getSession().setAttribute("allItem",list);
        //3.页面跳转
        resp.sendRedirect(req.getContextPath()+"/admin/allItem.jsp");
    }
}

