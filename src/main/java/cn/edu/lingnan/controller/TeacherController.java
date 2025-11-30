package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.Teacher;
import cn.edu.lingnan.service.TeacherService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TeacherController - 教师管理控制器
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    
    @Autowired
    private TeacherService teacherService;

    /**
     * 查询所有教师
     */
    @RequestMapping("/queryAll")
    public String queryAll(HttpSession session, Model model) {
        List<Teacher> list = teacherService.queryAllTeachers();
        session.setAttribute("allTeacher", list);
        model.addAttribute("allTeacher", list);
        return "admin/allTeacher";
    }

    /**
     * 根据ID查询教师
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public Teacher queryById(@RequestParam("teacherId") String teacherId) {
        return teacherService.queryTeacherById(teacherId);
    }

    /**
     * 根据姓名查询教师
     */
    @RequestMapping("/queryByName")
    public String queryByName(@RequestParam("teacherName") String teacherName, Model model) {
        List<Teacher> list = teacherService.queryTeacherByName(teacherName);
        model.addAttribute("allTeacher", list);
        return "admin/allTeacher";
    }

    /**
     * 根据院系ID查询教师
     */
    @RequestMapping("/queryByDeptId")
    public String queryByDeptId(@RequestParam("deptId") String deptId, Model model) {
        List<Teacher> list = teacherService.queryTeachersByDeptId(deptId);
        model.addAttribute("allTeacher", list);
        return "admin/allTeacher";
    }

    /**
     * 插入教师
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insertTeacher(@ModelAttribute Teacher teacher) {
        try {
            int result = teacherService.insertTeacher(teacher);
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
     * 更新教师信息
     */
    @RequestMapping("/update")
    public String updateTeacher(@ModelAttribute Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return "redirect:/teacher/queryAll";
    }

    /**
     * 删除教师
     */
    @RequestMapping("/delete")
    public String deleteTeacher(@RequestParam("teacherId") String teacherId) {
        teacherService.deleteTeacher(teacherId);
        return "redirect:/teacher/queryAll";
    }
}

