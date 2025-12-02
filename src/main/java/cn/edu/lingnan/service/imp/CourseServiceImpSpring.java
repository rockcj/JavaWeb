package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.mapper.CourseMapper;
import cn.edu.lingnan.mapper.DepartmentMapper;
import cn.edu.lingnan.pojo.Course;
import cn.edu.lingnan.service.CourseService;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("courseService")
public class CourseServiceImpSpring extends SqlSessionDaoSupport implements CourseService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    public void setSqlSessionTemplate(org.mybatis.spring.SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public List<Course> queryAllCourse() {
        return getSqlSession().getMapper(CourseMapper.class).queryAllCourse();
    }

    @Override
    public Course queryCourseById(String courseId) {
        return getSqlSession().getMapper(CourseMapper.class).queryCourseById(courseId);
    }

    @Override
    public List<Course> queryCourseByName(String courseName) {
        return getSqlSession().getMapper(CourseMapper.class).queryCourseByName(courseName);
    }

    @Override
    public Course queryCourseByCode(String courseCode) {
        return getSqlSession().getMapper(CourseMapper.class).queryCourseByCode(courseCode);
    }

    @Override
    public List<Course> queryCourseByDeptId(String deptId) {
        return getSqlSession().getMapper(CourseMapper.class).queryCourseByDeptId(deptId);
    }

    @Override
    public List<Course> queryCourseByTeacher(String teacherName) {
        return getSqlSession().getMapper(CourseMapper.class).queryCourseByTeacher(teacherName);
    }

    @Override
    public List<Course> queryCourseBySemester(String semester) {
        return getSqlSession().getMapper(CourseMapper.class).queryCourseBySemester(semester);
    }

    @Override
    public int insertCourse(Course course) {
        try {
            // 保障课程状态默认有效，避免cflag为null导致查询过滤
            if (course.getCflag() == null) {
                course.setCflag(0);
            }
            CourseMapper courseMapper = getSqlSession().getMapper(CourseMapper.class);

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
                System.out.println("插入课程成功");
            } else {
                System.out.println("插入课程失败");
            }
            return result;
        } catch (Exception e) {
            System.out.println("插入课程时发生错误: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteCourseById(String courseId) {
        try {
            CourseMapper courseMapper = getSqlSession().getMapper(CourseMapper.class);

            // 先查询课程是否存在
            Course course = courseMapper.queryCourseById(courseId);
            if (course == null) {
                System.out.println("课程不存在");
                return 0;
            }

            int result = courseMapper.deleteCourseById(courseId);
            if (result > 0) {
                System.out.println("删除课程成功");
            } else {
                System.out.println("删除课程失败");
            }
            return result;
        } catch (Exception e) {
            System.out.println("删除课程时发生错误: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateCourse(Course course) {
        try {
            // 更新前补齐cflag，防止写回null再次影响查询
            if (course.getCflag() == null) {
                course.setCflag(0);
            }
            CourseMapper courseMapper = getSqlSession().getMapper(CourseMapper.class);

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
                System.out.println("更新课程成功");
            } else {
                System.out.println("更新课程失败");
            }
            return result;
        } catch (Exception e) {
            System.out.println("更新课程时发生错误: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int editCourseFlag(String courseId, int cflag) {
        try {
            CourseMapper courseMapper = getSqlSession().getMapper(CourseMapper.class);

            // 先查询课程是否存在
            Course course = courseMapper.queryCourseById(courseId);
            if (course == null) {
                System.out.println("课程不存在");
                return 0;
            }

            int result = courseMapper.editCourseFlag(courseId, cflag);
            if (result > 0) {
                System.out.println("修改课程状态成功");
            }
            return result;
        } catch (Exception e) {
            System.out.println("修改课程状态时发生错误: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Course> queryCourseByFlag(int cflag) {
        return getSqlSession().getMapper(CourseMapper.class).queryCourseByFlag(cflag);
    }

    @Override
    public List<Course> queryCourseByType(String courseType) {
        return getSqlSession().getMapper(CourseMapper.class).queryCourseByType(courseType);
    }
}