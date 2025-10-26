package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Student;

public interface StudentHistoryDao {
    public int insertStudent(Student _student);

    Student queryStudentById(String sid);
}
