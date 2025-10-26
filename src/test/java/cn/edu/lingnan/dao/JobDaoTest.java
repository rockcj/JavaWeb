package cn.edu.lingnan.dao;

import cn.edu.lingnan.dao.imp.JobDaoImpIMysql;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.service.JobService;
import cn.edu.lingnan.service.imp.JobServiceImpMysql;
import org.junit.Test;

public class JobDaoTest {
    JobDao jobDao = new JobDaoImpIMysql();
    JobService jobService = new JobServiceImpMysql();
    @Test
    public void queryAllJob(){
        System.out.println(jobDao.queryAllJob());
    }

    @Test
    public void queryJobBysId(){
        System.out.println(jobDao.queryJobBysId("001"));
    }

    @Test
    public void queryJobBycId(){
        System.out.println(jobDao.queryJobByiId("1"));
    }

    @Test
    public void queryJobBysIdAndcId(){
        System.out.println(jobDao.queryJobBysIdAndcId("001","1"));
    }

    @Test
    public void queryJobByName(){
        System.out.println(jobDao.queryJobByName("m"));
    }

    @Test
    public void insertJob(){
        Job job = new Job("005","6","校长",2);
        System.out.println(jobService.insertJob(job));
    }

    @Test
    public void deleteJobById(){
        System.out.println(jobDao.deleteJobBysId("005"));
    }

    @Test
    public void queryJobDtoAll(){
        System.out.println(jobDao.queryJobDtoAll());
    }

    @Test
    public void editJob(){
        System.out.println(jobService.editJob("005","6","老师",2));
    }
    
}
