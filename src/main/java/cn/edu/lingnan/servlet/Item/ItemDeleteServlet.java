package cn.edu.lingnan.servlet.Item;

import cn.edu.lingnan.service.ItemService;
import cn.edu.lingnan.service.imp.ItemServiceImpMysql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteItem")
public class ItemDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 从页面获取表单请求参数
        String iid = req.getParameter("iid");
        String flag = req.getParameter("flag");
        System.out.println("删除的项目id为" + iid);
        //2. 调用业务逻辑层完成数据库的增删查改操作
        ItemService itemService = new ItemServiceImpMysql();
        //批量删除和单个删除
        if(flag!=null)//多
        {
            String iids [] = iid.split(",");
            for(String iid1:iids)
            {
                itemService.deleteItemById(iid1);
            }
        }else//单
        {
            itemService.deleteItemById(iid);
        }
        resp.sendRedirect(req.getContextPath()+"/queryItemAll");
    }
}
