package cn.edu.lingnan.service.imp;

import cn.edu.lingnan.Dto.JobDto;
import cn.edu.lingnan.dao.ItemDao;
import cn.edu.lingnan.dao.JobDao;
import cn.edu.lingnan.dao.StudentDao;
import cn.edu.lingnan.dao.imp.ItemDaoImpIMysql;
import cn.edu.lingnan.dao.imp.JobDaoImpIMysql;
import cn.edu.lingnan.dao.imp.StudentDaoImpIMysql;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.service.JobService;

import java.util.List;

public class JobServiceImpMysql implements JobService {
    //简单业务直接返回dao层查询结果
    JobDao jobDao = new JobDaoImpIMysql();
    StudentDao student = new StudentDaoImpIMysql();
    ItemDao itemDao = new ItemDaoImpIMysql();
    @Override
    public List<Job> queryAllJob() {
        return jobDao.queryAllJob();
    }

    @Override
    public List<Job> queryJobBysId(String _sid) {
        return jobDao.queryJobBysId(_sid);
    }

    @Override
    public List<Job> queryjobBycId(String _iid) {
        return jobDao.queryJobByiId(_iid);
    }

    @Override
    public List<Job> queryJobByName(String _sname) {
        return jobDao.queryJobByName(_sname);
    }

    @Override
    public Job queryJobBysIdAndcId(String _sid, String _iid) {
        return jobDao.queryJobBysIdAndcId(_sid, _iid);
    }

    //复杂业务.....
    //由多对多联系产生的这张表上做操作
    //新增一条（改职位价格工资）
    //输入参数：item对象
    //输出参数：成功1，失败0、-1
    //失败，1.没有对应的学生/课程(-1)，2.新增时语句错误/从出现异常(0)，3.重复插入(2)
    @Override
    public int insertJob(Job _job) {
        //可以联合调用学生表和和课程表的方法，判断学号和课程号是否存在，避免重复简化逻辑
        //对Dao层的方法进行组合
        if(_job.getSid()==null||_job.getIid()==null){
            System.out.println("输入参数错误");
            return 5;
        }
        if(student.queryStudentById(_job.getSid())!=null && itemDao.queryItemById(_job.getIid())!=null)
        {
            if(jobDao.queryJobBysIdAndcId(_job.getSid(),_job.getIid())==null)
            {
                System.out.println("插入成功");
                return jobDao.insertJob(_job);//成功1，失败0
            }
            else {
                System.out.println("该记录以存在");
                return 2;
            }
        }else {
            System.out.println("插入失败");
            return -1;
        }
    }

    @Override
    public int editJob(String _sid, String _iid, String _job, int _scflag)
    {
        int flag=0;
        Job job = new Job(_sid,_iid,_job,_scflag);
        if(jobDao.queryJobBysIdAndcId(_sid,_iid)!=null)//保证数据存在
        {
            flag = jobDao.editJob(job);
            System.out.println("修改成功");
        }else {
            System.out.println("输入参数错误/id不匹配");
        }
        return flag;
    }

    @Override
    public int deleteJobBysId(String _sid) {
        return jobDao.deleteJobBysId(_sid);
    }

    @Override
    public int deleteJobByiId(String _iid) {
        return jobDao.deleteJobByiId(_iid);
    }

    @Override
    public int deleteJob(String _sid, String _iid) {
        return jobDao.deleteJob(_sid,_iid);
    }

    @Override
    public List<JobDto> queryJobDtoAll() {
        return jobDao.queryJobDtoAll();
    }

    @Override
    public List<JobDto> queryJobDtoStuBySid(String _sid) {
        return jobDao.queryJobDtoStuBySid(_sid);
    }
}
