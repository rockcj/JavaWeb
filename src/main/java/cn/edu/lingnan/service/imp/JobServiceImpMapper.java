package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.Dto.JobDto;
import cn.edu.lingnan.mapper.*;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.pojo.Student;
import cn.edu.lingnan.pojo.Item;
import cn.edu.lingnan.service.JobService;
import cn.edu.lingnan.util.MyBatisUtil;

import java.util.List;

public class JobServiceImpMapper implements JobService {

    @Override
    public List<Job> queryAllJob() {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            return jobMapper.queryAllJob();
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Job> queryJobBysId(String _sid) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            return jobMapper.queryJobBysId(_sid);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Job> queryjobBycId(String _iid) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            return jobMapper.queryJobByiId(_iid);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<Job> queryJobByName(String _sname) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            return jobMapper.queryJobByName(_sname);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int insertJob(Job _job) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);

            // 检查学生是否存在
            Student student = studentMapper.queryStudentById(_job.getSid());
            if (student == null) {
                System.out.println("学生不存在");
                return 2;
            }

            // 检查项目是否存在
            Item item = itemMapper.queryItemById(_job.getIid());
            if (item == null) {
                System.out.println("项目不存在");
                return 3;
            }

            // 检查是否已经存在相同的job记录
            Job existingJob = jobMapper.queryJobBysIdAndcId(_job.getSid(), _job.getIid());
            if (existingJob != null) {
                System.out.println("该学生和项目的关联已存在");
                return 0;
            }

            int result = jobMapper.insertJob(_job);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("插入职位成功");
            } else {
                sqlSession.rollback();
                System.out.println("插入职位失败");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("插入职位时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public Job queryJobBysIdAndcId(String _sid, String _iid) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            return jobMapper.queryJobBysIdAndcId(_sid, _iid);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int editJob(String _sid, String _iid, String _job, int _scflag) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);

            Job jobObj = new Job();
            jobObj.setSid(_sid);
            jobObj.setIid(_iid);
            jobObj.setJob(_job);
            jobObj.setScflag(_scflag);

            int result = jobMapper.editJob(jobObj);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("修改职位成功");
            } else {
                sqlSession.rollback();
                System.out.println("修改职位失败");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("修改职位时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int deleteJobBysId(String _sid) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            int result = jobMapper.deleteJobBysId(_sid);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("删除该学生的所有职位成功");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("删除职位时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int deleteJobByiId(String _iid) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            int result = jobMapper.deleteJobByiId(_iid);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("删除该项目的所有职位成功");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("删除职位时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public int deleteJob(String _sid, String _iid) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            int result = jobMapper.deleteJob(_sid, _iid);
            if (result > 0) {
                sqlSession.commit();
                System.out.println("删除职位成功");
            }
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("删除职位时发生错误: " + e.getMessage());
            return 0;
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<JobDto> queryJobDtoAll() {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            return jobMapper.queryJobDtoAll();
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Override
    public List<JobDto> queryJobDtoStuBySid(String _sid) {
        var sqlSession = MyBatisUtil.getSqlSession();
        try {
            JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
            return jobMapper.queryJobDtoStuBySid(_sid);
        } finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }
}