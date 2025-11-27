package cn.edu.lingnan.mapper;

import cn.edu.lingnan.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {
    // 查询所有教师
    List<Teacher> queryAllTeachers();

    // 根据ID查询教师
    Teacher queryTeacherById(String teacherId);

    // 根据姓名查询教师
    List<Teacher> queryTeacherByName(String teacherName);

    // 根据院系ID查询教师
    List<Teacher> queryTeachersByDeptId(String deptId);

    // 根据条件查询教师
    List<Teacher> queryTeacherByCondition(Map<String, Object> map);

    // 插入教师
    int insertTeacher(Teacher teacher);

    // 更新教师
    int updateTeacher(Teacher teacher);

    // 删除教师
    int deleteTeacher(String teacherId);

    // 根据条件删除教师
    int deleteTeacherByCondition(Map<String, Object> map);

    // 更新教师密码
    int updateTeacherPassword(@Param("teacherId") String teacherId, @Param("newPassword") String newPassword);
}