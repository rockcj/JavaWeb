package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.mapper.TeacherMapper;
import cn.edu.lingnan.pojo.Teacher;
import cn.edu.lingnan.service.TeacherService;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("teacherService")
@Transactional
public class TeacherServiceImp extends SqlSessionDaoSupport implements TeacherService {

    @Autowired
    public void setSqlSessionTemplate(org.mybatis.spring.SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public List<Teacher> queryAllTeachers() {
        return getSqlSession().getMapper(TeacherMapper.class).queryAllTeachers();
    }

    @Override
    public Teacher queryTeacherById(String teacherId) {
        return getSqlSession().getMapper(TeacherMapper.class).queryTeacherById(teacherId);
    }

    @Override
    public List<Teacher> queryTeacherByName(String teacherName) {
        return getSqlSession().getMapper(TeacherMapper.class).queryTeacherByName(teacherName);
    }

    @Override
    public List<Teacher> queryTeachersByDeptId(String deptId) {
        return getSqlSession().getMapper(TeacherMapper.class).queryTeachersByDeptId(deptId);
    }

    @Override
    public List<Teacher> queryTeacherByCondition(Map<String, Object> map) {
        return getSqlSession().getMapper(TeacherMapper.class).queryTeacherByCondition(map);
    }

    @Override
    public int insertTeacher(Teacher teacher) {
        return getSqlSession().getMapper(TeacherMapper.class).insertTeacher(teacher);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return getSqlSession().getMapper(TeacherMapper.class).updateTeacher(teacher);
    }

    @Override
    public int deleteTeacher(String teacherId) {
        return getSqlSession().getMapper(TeacherMapper.class).deleteTeacher(teacherId);
    }

    @Override
    public int deleteTeacherByCondition(Map<String, Object> map) {
        return getSqlSession().getMapper(TeacherMapper.class).deleteTeacherByCondition(map);
    }

    @Override
    public int updateTeacherPassword(String teacherId, String newPassword) {
        return getSqlSession().getMapper(TeacherMapper.class).updateTeacherPassword(teacherId, newPassword);
    }
}