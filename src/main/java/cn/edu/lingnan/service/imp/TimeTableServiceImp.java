package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.mapper.TimeTableMapper;
import cn.edu.lingnan.pojo.TimeTable;
import cn.edu.lingnan.service.TimeTableService;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("timeTableService")
@Transactional
public class TimeTableServiceImp extends SqlSessionDaoSupport implements TimeTableService {

    @Autowired
    public void setSqlSessionTemplate(org.mybatis.spring.SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public List<TimeTable> queryAllTimeTables() {
        return getSqlSession().getMapper(TimeTableMapper.class).queryAllTimeTables();
    }

    @Override
    public TimeTable queryTimeTableById(String timetableId) {
        return getSqlSession().getMapper(TimeTableMapper.class).queryTimeTableById(timetableId);
    }

    @Override
    public List<TimeTable> queryTimeTableByCourseId(String courseId) {
        return getSqlSession().getMapper(TimeTableMapper.class).queryTimeTableByCourseId(courseId);
    }

    @Override
    public List<TimeTable> queryTimeTableByTeacherId(String teacherId) {
        return getSqlSession().getMapper(TimeTableMapper.class).queryTimeTableByTeacherId(teacherId);
    }

    @Override
    public List<TimeTable> queryTimeTableByClassroomId(String classroomId) {
        return getSqlSession().getMapper(TimeTableMapper.class).queryTimeTableByClassroomId(classroomId);
    }

    @Override
    public List<TimeTable> queryTimeTableBySemester(String semester) {
        return getSqlSession().getMapper(TimeTableMapper.class).queryTimeTableBySemester(semester);
    }

    @Override
    public List<TimeTable> queryTimeTableByTeacherAndSemester(String teacherId, String semester) {
        return getSqlSession().getMapper(TimeTableMapper.class).queryTimeTableByTeacherAndSemester(teacherId, semester);
    }

    @Override
    public List<TimeTable> queryTimeTableByCondition(Map<String, Object> map) {
        return getSqlSession().getMapper(TimeTableMapper.class).queryTimeTableByCondition(map);
    }

    @Override
    public int insertTimeTable(TimeTable timeTable) {
        // 防止flag为空导致查询过滤，默认0表示有效数据
        if (timeTable.getFlag() == null) {
            timeTable.setFlag(0);
        }
        return getSqlSession().getMapper(TimeTableMapper.class).insertTimeTable(timeTable);
    }

    @Override
    public int updateTimeTable(TimeTable timeTable) {
        return getSqlSession().getMapper(TimeTableMapper.class).updateTimeTable(timeTable);
    }

    @Override
    public int deleteTimeTable(String timetableId) {
        return getSqlSession().getMapper(TimeTableMapper.class).deleteTimeTable(timetableId);
    }

    @Override
    public int deleteTimeTableByCondition(Map<String, Object> map) {
        return getSqlSession().getMapper(TimeTableMapper.class).deleteTimeTableByCondition(map);
    }

    @Override
    public List<TimeTable> queryTimeConflictByTeacher(String teacherId, String dayOfWeek, String timeSlot, String semester) {
        return getSqlSession().getMapper(TimeTableMapper.class).queryTimeConflictByTeacher(teacherId, dayOfWeek, timeSlot, semester);
    }
}