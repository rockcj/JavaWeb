package cn.edu.lingnan.controller;

import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LoginController - 登录和注册控制器
 * 迁移自LoginServlet和Register
 */
@Controller
public class LoginController {
    
    @Autowired
    private StudentService studentService;

    /**
     * 登录处理
     * 对应原：LoginServlet
     */
    @RequestMapping(value = {"/login", "/error"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String login(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       HttpSession session) {
        System.out.println("username:" + username + "  password:" + password);
        
        // 调用业务逻辑层完成数据库的查询操作
        Student stu = studentService.queryStudentByNameAndPassword(username, password);
        
        if (stu != null) {
            // 设置session，记录会话
            session.setAttribute("sright", stu.getSright());
            session.setAttribute("stflag", stu.getStflag());
            session.setAttribute("user", stu.getSright() == 1 ? username + "管理员" : username + "用户");
            session.setAttribute("flag", stu.getStflag() == 1 ? "正常" : "审核未通过");
            System.out.println("登录成功");
            
            if (stu.getSright() == 1 || stu.getStflag() != 1) {
                return "redirect:/main.jsp";
            } else {
                session.setAttribute("sid", stu.getSid());
                return "redirect:/student/one";
            }
        } else {
            System.out.println("登录失败");
            return "redirect:/loginError.html";
        }
    }

    /**
     * 显示注册页面
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister() {
        // 返回register.jsp（在web根目录下）
        return "register";
    }

    /**
     * 注册处理
     * 对应原：Register
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("userId") String userId,
                          @RequestParam("username") String username,
                          @RequestParam("OKPassword") String password,
                          Model model) {
        System.out.println("username:" + username + "  password:" + password);
        
        // 检查ID是否已被注册
        Student stu = studentService.queryStudentById(userId);
        if (stu != null) {
            System.out.println("该id已被注册，请重新输入");
            model.addAttribute("errorId", "该id已被注册，请重新输入");
            return "register";
        }
        
        // 检查用户名是否已存在
        List<Student> stu1 = studentService.queryStudentByName(username);
        if (stu1.size() == 0) {
            Student student = new Student(userId, username, password, 0, 0);
            studentService.insertStudent(student);
            return "redirect:/Login.html";
        } else {
            System.out.println("该用户名已存在，请重新输入");
            model.addAttribute("errorName", "该用户名已存在，请重新输入");
            return "register";
        }
    }

    /**
     * 登出处理
     * 对应原：LoginOutServlet
     */
    @RequestMapping("/loginout")
    public String loginout(HttpSession session) {
        // 销毁session
        session.invalidate();
        // 重定向到登录页面
        return "redirect:/Login.html";
    }
}

