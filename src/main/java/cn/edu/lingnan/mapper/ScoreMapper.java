package cn.edu.lingnan.mapper;

import cn.edu.lingnan.pojo.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScoreMapper {
    // 查询所有成绩
    List<Score> queryAllScores();

    // 根据ID查询成绩
    Score queryScoreById(String scoreId);

    // 根据学生ID查询成绩
    List<Score> queryScoresByStudentId(String studentId);

    // 根据课程ID查询成绩
    List<Score> queryScoresByCourseId(String courseId);

    // 根据学生ID和课程ID查询成绩
    Score queryScoreByStudentAndCourse(@Param("studentId") String studentId, @Param("courseId") String courseId);

    // 根据条件查询成绩
    List<Score> queryScoresByCondition(Map<String, Object> map);

    // 插入成绩
    int insertScore(Score score);

    // 更新成绩
    int updateScore(Score score);

    // 删除成绩
    int deleteScore(String scoreId);

    // 根据条件删除成绩
    int deleteScoreByCondition(Map<String, Object> map);

    // 批量插入成绩
    int batchInsertScores(List<Score> scores);
}