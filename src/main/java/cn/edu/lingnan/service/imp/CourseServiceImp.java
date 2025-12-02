package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.mapper.CourseMapper;
import cn.edu.lingnan.mapper.DepartmentMapper;
import cn.edu.lingnan.pojo.Course;
import cn.edu.lingnan.service.CourseService;
import cn.edu.lingnan.util.MyBatisUtil;

import java.util.List;

public class CourseServiceImp implements CourseService {

    @Override
    public List<Course> queryAllCourse() {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            return courseMapper.queryAllCourse();
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public Course queryCourseById(String courseId) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            return courseMapper.queryCourseById(courseId);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Course> queryCourseByName(String courseName) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            return courseMapper.queryCourseByName(courseName);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public Course queryCourseByCode(String courseCode) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            return courseMapper.queryCourseByCode(courseCode);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Course> queryCourseByDeptId(String deptId) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            return courseMapper.queryCourseByDeptId(deptId);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Course> queryCourseByTeacher(String teacherName) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            return courseMapper.queryCourseByTeacher(teacherName);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Course> queryCourseBySemester(String semester) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            return courseMapper.queryCourseBySemester(semester);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int insertCourse(Course course) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            // 统一将cflag默认设置为0，避免插入null后被前端查询过滤
            if (course.getCflag() == null) {
                course.setCflag(0);
            }
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

            // 检查课程是否已存在
            Course existingCourse = courseMapper.queryCourseById(course.getCourseId());
            if (existingCourse != null) {
                System.out.println("课程已存在");
                return 0;
            }

            // 检查课程编码是否已存在
            Course existingCourseByCode = courseMapper.queryCourseByCode(course.getCourseCode());
            if (existingCourseByCode != null) {
                System.out.println("课程编码已存在");
                return 0;
            }

            // 检查院系是否存在
            if (course.getDeptId() != null && !course.getDeptId().isEmpty()) {
                var department = departmentMapper.queryDepartmentById(course.getDeptId());
                if (department == null) {
                    System.out.println("院系不存在");
                    return 0;
                }
            }

            int result = courseMapper.insertCourse(course);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("插入课程成功");
            } else {
                sqlSession.rollback();
                System.out.println("插入课程失败");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("插入课程时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int deleteCourseById(String courseId) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

            // 先查询课程是否存在
            Course course = courseMapper.queryCourseById(courseId);
            if (course == null) {
                System.out.println("课程不存在");
                return 0;
            }

            int result = courseMapper.deleteCourseById(courseId);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("删除课程成功");
            } else {
                sqlSession.rollback();
                System.out.println("删除课程失败");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("删除课程时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int updateCourse(Course course) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            // 更新操作同样补齐cflag，确保状态字段不被写成null
            if (course.getCflag() == null) {
                course.setCflag(0);
            }
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

            // 先查询课程是否存在
            Course existingCourse = courseMapper.queryCourseById(course.getCourseId());
            if (existingCourse == null) {
                System.out.println("课程不存在");
                return 0;
            }

            // 检查院系是否存在
            if (course.getDeptId() != null && !course.getDeptId().isEmpty()) {
                var department = departmentMapper.queryDepartmentById(course.getDeptId());
                if (department == null) {
                    System.out.println("院系不存在");
                    return 0;
                }
            }

            int result = courseMapper.updateCourse(course);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("更新课程成功");
            } else {
                sqlSession.rollback();
                System.out.println("更新课程失败");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("更新课程时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int editCourseFlag(String courseId, int cflag) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

            // 先查询课程是否存在
            Course course = courseMapper.queryCourseById(courseId);
            if (course == null) {
                System.out.println("课程不存在");
                return 0;
            }

            int result = courseMapper.editCourseFlag(courseId, cflag);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("修改课程状态成功");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("修改课程状态时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Course> queryCourseByFlag(int cflag) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            return courseMapper.queryCourseByFlag(cflag);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Course> queryCourseByType(String courseType) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            return courseMapper.queryCourseByType(courseType);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }
}