package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.Course;
import cn.edu.lingnan.service.CourseService;
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
 * CourseController - 课程管理控制器
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    
    @Autowired
    private CourseService courseService;

    /**
     * 显示添加课程页面
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public String showAddCourse() {
        return "redirect:/admin/addCourse.jsp";
    }

    /**
     * 查询所有课程
     */
    @RequestMapping("/queryAll")
    public String queryAll(HttpSession session, Model model) {
        List<Course> list = courseService.queryAllCourse();
        session.setAttribute("allCourse", list);
        model.addAttribute("allCourse", list);
        return "admin/allCourse";
    }

    /**
     * 根据ID查询课程
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public Course queryById(@RequestParam("courseId") String courseId) {
        return courseService.queryCourseById(courseId);
    }

    /**
     * 根据名称查询课程
     */
    @RequestMapping("/queryByName")
    public String queryByName(@RequestParam("courseName") String courseName, Model model) {
        List<Course> list = courseService.queryCourseByName(courseName);
        model.addAttribute("allCourse", list);
        return "admin/allCourse";
    }

    /**
     * 插入课程（批量）
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertCourse(@RequestBody String jsonData) {
        Map<String, Object> result = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Course> courses = mapper.readValue(jsonData,
                    mapper.getTypeFactory().constructCollectionType(List.class, Course.class));
            for (Course course : courses) {
                courseService.insertCourse(course);
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
     * 显示编辑课程页面
     */
    @RequestMapping("/showUpdate")
    @SuppressWarnings("unchecked")
    public String showUpdate(@RequestParam("courseId") String courseId, HttpSession session, Model model) {
        // 确保session中有allCourse列表
        List<Course> list = (List<Course>) session.getAttribute("allCourse");
        if (list == null) {
            list = courseService.queryAllCourse();
            session.setAttribute("allCourse", list);
        }
        model.addAttribute("allCourse", list);
        return "admin/updateCourse";
    }

    /**
     * 更新课程信息
     */
    @RequestMapping("/update")
    public String updateCourse(@ModelAttribute Course course) {
        courseService.updateCourse(course);
        return "redirect:/course/queryAll";
    }

    /**
     * 删除课程
     */
    @RequestMapping("/delete")
    public String deleteCourse(@RequestParam("courseId") String courseId) {
        courseService.deleteCourseById(courseId);
        return "redirect:/course/queryAll";
    }

    /**
     * 修改课程状态
     */
    @RequestMapping("/editFlag")
    public String editFlag(@RequestParam("courseId") String courseId,
                          @RequestParam("cflag") int cflag) {
        courseService.editCourseFlag(courseId, cflag);
        return "redirect:/course/queryAll";
    }
}

