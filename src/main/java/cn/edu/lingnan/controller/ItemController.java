package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.Item;
import cn.edu.lingnan.service.ItemService;
import cn.edu.lingnan.service.imp.ItemServiceImpMysql;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ItemController - 项目/课程项管理控制器
 * 迁移自Item相关的Servlet
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    /**
     * 查询所有项目
     * 对应原：ItemQueryAllServlet
     */
    @RequestMapping("/queryAll")
    public String queryAll(HttpSession session) {
        List<Item> list = itemService.queryAllItem();
        session.setAttribute("allItem", list);
        return "redirect:/admin/allItem.jsp";
    }

    /**
     * 插入项目（批量）
     * 对应原：ItemInsertServlet
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertItem(@RequestBody String jsonData) {
        Map<String, Object> result = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Item> items = mapper.readValue(jsonData,
                    mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
            for (Item item : items) {
                itemService.insertItem(item);
            }
            result.put("status", "success");
            return result;
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
            return result;
        }
    }

    /**
     * 更新项目信息
     * 对应原：ItemUpdateServlet
     */
    @RequestMapping("/update")
    public String updateItem(@RequestParam("iid") String iid,
                            @RequestParam("iname") String iname,
                            @RequestParam("iflag") int iflag) {
        itemService.editItem(iid, iname, iflag);
        return "redirect:/item/queryAll";
    }

    /**
     * 删除项目（支持单个和批量）
     * 对应原：ItemDeleteServlet
     */
    @RequestMapping("/delete")
    public String deleteItem(@RequestParam("iid") String iid,
                            @RequestParam(value = "flag", required = false) String flag) {
        System.out.println("删除的项目id为" + iid);
        if (flag != null) {
            // 批量删除
            String[] iids = iid.split(",");
            for (String iid1 : iids) {
                itemService.deleteItemById(iid1);
            }
        } else {
            // 单个删除
            itemService.deleteItemById(iid);
        }
        return "redirect:/item/queryAll";
    }

    /**
     * 编辑项目（保留原有功能）
     */
    public void editItemController() {
        // 保留原有控制台输入功能，用于测试
    }
}
