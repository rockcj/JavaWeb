package cn.edu.lingnan.test;

import cn.edu.lingnan.dao.StudentDao;
import cn.edu.lingnan.dao.imp.StudentDaoImpIMysql;

public class XMLParseTest {
    public static void main(String[] args) {
        StudentDao student = new StudentDaoImpIMysql();
        if (student.queryStudentByNameAndPassword("admin", "admin")!=null) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
    }
}
