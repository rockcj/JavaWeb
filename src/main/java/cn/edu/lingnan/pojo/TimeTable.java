package cn.edu.lingnan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {
    private String timetableId;
    private String courseId;
    private String teacherId;
    private String classroomId;
    private String dayOfWeek;
    private String timeSlot;
    private String semester;
    private Integer flag;

    // 关联查询字段
    private Course course;
    private Teacher teacher;
}