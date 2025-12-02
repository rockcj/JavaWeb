package cn.edu.lingnan.mapper;

import cn.edu.lingnan.pojo.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    /**
     * 查询所有课程记录（使用XML映射文件���关联院系信息）
     * @return 课程对象列表
     */
    List<Course> queryAllCourse();

    /**
     * 根据课程ID精确查询
     * @param courseId 课程ID
     * @return Course对象
     */
    @Select("SELECT course_id, course_name, course_code, dept_id, credit, course_hours, course_type, teacher_name, semester, course_desc, create_time, update_time, cflag FROM course WHERE course_id = #{courseId}")
    Course queryCourseById(@Param("courseId") String courseId);

    /**
     * 根据课程名称模糊查询
     * @param courseName 课程名称（支持模糊匹配）
     * @return 课程对象列表
     */
    @Select("SELECT course_id, course_name, course_code, dept_id, credit, course_hours, course_type, teacher_name, semester, course_desc, create_time, update_time, cflag FROM course WHERE course_name LIKE CONCAT('%', #{courseName}, '%')")
    List<Course> queryCourseByName(@Param("courseName") String courseName);

    /**
     * 根据课程编码查询
     * @param courseCode 课程编码
     * @return Course对象
     */
    @Select("SELECT course_id, course_name, course_code, dept_id, credit, course_hours, course_type, teacher_name, semester, course_desc, create_time, update_time, cflag FROM course WHERE course_code = #{courseCode}")
    Course queryCourseByCode(@Param("courseCode") String courseCode);

    /**
     * 根据院系ID查询课程
     * @param deptId 院系ID
     * @return 课程对象列表
     */
    @Select("SELECT course_id, course_name, course_code, dept_id, credit, course_hours, course_type, teacher_name, semester, course_desc, create_time, update_time, cflag FROM course WHERE dept_id = #{deptId}")
    List<Course> queryCourseByDeptId(@Param("deptId") String deptId);

    /**
     * 根据教师姓名查询课程
     * @param teacherName 教师姓名
     * @return 课程对象列表
     */
    @Select("SELECT course_id, course_name, course_code, dept_id, credit, course_hours, course_type, teacher_name, semester, course_desc, create_time, update_time, cflag FROM course WHERE teacher_name LIKE CONCAT('%', #{teacherName}, '%')")
    List<Course> queryCourseByTeacher(@Param("teacherName") String teacherName);

    /**
     * 根据学期查询课程
     * @param semester 学期
     * @return 课程对象列表
     */
    @Select("SELECT course_id, course_name, course_code, dept_id, credit, course_hours, course_type, teacher_name, semester, course_desc, create_time, update_time, cflag FROM course WHERE semester = #{semester}")
    List<Course> queryCourseBySemester(@Param("semester") String semester);

    /**
     * 新增课程记录
     * @param course 课程对象
     * @return 影响的记录数
     */
    @Insert("INSERT INTO course(course_id, course_name, course_code, dept_id, credit, course_hours, course_type, teacher_name, semester, course_desc, cflag) VALUES(#{course.courseId}, #{course.courseName}, #{course.courseCode}, #{course.deptId}, #{course.credit}, #{course.courseHours}, #{course.courseType}, #{course.teacherName}, #{course.semester}, #{course.courseDesc}, #{course.cflag})")
    int insertCourse(@Param("course") Course course);

    /**
     * 删除课程记录
     * @param courseId 课程ID
     * @return 影响的记录数
     */
    @Delete("DELETE FROM course WHERE course_id = #{courseId}")
    int deleteCourseById(@Param("courseId") String courseId);

    /**
     * 更新课程信息（使用XML映射文件实现复杂更新）
     * @param course 课程对象
     * @return 影响的记录数
     */
    int updateCourse(@Param("course") Course course);

    /**
     * 修改课程状态标志
     * @param courseId 课程ID
     * @param cflag 新状态值
     * @return 影响的记录数
     */
    @Update("UPDATE course SET cflag = #{cflag} WHERE course_id = #{courseId}")
    int editCourseFlag(@Param("courseId") String courseId, @Param("cflag") Integer cflag);

    /**
     * 按状态查询课程
     * @param cflag 状态标志
     * @return 课程对象列表
     */
    @Select("SELECT course_id, course_name, course_code, dept_id, credit, course_hours, course_type, teacher_name, semester, course_desc, create_time, update_time, cflag " +
            "FROM course WHERE COALESCE(cflag, 0) = #{cflag}")
    List<Course> queryCourseByFlag(@Param("cflag") Integer cflag);

    /**
     * 根据课程类型查询课程
     * @param courseType 课程类型
     * @return 课程对象列表
     */
    @Select("SELECT course_id, course_name, course_code, dept_id, credit, course_hours, course_type, teacher_name, semester, course_desc, create_time, update_time, cflag FROM course WHERE course_type = #{courseType}")
    List<Course> queryCourseByType(@Param("courseType") String courseType);
}