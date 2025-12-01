package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.StudentService;
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
 * StudentController - 学生管理控制器
 * 迁移自Student相关的Servlet
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    /**
     * 查询所有学生
     * 对应原：StudentQueryAllServlet
     */
    @RequestMapping("/queryAll")
    public String queryAll(@RequestParam(value = "flag", required = false) String flag, 
                          Model model, HttpSession session) {
        List<Student> list;
        if (flag == null) {
            list = studentService.queryAllStudentAll();
        } else {
            int Flag = Integer.parseInt(flag);
            list = studentService.queryAllStudentAll(Flag);
            session.setAttribute("ok", Flag);
        }
        session.setAttribute("allStu", list);
        return "redirect:/admin/allStu.jsp";
    }

    /**
     * 查询单个学生信息
     * 对应原：OneStudent
     */
    @RequestMapping("/one")
    public String queryOne(HttpSession session, Model model) {
        if (session != null) {
            String sid = (String) session.getAttribute("sid");
            if (sid != null) {
                Student stu = studentService.queryStudentById(sid);
                session.setAttribute("stu", stu);
                return "redirect:/USER/Stu.jsp";
            }
        }
        return "redirect:/Login.html";
    }

    /**
     * 插入学生（批量）
     * 对应原：StudentInsertServlet
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertStudent(@RequestBody String jsonData) {
        Map<String, Object> result = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Student> students = mapper.readValue(jsonData,
                    mapper.getTypeFactory().constructCollectionType(List.class, Student.class));
            for (Student stu : students) {
                studentService.insertStudent(stu);
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
     * 更新学生信息
     * 对应原：StudentUpdateServlet
     */
    @RequestMapping("/update")
    public String updateStudent(@RequestParam("sid") String sid,
                               @RequestParam("sname") String sname,
                               @RequestParam("spassword") String spassword,
                               @RequestParam("sright") int sright,
                               @RequestParam("stflag") int stflag) {
        Student student = new Student(sid, sname, spassword, sright, stflag);
        studentService.updateStudent(student);
        return "redirect:/student/queryAll";
    }

    /**
     * 删除学生（支持单个和批量）
     * 对应原：StudentDeleteServlet
     */
    @RequestMapping("/delete")
    public String deleteStudent(@RequestParam("sid") String sid,
                               @RequestParam(value = "flag", required = false) String flag) {
        System.out.println("删除的学生id为" + sid);
        if (flag != null) {
            // 批量删除
            String[] sids = sid.split(",");
            for (String sid1 : sids) {
                studentService.deleteStudentById(sid1);
            }
        } else {
            // 单个删除
            studentService.deleteStudentById(sid);
        }
        return "redirect:/student/queryAll?flag=1";
    }

    /**
     * 编辑学生密码（保留原有功能）
     */
    public void editStudentPassword() {
        // 保留原有控制台输入功能，用于测试
    }

    /**
     * 编辑学生姓名（保留原有功能）
     */
    public void editStudentName() {
        // 保留原有控制台输入功能，用于测试
    }

    /**
     * 审核通过学生（单个或批量）
     * 对应原：Check Servlet
     */
    @RequestMapping("/check")
    public String checkStudent(@RequestParam("sid") String sid,
                              @RequestParam(value = "count", required = false) String count) {
        if (count != null) {
            // 批量审核通过
            String[] sids = sid.split(",");
            for (String sid1 : sids) {
                studentService.editStudentFlag(sid1, 1);
            }
        } else {
            // 单个审核通过
            studentService.editStudentFlag(sid, 1);
        }
        return "redirect:/student/queryAll?flag=1";
    }
}
