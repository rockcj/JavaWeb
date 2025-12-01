package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.Department;
import cn.edu.lingnan.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DepartmentController - 院系管理控制器
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    /**
     * 显示添加院系页面
     */
    @RequestMapping(value = "/addDepartment", method = RequestMethod.GET)
    public String showAddDepartment() {
        return "redirect:/admin/addDepartment.jsp";
    }

    /**
     * 查询所有院系
     */
    @RequestMapping("/queryAll")
    public String queryAll(HttpSession session, Model model) {
        List<Department> list = departmentService.queryAllDepartment();
        session.setAttribute("allDepartment", list);
        model.addAttribute("allDepartment", list);
        return "admin/allDepartment";
    }

    /**
     * 根据ID查询院系
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public Department queryById(@RequestParam("deptId") String deptId) {
        return departmentService.queryDepartmentById(deptId);
    }

    /**
     * 根据名称查询院系
     */
    @RequestMapping("/queryByName")
    public String queryByName(@RequestParam("deptName") String deptName, Model model) {
        List<Department> list = departmentService.queryDepartmentByName(deptName);
        model.addAttribute("allDepartment", list);
        return "admin/allDepartment";
    }

    /**
     * 插入院系（批量）
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertDepartment(@RequestBody String jsonData) {
        Map<String, Object> result = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Department> departments = mapper.readValue(jsonData,
                    mapper.getTypeFactory().constructCollectionType(List.class, Department.class));
            for (Department department : departments) {
                departmentService.insertDepartment(department);
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
     * 显示编辑院系页面
     */
    @RequestMapping("/showUpdate")
    @SuppressWarnings("unchecked")
    public String showUpdate(@RequestParam("deptId") String deptId, HttpSession session, Model model) {
        // 确保session中有allDepartment列表
        List<Department> list = (List<Department>) session.getAttribute("allDepartment");
        if (list == null) {
            list = departmentService.queryAllDepartment();
            session.setAttribute("allDepartment", list);
        }
        model.addAttribute("allDepartment", list);
        return "admin/updateDepartment";
    }

    /**
     * 更新院系信息
     */
    @RequestMapping("/update")
    public String updateDepartment(@ModelAttribute Department department) {
        departmentService.updateDepartment(department);
        return "redirect:/department/queryAll";
    }

    /**
     * 删除院系
     */
    @RequestMapping("/delete")
    public String deleteDepartment(@RequestParam("deptId") String deptId) {
        departmentService.deleteDepartmentById(deptId);
        return "redirect:/department/queryAll";
    }

    /**
     * 修改院系状态
     */
    @RequestMapping("/editFlag")
    public String editFlag(@RequestParam("deptId") String deptId,
                           @RequestParam("dflag") int dflag) {
        departmentService.editDepartmentFlag(deptId, dflag);
        return "redirect:/department/queryAll";
    }
}

