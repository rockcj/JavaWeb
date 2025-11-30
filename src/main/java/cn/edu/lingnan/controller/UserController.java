package cn.edu.lingnan.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserController - 自定义Controller（注解方式）
 * 演示3种接收数据方式、3种传递数据方式、3种页面跳转方式
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 方式1：使用@RequestParam接收请求参数
     * 方式2：使用HttpServletRequest接收参数
     * 方式3：使用@PathVariable接收路径变量
     */
    
    /**
     * 方式1：使用@RequestParam接收数据
     * 访问：/user/add?name=张三&age=20
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addUser1(@RequestParam("name") String name, 
                          @RequestParam("age") Integer age) {
        return "方式1-@RequestParam接收：姓名=" + name + ", 年龄=" + age;
    }

    /**
     * 方式2：使用HttpServletRequest接收数据
     * 访问：/user/update?name=李四&age=25
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser2(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        return "方式2-HttpServletRequest接收：姓名=" + name + ", 年龄=" + age;
    }

    /**
     * 方式3：使用@PathVariable接收路径变量
     * 访问：/user/delete/1001
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteUser3(@PathVariable("id") String id) {
        return "方式3-@PathVariable接收：用户ID=" + id;
    }

    /**
     * 方式4：使用@ModelAttribute接收对象
     * 访问：/user/save?name=王五&age=30&email=wangwu@example.com
     */
    @RequestMapping("/save")
    @ResponseBody
    public String saveUser4(@ModelAttribute User user) {
        return "方式4-@ModelAttribute接收：用户信息=" + user.toString();
    }

    /**
     * 用户表单页面
     */
    @RequestMapping("/form")
    public String showForm() {
        return "userForm";
    }

    /**
     * 处理表单提交 - 演示3种传递数据方式
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitForm(@RequestParam("name") String name,
                           @RequestParam("age") Integer age,
                           Model model,
                           HttpSession session) {
        
        // 方式1：使用Model传递数据
        model.addAttribute("message", "方式1-Model传递：表单提交成功！");
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        
        // 方式2：使用ModelAndView传递数据（在方法中创建）
        // 这里使用Model，等价于ModelAndView
        
        // 方式3：使用HttpSession传递数据
        session.setAttribute("sessionMessage", "方式3-HttpSession传递：数据已保存到Session");
        session.setAttribute("userName", name);
        
        // 方式4：使用Model传递对象
        User user = new User();
        user.setName(name);
        user.setAge(age);
        model.addAttribute("user", user);
        
        // 方式5：使用Model传递集合
        List<String> hobbies = new ArrayList<>();
        hobbies.add("阅读");
        hobbies.add("运动");
        hobbies.add("编程");
        model.addAttribute("hobbies", hobbies);
        
        // 方式6：使用Model传递Map
        Map<String, Object> info = new HashMap<>();
        info.put("注册时间", "2024-01-01");
        info.put("状态", "正常");
        model.addAttribute("info", info);
        
        return "userResult";
    }

    /**
     * 用户列表页面 - 演示3种页面跳转方式
     */
    @RequestMapping("/list")
    public ModelAndView userList() {
        ModelAndView mv = new ModelAndView();
        List<User> users = new ArrayList<>();
        users.add(new User("张三", 20, "zhangsan@example.com"));
        users.add(new User("李四", 25, "lisi@example.com"));
        users.add(new User("王五", 30, "wangwu@example.com"));
        
        mv.addObject("users", users);
        mv.setViewName("userList");
        return mv;
    }

    /**
     * 方式1：使用ModelAndView进行页面跳转（带数据）
     * 访问：/user/detail/1
     */
    @RequestMapping("/detail/{id}")
    public ModelAndView detail1(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "方式1-ModelAndView跳转：查看用户详情，ID=" + id);
        mv.setViewName("userDetail");
        return mv;
    }

    /**
     * 方式2：使用String返回视图名称进行页面跳转
     * 访问：/user/edit/2
     */
    @RequestMapping("/edit/{id}")
    public String edit2(@PathVariable("id") String id, Model model) {
        model.addAttribute("message", "方式2-String视图名跳转：编辑用户，ID=" + id);
        return "userEdit";
    }

    /**
     * 方式3：使用redirect重定向跳转
     * 访问：/user/redirect
     */
    @RequestMapping("/redirect")
    public String redirect3() {
        return "redirect:/user/list";
    }

    /**
     * 方式4：使用forward转发跳转
     * 访问：/user/forward
     */
    @RequestMapping("/forward")
    public String forward4() {
        return "forward:/user/list";
    }

    /**
     * 用户实体类（内部类）
     */
    public static class User {
        private String name;
        private Integer age;
        private String email;

        public User() {
        }

        public User(String name, Integer age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + ", email='" + email + "'}";
        }
    }
}

