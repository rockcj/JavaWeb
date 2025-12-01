package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.Score;
import cn.edu.lingnan.service.ScoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ScoreController - 成绩管理控制器
 */
@Controller
@RequestMapping("/score")
public class ScoreController {
    
    @Autowired
    private ScoreService scoreService;

    /**
     * 查询所有成绩
     */
    @RequestMapping("/queryAll")
    public String queryAll(HttpSession session, Model model) {
        List<Score> list = scoreService.queryAllScores();
        session.setAttribute("allScore", list);
        model.addAttribute("allScore", list);
        return "admin/allScore";
    }

    /**
     * 根据ID查询成绩
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public Score queryById(@RequestParam("scoreId") String scoreId) {
        return scoreService.queryScoreById(scoreId);
    }

    /**
     * 根据学生ID查询成绩
     */
    @RequestMapping("/queryByStudentId")
    public String queryByStudentId(@RequestParam("studentId") String studentId, Model model) {
        List<Score> list = scoreService.queryScoresByStudentId(studentId);
        model.addAttribute("allScore", list);
        return "admin/allScore";
    }

    /**
     * 根据课程ID查询成绩
     */
    @RequestMapping("/queryByCourseId")
    public String queryByCourseId(@RequestParam("courseId") String courseId, Model model) {
        List<Score> list = scoreService.queryScoresByCourseId(courseId);
        model.addAttribute("allScore", list);
        return "admin/allScore";
    }

    /**
     * 插入成绩
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insertScore(@ModelAttribute Score score) {
        try {
            int result = scoreService.insertScore(score);
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
     * 批量插入成绩
     */
    @RequestMapping(value = "/batchInsert", method = RequestMethod.POST)
    @ResponseBody
    public String batchInsertScore(@RequestBody List<Score> scores) {
        try {
            int result = scoreService.batchInsertScores(scores);
            if (result > 0) {
                return "{\"status\":\"success\",\"count\":" + result + "}";
            } else {
                return "{\"status\":\"error\",\"message\":\"批量插入失败\"}";
            }
        } catch (Exception e) {
            return "{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}";
        }
    }

    /**
     * 显示编辑成绩页面
     */
    @RequestMapping("/showUpdate")
    @SuppressWarnings("unchecked")
    public String showUpdate(@RequestParam("scoreId") String scoreId, HttpSession session, Model model) {
        // 确保session中有allScore列表
        List<Score> list = (List<Score>) session.getAttribute("allScore");
        if (list == null) {
            list = scoreService.queryAllScores();
            session.setAttribute("allScore", list);
        }
        model.addAttribute("allScore", list);
        return "admin/updateScore";
    }

    /**
     * 更新成绩信息
     */
    @RequestMapping("/update")
    public String updateScore(@ModelAttribute Score score) {
        scoreService.updateScore(score);
        return "redirect:/score/queryAll";
    }

    /**
     * 删除成绩
     */
    @RequestMapping("/delete")
    public String deleteScore(@RequestParam("scoreId") String scoreId) {
        scoreService.deleteScore(scoreId);
        return "redirect:/score/queryAll";
    }
}

