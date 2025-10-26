package cn.edu.lingnan.servlet.Student;


import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/insertStudent")
public class StudentInsertServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);// 将读取到的行追加到 StringBuilder 中
            }

            ObjectMapper mapper = new ObjectMapper();//  创建 ObjectMapper 对象
            List<Student> Students = mapper.readValue(sb.toString(),
                    mapper.getTypeFactory().constructCollectionType(List.class, Student.class));//  将 JSON 转换为 List<Item>
            StudentServiceImpMysql  studentService = new StudentServiceImpMysql();
            for (Student stu : Students) {
                studentService.insertStudent(stu);
            }
            // 设置正确的 Content-Type
            resp.setContentType("application/json;charset=UTF-8");
            // 返回标准 JSON 响应
            //,"data":{}
            resp.getWriter().write("{\"status\":\"success\"}");
        } catch (Exception e) {
            resp.setContentType("application/json;charset=UTF-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        }
    }
}
