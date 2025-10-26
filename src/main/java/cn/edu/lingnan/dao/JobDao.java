package cn.edu.lingnan.dao;


import cn.edu.lingnan.Dto.JobDto;
import cn.edu.lingnan.pojo.Job;

import java.util.List;

//分数接口
public interface JobDao {
    //查询所有成绩记录
    //输入参数：无
    //输出参数：课程对象的集合list
    public List<Job> queryAllJob();

    //按学生id进行精确查询
    public List<Job> queryJobBysId(String _sid);

    //按项目id进行精确查询
    public List<Job> queryJobByiId(String _iid);

    //按学生id和项目id进行精确查询
    public Job queryJobBysIdAndcId(String _sid, String _iid);

    //按学生姓名进行模糊查询
    //返回多个对象，使用List集合
    public List<Job> queryJobByName(String _sname);

    //由多对多联系产生的这张表上做操作
    //新增一条分数（改职位价格工资）
    //输入参数：课程对象
    //输出参数：成功1，失败0、2、3
    //失败，1.没有对应的学(2)，2.没有对应的课程(3)，3.新增时语句错误/从出现异常(0)
    public int insertJob(Job _job);

    //删除一条职位记录
    public int deleteJobBysId(String _sid);

    public int deleteJobByiId(String _iid);

    //学生姓名+项目名+职位+学生id+项目id
    public List<JobDto> queryJobDtoAll();

    public List<JobDto> queryJobDtoStuBySid(String _sid);

    //修改职位
    public int editJob(Job _job);

    int deleteJob(String _sid, String _iid);
}
