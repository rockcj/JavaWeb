package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.mapper.ScoreMapper;
import cn.edu.lingnan.pojo.Score;
import cn.edu.lingnan.service.ScoreService;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("scoreService")
@Transactional
public class ScoreServiceImp extends SqlSessionDaoSupport implements ScoreService {

    @Autowired
    public void setSqlSessionTemplate(org.mybatis.spring.SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public List<Score> queryAllScores() {
        return getSqlSession().getMapper(ScoreMapper.class).queryAllScores();
    }

    @Override
    public Score queryScoreById(String scoreId) {
        return getSqlSession().getMapper(ScoreMapper.class).queryScoreById(scoreId);
    }

    @Override
    public List<Score> queryScoresByStudentId(String studentId) {
        return getSqlSession().getMapper(ScoreMapper.class).queryScoresByStudentId(studentId);
    }

    @Override
    public List<Score> queryScoresByCourseId(String courseId) {
        return getSqlSession().getMapper(ScoreMapper.class).queryScoresByCourseId(courseId);
    }

    @Override
    public Score queryScoreByStudentAndCourse(String studentId, String courseId) {
        return getSqlSession().getMapper(ScoreMapper.class).queryScoreByStudentAndCourse(studentId, courseId);
    }

    @Override
    public List<Score> queryScoresByCondition(Map<String, Object> map) {
        return getSqlSession().getMapper(ScoreMapper.class).queryScoresByCondition(map);
    }

    @Override
    public int insertScore(Score score) {
        return getSqlSession().getMapper(ScoreMapper.class).insertScore(score);
    }

    @Override
    public int updateScore(Score score) {
        return getSqlSession().getMapper(ScoreMapper.class).updateScore(score);
    }

    @Override
    public int deleteScore(String scoreId) {
        return getSqlSession().getMapper(ScoreMapper.class).deleteScore(scoreId);
    }

    @Override
    public int deleteScoreByCondition(Map<String, Object> map) {
        return getSqlSession().getMapper(ScoreMapper.class).deleteScoreByCondition(map);
    }

    @Override
    public int batchInsertScores(List<Score> scores) {
        return getSqlSession().getMapper(ScoreMapper.class).batchInsertScores(scores);
    }
}