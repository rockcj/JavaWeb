package cn.edu.lingnan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * HelloController - Spring MVC基础控制器
 * 实现"Hello Spring MVC"字符输出
 */
@Controller
public class HelloController {

    /**
     * 处理/hello请求，返回Hello Spring MVC
     * @return ModelAndView对象，包含视图名称和数据
     */
    @RequestMapping("/hello")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView();
        // 设置视图名称（视图解析器会自动添加前缀/WEB-INF/views/和后缀.jsp）
        mv.setViewName("hello");
        // 添加数据到Model
        mv.addObject("message", "Hello Spring MVC");
        return mv;
    }
}

