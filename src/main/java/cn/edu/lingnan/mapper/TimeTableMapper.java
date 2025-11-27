package cn.edu.lingnan.mapper;

import cn.edu.lingnan.pojo.TimeTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TimeTableMapper {
    // 查询所有课表
    List<TimeTable> queryAllTimeTables();

    // 根据ID查询课表
    TimeTable queryTimeTableById(String timetableId);

    // 根据课程ID查询课表
    List<TimeTable> queryTimeTableByCourseId(String courseId);

    // 根据教师ID查询课表
    List<TimeTable> queryTimeTableByTeacherId(String teacherId);

    // 根据教室ID查询课表
    List<TimeTable> queryTimeTableByClassroomId(String classroomId);

    // 根据学期查询课表
    List<TimeTable> queryTimeTableBySemester(String semester);

    // 根据教师ID和学期查询课表
    List<TimeTable> queryTimeTableByTeacherAndSemester(@Param("teacherId") String teacherId, @Param("semester") String semester);

    // 根据条件查询课表
    List<TimeTable> queryTimeTableByCondition(Map<String, Object> map);

    // 插入课表
    int insertTimeTable(TimeTable timeTable);

    // 更新课表
    int updateTimeTable(TimeTable timeTable);

    // 删除课表
    int deleteTimeTable(String timetableId);

    // 根据条件删除课表
    int deleteTimeTableByCondition(Map<String, Object> map);

    // 根据教师ID和时间段查询冲突
    List<TimeTable> queryTimeConflictByTeacher(@Param("teacherId") String teacherId, @Param("dayOfWeek") String dayOfWeek, @Param("timeSlot") String timeSlot, @Param("semester") String semester);
}