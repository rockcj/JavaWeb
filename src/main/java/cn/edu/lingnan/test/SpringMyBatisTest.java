package cn.edu.lingnan.test;

import cn.edu.lingnan.pojo.Course;
import cn.edu.lingnan.pojo.Score;
import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.CourseService;
import cn.edu.lingnan.service.ScoreService;
import cn.edu.lingnan.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

public class SpringMyBatisTest {

    public static void main(String[] args) {
        // 加载Spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 测试Student模块（property注入）
        System.out.println("========== 测试Student模块（property注入） ==========");
        testStudentModule(context);

        // 测试Course模块（自动装配）
        System.out.println("\n========== 测试Course模块（自动装配） ==========");
        testCourseModule(context);

        // 测试Score模块（注解配置）
        System.out.println("\n========== 测试Score模块（注解配置） ==========");
        testScoreModule(context);
    }

    private static void testStudentModule(ApplicationContext context) {
        StudentService studentService = (StudentService) context.getBean("studentService");

        System.out.println("--- 查询所有学生 ---");
        List<Student> students = studentService.queryAllStudentAll();
        System.out.println("找到学生数量: " + students.size());

        if (!students.isEmpty()) {
            Student firstStudent = students.get(0);
            System.out.println("第一个学生信息: " + firstStudent);

            System.out.println("--- 根据ID查询学生 ---");
            Student student = studentService.queryStudentById(firstStudent.getSid());
            System.out.println("查询结果: " + student);
        }
    }

    private static void testCourseModule(ApplicationContext context) {
        CourseService courseService = (CourseService) context.getBean("courseService");

        System.out.println("--- 查询所有课程 ---");
        List<Course> courses = courseService.queryAllCourse();
        System.out.println("找到课程数量: " + courses.size());

        if (!courses.isEmpty()) {
            Course firstCourse = courses.get(0);
            System.out.println("第一个课程信息: " + firstCourse);

            System.out.println("--- 根据ID查询课程 ---");
            Course course = courseService.queryCourseById(firstCourse.getCourseId());
            System.out.println("查询结果: " + course);
        }
    }

    private static void testScoreModule(ApplicationContext context) {
        ScoreService scoreService = (ScoreService) context.getBean("scoreService");

        System.out.println("--- 查询所有成绩 ---");
        try {
            List<Score> scores = scoreService.queryAllScores();
            System.out.println("找到成绩数量: " + scores.size());

            // 插入测试成绩
            System.out.println("--- 插入测试成绩 ---");
            Score testScore = new Score();
            testScore.setScoreId("TEST001");
            testScore.setStudentId("S001");
            testScore.setCourseId("C001");
            testScore.setScore(85.5);
            testScore.setSemester("2024春季");
            testScore.setExamType("期中");

            int result = scoreService.insertScore(testScore);
            System.out.println("插入结果: " + (result > 0 ? "成功" : "失败"));

            // 查询刚插入的成绩
            System.out.println("--- 根据ID查询成绩 ---");
            Score insertedScore = scoreService.queryScoreById("TEST001");
            System.out.println("查询结果: " + insertedScore);

            // 删除测试成绩
            if (insertedScore != null) {
                System.out.println("--- 删除测试成绩 ---");
                int deleteResult = scoreService.deleteScore("TEST001");
                System.out.println("删除结果: " + (deleteResult > 0 ? "成功" : "失败"));
            }
        } catch (Exception e) {
            System.out.println("测试Score模块时出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
}