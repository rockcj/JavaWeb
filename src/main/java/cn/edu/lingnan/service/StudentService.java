package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Student;

import java.util.List;

public interface StudentService {
    public Student queryStudentByNameAndPassword(String _sname, String _spassword);
    //使用方法重载
    public List<Student> queryAllStudentAll(int log);

    public List<Student> queryAllStudentAll();

    public Student queryStudentById(String _sid);

    public List<Student> queryStudentByName(String _sname);

    public int insertStudent(Student _student);

    public int insertHistoryStudent(Student _student);


    public int deleteStudentById(String _sid);

    // 学生修改密码
    public int editStudentPassword(String _sid, String _OldPassword, String _NewPassword);

    // 学生修改姓名
    public int editStudentName(String _sid, String _NewName);

    public int editStudentFlag(String _sid, int _NewFlag);

    public int updateStudent(Student _student);

    //学生复学？
}
