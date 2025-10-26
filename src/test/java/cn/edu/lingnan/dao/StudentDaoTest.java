package cn.edu.lingnan.dao;

import cn.edu.lingnan.dao.imp.StudentDaoImpIMysql;
import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.StudentService;
import cn.edu.lingnan.service.imp.StudentServiceImpMysql;
import org.junit.Test;


public class StudentDaoTest {
    StudentDao studentDao = new StudentDaoImpIMysql();
    StudentService studentService = new StudentServiceImpMysql();
    @Test
    public void queryStudentByNameAndPassword() {
        System.out.println(studentDao.queryStudentByNameAndPassword("admin", "admin"));
    }
    @Test
    public void queryAllStudentAll() {
        System.out.println(studentDao.queryAllStudentAll(5));
    }

    @Test
    public void queryStudentById() {
        System.out.println(studentDao.queryStudentById("005"));
    }

    @Test
    public void queryStudentByName() {
        System.out.println(studentDao.queryStudentByName("mi"));
    }

    @Test
    public void insertStudent() {
        Student student = new Student("005", "啊啊", "abcd", 0, 1);
        System.out.println(studentService.insertStudent(student));
    }

    @Test
    public void deleteStudentById() {
        System.out.println(studentService.deleteStudentById("005"));
        //因为iid和sid有外键约束，删除会触发外键约束，所以失败
        //父表：student
        //子表：job
        //如果子表中有相关记录，则不允许删除或更新父表中的记录。
        //在删除或更新 student 表之前，需要确保 job 表中没有依赖记该录的数据
    }

    @Test
    public void editStudentPassword() {
        System.out.println(studentService.editStudentPassword("005", "1234", "abcd"));
    }

    @Test
    public void editStudentName() {
        System.out.println(studentService.editStudentName("005", "bbbb"));
    }

}
