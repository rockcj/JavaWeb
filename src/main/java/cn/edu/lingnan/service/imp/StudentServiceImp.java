package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.mapper.*;
import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.service.StudentService;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class StudentServiceImp extends SqlSessionDaoSupport implements StudentService {

    @Override
    public Student queryStudentByNameAndPassword(String _sname, String _spassword) {
        return getSqlSession().getMapper(StudentMapper.class).queryStudentByNameAndPassword(_sname, _spassword);
    }

    @Override
    public List<Student> queryAllStudentAll(int log) {
        return getSqlSession().getMapper(StudentMapper.class).queryAllStudentAll(log);
    }

    @Override
    public List<Student> queryAllStudentAll() {
        return queryAllStudentAll(-1); // 查询所有学生
    }

    @Override
    public Student queryStudentById(String _sid) {
        return getSqlSession().getMapper(StudentMapper.class).queryStudentById(_sid);
    }

    @Override
    public List<Student> queryStudentByName(String _sname) {
        return getSqlSession().getMapper(StudentMapper.class).queryStudentByName(_sname);
    }

    @Override
    public int insertStudent(Student _student) {
        try {
            StudentMapper studentMapper = getSqlSession().getMapper(StudentMapper.class);

            // 检查学生是否已存在
            Student existingStudent = studentMapper.queryStudentById(_student.getSid());
            if (existingStudent != null) {
                System.out.println("学生已存在");
                return 0;
            }

            int result = studentMapper.insertStudent(_student);
            if (result > 0) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
            return result;
        } catch (Exception e) {
            System.out.println("插入学生时发生错误: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int insertHistoryStudent(Student _student) {
        try {
            StudentHistoryMapper studentHistoryMapper = getSqlSession().getMapper(StudentHistoryMapper.class);
            int result = studentHistoryMapper.insertStudent(_student);
            return result;
        } catch (Exception e) {
            System.out.println("插入学生历史记录时发生错误: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteStudentById(String _sid) {
        try {
            StudentMapper studentMapper = getSqlSession().getMapper(StudentMapper.class);
            JobMapper jobMapper = getSqlSession().getMapper(JobMapper.class);
            StudentHistoryMapper studentHistoryMapper = getSqlSession().getMapper(StudentHistoryMapper.class);

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
                System.out.println("删除学生成功");
            } else {
                System.out.println("删除学生失败");
            }
            return result;
        } catch (Exception e) {
            System.out.println("删除学生时发生错误: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int editStudentPassword(String _sid, String _OldPassword, String _NewPassword) {
        try {
            StudentMapper studentMapper = getSqlSession().getMapper(StudentMapper.class);

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
                System.out.println("密码修改成功");
            }
            return result;
        } catch (Exception e) {
            System.out.println("修改密码时发生错误: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int editStudentName(String _sid, String _NewName) {
        try {
            StudentMapper studentMapper = getSqlSession().getMapper(StudentMapper.class);
            int result = studentMapper.editStudentName(_sid, _NewName);
            if (result > 0) {
                System.out.println("姓名修改成功");
            }
            return result;
        } catch (Exception e) {
            System.out.println("修改姓名时发生错误: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int editStudentFlag(String _sid, int _NewFlag) {
        try {
            StudentMapper studentMapper = getSqlSession().getMapper(StudentMapper.class);
            int result = studentMapper.editStudentFlag(_sid, _NewFlag);
            if (result > 0) {
                System.out.println("状态修改成功");
            }
            return result;
        } catch (Exception e) {
            System.out.println("修改状态时发生错误: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateStudent(Student _student) {
        try {
            StudentMapper studentMapper = getSqlSession().getMapper(StudentMapper.class);
            int result = studentMapper.updateStudent(_student);
            if (result > 0) {
                System.out.println("更新学生成功");
            }
            return result;
        } catch (Exception e) {
            System.out.println("更新学生时发生错误: " + e.getMessage());
            return 0;
        }
    }
}