package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.Score;
import cn.edu.lingnan.pojo.TimeTable;
import cn.edu.lingnan.service.ScoreService;
import cn.edu.lingnan.service.TimeTableService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * UserController - 用户端控制器
 * 处理学生用户查看自己相关信息的请求
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private ScoreService scoreService;
    
    @Autowired
    private TimeTableService timeTableService;
    
    /**
     * 查询学生自己的成绩信息
     */
    @RequestMapping(value = "/myScores", method = {RequestMethod.GET, RequestMethod.POST})
    public String queryMyScores(HttpSession session, Model model) {
        System.out.println("UserController.queryMyScores 被调用");
        if (session != null) {
            String sid = (String) session.getAttribute("sid");
            System.out.println("Session中的sid: " + sid);
            if (sid != null) {
                List<Score> list = scoreService.queryScoresByStudentId(sid);
                session.setAttribute("myScores", list);
                model.addAttribute("myScores", list);
                System.out.println("查询到成绩信息，共 " + (list != null ? list.size() : 0) + " 条记录");
                return "USER/myScores";
            } else {
                System.out.println("Session中没有sid");
            }
        } else {
            System.out.println("Session为null");
        }
        return "redirect:/Login.html";
    }
    
    /**
     * 查询学生自己的课程表信息
     * 根据学生的成绩记录（选课记录）查询对应的课程表
     */
    @RequestMapping(value = "/myTimeTable", method = {RequestMethod.GET, RequestMethod.POST})
    public String queryMyTimeTable(HttpSession session, Model model) {
        System.out.println("UserController.queryMyTimeTable 被调用");
        if (session != null) {
            String sid = (String) session.getAttribute("sid");
            System.out.println("Session中的sid: " + sid);
            if (sid != null) {
                // 先查询学生的成绩记录（选课记录），获取课程ID列表
                List<Score> scores = scoreService.queryScoresByStudentId(sid);
                java.util.ArrayList<TimeTable> allTimeTables = new java.util.ArrayList<>();
                
                if (scores != null && scores.size() > 0) {
                    // 遍历学生的选课记录，查询每门课程的课程表
                    for (Score score : scores) {
                        if (score.getCourseId() != null) {
                            List<TimeTable> courseTimeTables = timeTableService.queryTimeTableByCourseId(score.getCourseId());
                            if (courseTimeTables != null) {
                                allTimeTables.addAll(courseTimeTables);
                            }
                        }
                    }
                }
                
                session.setAttribute("myTimeTable", allTimeTables);
                model.addAttribute("myTimeTable", allTimeTables);
                System.out.println("查询到课程表信息，共 " + allTimeTables.size() + " 条记录");
                return "USER/myTimeTable";
            } else {
                System.out.println("Session中没有sid");
            }
        } else {
            System.out.println("Session为null");
        }
        return "redirect:/Login.html";
    }
}
