package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.Department;
import cn.edu.lingnan.service.DepartmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DepartmentController - 院系管理控制器
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

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
     * 插入院系
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insertDepartment(@ModelAttribute Department department) {
        try {
            int result = departmentService.insertDepartment(department);
            if (result > 0) {
                return "{\"status\":\"success\"}";
            } else {
                return "{\"status\":\"error\",\"message\":\"插入失败\"}";
            }
        } catch (Exception e) {
            return "{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}";
        }
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

