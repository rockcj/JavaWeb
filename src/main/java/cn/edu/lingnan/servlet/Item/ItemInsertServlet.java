package cn.edu.lingnan.servlet.Item;

import cn.edu.lingnan.pojo.Item;
import cn.edu.lingnan.service.imp.ItemServiceImpMysql;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/insertItem")
public class ItemInsertServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            ObjectMapper mapper = new ObjectMapper();
            List<Item> items = mapper.readValue(sb.toString(),
                    mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
            ItemServiceImpMysql itemService = new ItemServiceImpMysql();
            for (Item item : items) {
                itemService.insertItem(item); // 批量插入
            }
            // 设置正确的 Content-Type
            resp.setContentType("application/json;charset=UTF-8");
            // 返回标准 JSON 响应
            //,"data":{}
            resp.getWriter().write("{\"status\":\"success\"}");
        } catch (Exception e) {
            resp.setContentType("application/json;charset=UTF-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        }
    }
}
