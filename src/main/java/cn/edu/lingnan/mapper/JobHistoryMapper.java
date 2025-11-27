package cn.edu.lingnan.mapper;

import cn.edu.lingnan.pojo.Job;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JobHistoryMapper {

    /**
     * 将职位信息存入历史表
     * @param _job 职位对象
     * @return 影响的记录数
     */
    @Insert("INSERT INTO ScoreHistory(sid, iid, job, scflag) VALUES(#{_job.sid}, #{_job.iid}, #{_job.job}, #{_job.scflag})")
    int insertScoreHistory(@Param("_job") Job _job);
}