package cn.edu.lingnan.mapper;

import cn.edu.lingnan.Dto.JobDto;
import cn.edu.lingnan.pojo.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JobMapper {

    /**
     * 查询所有职位记录
     * @return 职位对象列表
     */
    @Select("SELECT * FROM job")
    List<Job> queryAllJob();

    /**
     * 根据学生ID查询该学生的所有职位
     * @param _sid 学生ID
     * @return 职位对象列表
     */
    @Select("SELECT * FROM job WHERE sid = #{_sid}")
    List<Job> queryJobBysId(@Param("_sid") String _sid);

    /**
     * 根据项目ID查询该项目下的所有职位
     * @param _iid 项目ID
     * @return 职位对象列表
     */
    @Select("SELECT * FROM job WHERE iid = #{_iid}")
    List<Job> queryJobByiId(@Param("_iid") String _iid);

    /**
     * 根据学生ID和项目ID查询特定职位记录
     * @param _sid 学生ID
     * @param _iid 项目ID
     * @return 职位对象
     */
    @Select("SELECT * FROM job WHERE sid = #{_sid} AND iid = #{_iid}")
    Job queryJobBysIdAndcId(@Param("_sid") String _sid, @Param("_iid") String _iid);

    /**
     * 根据学生姓名模糊查询相关职位信息（使用XML映射文件）
     * @param _sname 学生姓名
     * @return 职位对象列表
     */
    List<Job> queryJobByName(@Param("_sname") String _sname);

    /**
     * 新增职位记录
     * @param _job 职位对象
     * @return 影响的记录数
     */
    @Insert("INSERT INTO job(sid, iid, job, scflag) VALUES(#{_job.sid}, #{_job.iid}, #{_job.job}, #{_job.scflag})")
    int insertJob(@Param("_job") Job _job);

    /**
     * 删除指定学生的所有职位记录
     * @param _sid 学生ID
     * @return 影响的记录数
     */
    @Delete("DELETE FROM job WHERE sid = #{_sid}")
    int deleteJobBysId(@Param("_sid") String _sid);

    /**
     * 删除指定项目的所有职位记录
     * @param _iid 项目ID
     * @return 影响的记录数
     */
    @Delete("DELETE FROM job WHERE iid = #{_iid}")
    int deleteJobByiId(@Param("_iid") String _iid);

    /**
     * 删除特定学生和项目的关联关系
     * @param _sid 学生ID
     * @param _iid 项目ID
     * @return 影响的记录数
     */
    @Delete("DELETE FROM job WHERE sid = #{_sid} AND iid = #{_iid}")
    int deleteJob(@Param("_sid") String _sid, @Param("_iid") String _iid);

    /**
     * 查询所有职位的详细信息（使用XML映射文件）
     * @return JobDto对象列表
     */
    List<JobDto> queryJobDtoAll();

    /**
     * 根据学生ID查询该学生的所有职位详细信息
     * @param _sid 学生ID
     * @return JobDto对象列表
     */
    @Select("SELECT * FROM jobview WHERE sid = #{_sid}")
    List<JobDto> queryJobDtoStuBySid(@Param("_sid") String _sid);

    /**
     * 修改职位信息
     * @param _job 职位对象
     * @return 影响的记录数
     */
    @Update("UPDATE job SET job = #{_job.job}, scflag = #{_job.scflag} WHERE sid = #{_job.sid} AND iid = #{_job.iid}")
    int editJob(@Param("_job") Job _job);
}