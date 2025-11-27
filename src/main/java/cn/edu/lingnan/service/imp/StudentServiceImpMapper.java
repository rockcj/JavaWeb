package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.mapper.*;
import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.StudentService;
import cn.edu.lingnan.util.MyBatisUtil;

import java.util.List;

public class StudentServiceImpMapper implements StudentService {

    @Override
    public Student queryStudentByNameAndPassword(String _sname, String _spassword) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.queryStudentByNameAndPassword(_sname, _spassword);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Student> queryAllStudentAll(int log) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.queryAllStudentAll(log);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Student> queryAllStudentAll() {
        return queryAllStudentAll(-1); // 查询所有学生
    }

    @Override
    public Student queryStudentById(String _sid) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.queryStudentById(_sid);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Student> queryStudentByName(String _sname) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.queryStudentByName(_sname);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int insertStudent(Student _student) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

            // 检查学生是否已存在
            Student existingStudent = studentMapper.queryStudentById(_student.getSid());
            if (existingStudent != null) {
                System.out.println("学生已存在");
                return 0;
            }

            int result = studentMapper.insertStudent(_student);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("插入成功");
            } else {
                sqlSession.rollback();
                System.out.println("插入失败");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("插入学生时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int insertHistoryStudent(Student _student) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentHistoryMapper studentHistoryMapper = sqlSession.getMapper(StudentHistoryMapper.class);
            int result = studentHistoryMapper.insertStudent(_student);
            if (result > 0) {
                sqlSession.commit();
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("插入学生历史记录时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int deleteStudentById(String _sid) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            StudentHistoryMapper studentHistoryMapper = sqlSession.getMapper(StudentHistoryMapper.class);

            // 先查询学生是否存在
            Student student = studentMapper.queryStudentById(_sid);
            if (student == null) {
                System.out.println("学生不存在");
                return 0;
            }

            // 保存到历史表
            studentHistoryMapper.insertStudent(student);

            // 删除相关的job记录
            jobMapper.deleteJobBysId(_sid);

            // 删除学生
            int result = studentMapper.deleteStudentById(_sid);

            if (result > 0) {
                sqlSession.commit();
                System.out.println("删除学生成功");
            } else {
                sqlSession.rollback();
                System.out.println("删除学生失败");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("删除学生时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int editStudentPassword(String _sid, String _OldPassword, String _NewPassword) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

            // 先查询学生信息验证旧密码
            Student student = studentMapper.queryStudentById(_sid);
            if (student == null) {
                System.out.println("学生不存在");
                return 0;
            }

            if (!_OldPassword.equals(student.getSpassword())) {
                System.out.println("旧密码不正确");
                return 0;
            }

            int result = studentMapper.editStudentPassword(_sid, _NewPassword);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("密码修改成功");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("修改密码时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int editStudentName(String _sid, String _NewName) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            int result = studentMapper.editStudentName(_sid, _NewName);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("姓名修改成功");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("修改姓名时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int editStudentFlag(String _sid, int _NewFlag) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            int result = studentMapper.editStudentFlag(_sid, _NewFlag);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("状态修改成功");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("修改状态时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int updateStudent(Student _student) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            int result = studentMapper.updateStudent(_student);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("更新学生成功");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("更新学生时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }
}