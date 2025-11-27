package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Course;

import java.util.List;

public interface CourseService {

    /**
     * 查询所有课程记录
     * @return 课程对象列表
     */
    List<Course> queryAllCourse();

    /**
     * 根据课程ID精确查询
     * @param courseId 课程ID
     * @return Course对象
     */
    Course queryCourseById(String courseId);

    /**
     * 根据课程名称模糊查询
     * @param courseName 课程名称（支持模糊匹配）
     * @return 课程对象列表
     */
    List<Course> queryCourseByName(String courseName);

    /**
     * 根据课程编码查询
     * @param courseCode 课程编码
     * @return Course对象
     */
    Course queryCourseByCode(String courseCode);

    /**
     * 根据院系ID查询课程
     * @param deptId 院系ID
     * @return 课程对象列表
     */
    List<Course> queryCourseByDeptId(String deptId);

    /**
     * 根据教师姓名查询课程
     * @param teacherName 教师姓名
     * @return 课程对象列表
     */
    List<Course> queryCourseByTeacher(String teacherName);

    /**
     * 根据学期查询课程
     * @param semester 学期
     * @return 课程对象列表
     */
    List<Course> queryCourseBySemester(String semester);

    /**
     * 新增课程记录
     * @param course 课程对象
     * @return 影响的记录数
     */
    int insertCourse(Course course);

    /**
     * 删除课程记录
     * @param courseId 课程ID
     * @return 影响的记录数
     */
    int deleteCourseById(String courseId);

    /**
     * 更新课程信息
     * @param course 课程对象
     * @return 影响的记录数
     */
    int updateCourse(Course course);

    /**
     * 修改课程状态标志
     * @param courseId 课程ID
     * @param cflag 新状态值
     * @return 影响的记录数
     */
    int editCourseFlag(String courseId, int cflag);

    /**
     * 按状态查询课程
     * @param cflag 状态标志
     * @return 课程对象列表
     */
    List<Course> queryCourseByFlag(int cflag);

    /**
     * 根据课程类型查询课程
     * @param courseType 课程类型
     * @return 课程对象列表
     */
    List<Course> queryCourseByType(String courseType);
}