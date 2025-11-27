package cn.edu.lingnan.test;

import cn.edu.lingnan.pojo.Course;
import cn.edu.lingnan.pojo.Department;
import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.CourseService;
import cn.edu.lingnan.service.DepartmentService;
import cn.edu.lingnan.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringIntegrationTest {

    private ApplicationContext context;
    private StudentService studentService;
    private CourseService courseService;
    private DepartmentService departmentService;

    @Before
    public void setUp() {
        // 加载Spring配置文件
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        studentService = (StudentService) context.getBean("studentService");
        courseService = (CourseService) context.getBean("courseService");
        departmentService = (DepartmentService) context.getBean("departmentService");
    }

    @Test
    public void testStudentModuleWithPropertyInjection() {
        System.out.println("========== 测试Student模块（Property注入） ==========");

        // 测试查询所有学生
        System.out.println("--- 测试查询所有学生 ---");
        List<Student> students = studentService.queryAllStudentAll();
        System.out.println("查询到的学生数量: " + students.size());

        if (!students.isEmpty()) {
            Student firstStudent = students.get(0);
            System.out.println("第一个学生: " + firstStudent.getSname() + " (ID: " + firstStudent.getSid() + ")");
        }

        // 测试根据ID查询
        if (!students.isEmpty()) {
            String studentId = students.get(0).getSid();
            System.out.println("--- 测试根据ID查询学生: " + studentId + " ---");
            Student student = studentService.queryStudentById(studentId);
            System.out.println("查询结果: " + (student != null ? student.getSname() : "未找到"));
        }
    }

    @Test
    public void testCourseModuleWithAutowired() {
        System.out.println("\n========== 测试Course模块（自动装配） ==========");

        // 测试查询所有课程
        System.out.println("--- 测试查询所有课程 ---");
        List<Course> courses = courseService.queryAllCourse();
        System.out.println("查询到的课程数量: " + courses.size());

        if (!courses.isEmpty()) {
            Course firstCourse = courses.get(0);
            System.out.println("第一门课程: " + firstCourse.getCourseName() + " (ID: " + firstCourse.getCourseId() + ")");
        }

        // 测试根据课程名称查询
        if (!courses.isEmpty()) {
            String courseName = courses.get(0).getCourseName();
            System.out.println("--- 测试根据课程名称查询: " + courseName + " ---");
            List<Course> coursesByName = courseService.queryCourseByName(courseName);
            System.out.println("查询结果数量: " + coursesByName.size());
        }
    }

    @Test
    public void testDepartmentModule() {
        System.out.println("\n========== 测试Department模块 ==========");

        // 测试查询所有院系
        System.out.println("--- 测试查询所有院系 ---");
        List<Department> departments = departmentService.queryAllDepartment();
        System.out.println("查询到的院系数量: " + departments.size());

        if (!departments.isEmpty()) {
            Department firstDept = departments.get(0);
            System.out.println("第一个院系: " + firstDept.getDeptName() + " (ID: " + firstDept.getDeptId() + ")");
        }
    }

    @Test
    public void testAopLogging() {
        System.out.println("\n========== 测试AOP日志功能 ==========");

        System.out.println("执行Service方法时，您应该能看到AOP切面输出的日志信息");

        // 执行几个不同的操作来触发AOP日志
        System.out.println("1. 执行查询操作（会触发AOP日志）:");
        List<Student> students = studentService.queryAllStudentAll();

        System.out.println("\n2. 执行另一个查询操作:");
        List<Course> courses = courseService.queryAllCourse();

        System.out.println("\n3. 如果执行插入/更新/删除操作，也会触发AOP日志");
        System.out.println("(由于测试数据安全，这里不执行写操作)");
    }
}