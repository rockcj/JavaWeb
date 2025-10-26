package cn.edu.lingnan.service;

import cn.edu.lingnan.Dto.JobDto;
import cn.edu.lingnan.pojo.Job;

import java.util.List;

public interface JobService {
    //查询所有职位记录
    //输入参数：无
    //输出参数：课程对象的集合list
    public List<Job> queryAllJob();


    //按学生id进行精确查询
    public List<Job> queryJobBysId(String _sid);

    //按课程id进行精确查询
    public List<Job> queryjobBycId(String _iid);

    //按学生姓名进行模糊查询
    //返回多个对象，使用List集合
    public List<Job> queryJobByName(String _sname);

    //由多对多联系产生的这张表上做操作
    //新增一条（改职位价格工资）
    //输入参数：item对象
    //输出参数：成功1，失败0、2、3
    //失败，1.没有对应的学(2)，2.没有对应的课程(3)，3.新增时语句错误/从出现异常(0)
    public int insertJob(Job _job);

    //按学生id和项目id进行精确查询
    public Job queryJobBysIdAndcId(String _sid, String _iid);

    public int editJob(String _sid, String _iid, String _job, int _scflag);

    public int deleteJobBysId(String _sid);
    public int deleteJobByiId(String _iid);

    public int deleteJob(String _sid, String _iid);

    public List<JobDto> queryJobDtoAll();
    public List<JobDto> queryJobDtoStuBySid(String _sid);
}
