package cn.edu.lingnan.mapper;

import cn.edu.lingnan.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     * 学生登录验证
     * @param _sname 学生姓名
     * @param _spassword 学生密码
     * @return Student对象，登录成功返回学生信息，失败返回null
     */
    @Select("SELECT * FROM student WHERE sname = #{_sname} AND spassword = #{_spassword}")
    Student queryStudentByNameAndPassword(@Param("_sname") String _sname, @Param("_spassword") String _spassword);

    /**
     * 查询所有学生记录，支持按状态筛选
     * @param log 状态标志（0=正常状态，1=删除状态，其他=全部）
     * @return 学生对象列表
     */
    @SelectProvider(type = StudentSqlProvider.class, method = "queryAllStudentAllSql")
    List<Student> queryAllStudentAll(int log);

    /**
     * 按学生ID精确查询
     * @param _sid 学生ID
     * @return Student对象
     */
    @Select("SELECT * FROM student WHERE sid = #{_sid}")
    Student queryStudentById(@Param("_sid") String _sid);

    /**
     * 按学生姓名模糊查询
     * @param _sname 学生姓名（支持模糊匹配）
     * @return 学生对象列表
     */
    @Select("SELECT * FROM student WHERE sname LIKE CONCAT('%', #{_sname}, '%')")
    List<Student> queryStudentByName(@Param("_sname") String _sname);

    /**
     * 新增学生记录
     * @param _student 学生对象
     * @return 影响的记录数
     */
    @Insert("INSERT INTO student(sid, sname, spassword, sright, stflag) VALUES(#{_student.sid}, #{_student.sname}, #{_student.spassword}, #{_student.sright}, #{_student.stflag})")
    int insertStudent(@Param("_student") Student _student);

    /**
     * 根据ID删除学生记录（硬删除）
     * @param _sid 学生ID
     * @return 影响的记录数
     */
    @Delete("DELETE FROM student WHERE sid = #{_sid}")
    int deleteStudentById(@Param("_sid") String _sid);

    /**
     * 修改学生密码
     * @param _sid 学生ID
     * @param _NewPassword 新密码
     * @return 影响的记录数
     */
    @Update("UPDATE student SET spassword = #{_NewPassword} WHERE sid = #{_sid}")
    int editStudentPassword(@Param("_sid") String _sid, @Param("_NewPassword") String _NewPassword);

    /**
     * 修改学生姓名
     * @param _sid 学生ID
     * @param _NewName 新姓名
     * @return 影响的记录数
     */
    @Update("UPDATE student SET sname = #{_NewName} WHERE sid = #{_sid}")
    int editStudentName(@Param("_sid") String _sid, @Param("_NewName") String _NewName);

    /**
     * 修改学生状态标志
     * @param _sid 学生ID
     * @param _NewFlag 新状态值
     * @return 影响的记录数
     */
    @Update("UPDATE student SET stflag = #{_NewFlag} WHERE sid = #{_sid}")
    int editStudentFlag(@Param("_sid") String _sid, @Param("_NewFlag") int _NewFlag);

    /**
     * 更新学生完整信息
     * @param _student 学生对象
     * @return 影响的记录数
     */
    @Update("UPDATE student SET sname = #{_student.sname}, spassword = #{_student.spassword}, sright = #{_student.sright}, stflag = #{_student.stflag} WHERE sid = #{_student.sid}")
    int updateStudent(@Param("_student") Student _student);

    // SQL提供者类，用于动态SQL
    class StudentSqlProvider {
        public String queryAllStudentAllSql(int log) {
            if (log == 0) {
                return "SELECT * FROM student WHERE COALESCE(stflag, 0) = 0";
            } else if (log == 1) {
                return "SELECT * FROM student WHERE stflag = 1";
            } else {
                return "SELECT * FROM student";
            }
        }
    }
}