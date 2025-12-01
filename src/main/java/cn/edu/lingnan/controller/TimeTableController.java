package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.TimeTable;
import cn.edu.lingnan.service.TimeTableService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TimeTableController - 课程表管理控制器
 */
@Controller
@RequestMapping("/timetable")
public class TimeTableController {
    
    @Autowired
    private TimeTableService timeTableService;

    /**
     * 查询所有课程表
     */
    @RequestMapping("/queryAll")
    public String queryAll(HttpSession session, Model model) {
        List<TimeTable> list = timeTableService.queryAllTimeTables();
        session.setAttribute("allTimeTable", list);
        model.addAttribute("allTimeTable", list);
        return "admin/allTimeTable";
    }

    /**
     * 根据ID查询课程表
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public TimeTable queryById(@RequestParam("timetableId") String timetableId) {
        return timeTableService.queryTimeTableById(timetableId);
    }

    /**
     * 根据课程ID查询课程表
     */
    @RequestMapping("/queryByCourseId")
    public String queryByCourseId(@RequestParam("courseId") String courseId, Model model) {
        List<TimeTable> list = timeTableService.queryTimeTableByCourseId(courseId);
        model.addAttribute("allTimeTable", list);
        return "admin/allTimeTable";
    }

    /**
     * 根据教师ID查询课程表
     */
    @RequestMapping("/queryByTeacherId")
    public String queryByTeacherId(@RequestParam("teacherId") String teacherId, Model model) {
        List<TimeTable> list = timeTableService.queryTimeTableByTeacherId(teacherId);
        model.addAttribute("allTimeTable", list);
        return "admin/allTimeTable";
    }

    /**
     * 根据学期查询课程表
     */
    @RequestMapping("/queryBySemester")
    public String queryBySemester(@RequestParam("semester") String semester, Model model) {
        List<TimeTable> list = timeTableService.queryTimeTableBySemester(semester);
        model.addAttribute("allTimeTable", list);
        return "admin/allTimeTable";
    }

    /**
     * 插入课程表
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertTimeTable(@ModelAttribute TimeTable timeTable) {
        Map<String, Object> result = new HashMap<>();
        try {
            int insertResult = timeTableService.insertTimeTable(timeTable);
            if (insertResult > 0) {
                result.put("status", "success");
            } else {
                result.put("status", "error");
                result.put("message", "插入失败");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        return result;
    }

    /**
     * 显示编辑课程表页面
     */
    @RequestMapping("/showUpdate")
    @SuppressWarnings("unchecked")
    public String showUpdate(@RequestParam("timetableId") String timetableId, HttpSession session, Model model) {
        // 确保session中有allTimeTable列表
        List<TimeTable> list = (List<TimeTable>) session.getAttribute("allTimeTable");
        if (list == null) {
            list = timeTableService.queryAllTimeTables();
            session.setAttribute("allTimeTable", list);
        }
        model.addAttribute("allTimeTable", list);
        return "admin/updateTimeTable";
    }

    /**
     * 更新课程表信息
     */
    @RequestMapping("/update")
    public String updateTimeTable(@ModelAttribute TimeTable timeTable) {
        timeTableService.updateTimeTable(timeTable);
        return "redirect:/timetable/queryAll";
    }

    /**
     * 删除课程表
     */
    @RequestMapping("/delete")
    public String deleteTimeTable(@RequestParam("timetableId") String timetableId) {
        timeTableService.deleteTimeTable(timetableId);
        return "redirect:/timetable/queryAll";
    }
}

