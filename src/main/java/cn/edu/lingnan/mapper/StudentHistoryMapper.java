package cn.edu.lingnan.mapper;

import cn.edu.lingnan.pojo.Student;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentHistoryMapper {

    /**
     * 将学生信息存入历史表
     * @param _student 学生对象
     * @return 影响的记录数
     */
    @Insert("INSERT INTO studenthistory(sid, sname, spassword, sright, stflag) VALUES(#{_student.sid}, #{_student.sname}, #{_student.spassword}, #{_student.sright}, #{_student.stflag})")
    int insertStudent(@Param("_student") Student _student);

    /**
     * 从历史表查询学生信息
     * @param sid 学生ID
     * @return Student对象
     */
    @Select("SELECT * FROM studenthistory WHERE sid = #{sid}")
    Student queryStudentById(@Param("sid") String sid);
}