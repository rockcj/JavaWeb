package cn.edu.lingnan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private String scoreId;
    private String studentId;
    private String courseId;
    private Double score;
    private String semester;
    private String examType;
    private Integer flag;

    // 关联查询时可能用到的字段
    private Student student;
    private Course course;
}