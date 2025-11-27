package cn.edu.lingnan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private String courseId;
    private String courseName;
    private String courseCode;
    private String deptId;
    private BigDecimal credit;
    private Integer courseHours;
    private String courseType;
    private String teacherName;
    private String semester;
    private String courseDesc;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer cflag;
}