package cn.edu.lingnan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private String teacherId;
    private String teacherName;
    private String gender;
    private Integer age;
    private String phone;
    private String email;
    private String deptId;
    private String title;
    private Integer flag;

    // 关联查询字段
    private Department department;
    private List<Course> courses;
}