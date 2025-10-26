package cn.edu.lingnan.dao.imp;

import cn.edu.lingnan.Dto.JobDto;
import cn.edu.lingnan.dao.JobDao;
import cn.edu.lingnan.pojo.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static cn.edu.lingnan.util.DBUtil.Close;
import static cn.edu.lingnan.util.DBUtil.Init;

public class JobDaoImpIMysql implements JobDao {
    @Override
    public List<Job> queryAllJob() {
        List<Job> result = new ArrayList<>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Init();
            stat = conn.createStatement();
            String sql = "select * from job";
            rs = stat.executeQuery(sql);//获取结果集
            while (rs.next()){
                Job job = new Job(
                        rs.getString("sid"),
                        rs.getString("iid"),
                        rs.getString("job"),
                        rs.getInt("scflag")
                );
                result.add(job);
            }
        }
        catch (SQLException e) {
            System.out.println("数据库连接失败，检查sql语句");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {//打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,stat,rs);
        }
        return result;
    }

    @Override
    public List<Job> queryJobBysId(String _sid) {
        List<Job> result = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "select * from job where sid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _sid);
            rs = pstmt.executeQuery();   //获取结果集
            while (rs.next())            //一个学生有多科成绩，所以有多条记录
            {
                Job job = new Job(
                        rs.getString("sid"),
                        rs.getString("iid"),
                        rs.getString("job"),
                        rs.getInt("scflag")
                );
                result.add(job);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {                //打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,pstmt,rs);  //PreparedStatement继承了Statement接口，所以可以传入PreparedStatement对象到 Statement stat 中
        }
        return result;
    }

    @Override
    public List<Job> queryJobByiId(String _iid) {
        List<Job> result = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "select * from job where iid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _iid);
            rs = pstmt.executeQuery();   //获取结果集
            while (rs.next())            //每科成绩对应多个学生，所以有多条记录
            {
                Job job = new Job(
                        rs.getString("sid"),
                        rs.getString("iid"),
                        rs.getString("job"),
                        rs.getInt("scflag")
                );
                result.add(job);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {                //打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,pstmt,rs);  //PreparedStatement继承了Statement接口，所以可以传入PreparedStatement对象到 Statement stat 中
        }
        return result;
    }

    @Override
    public Job queryJobBysIdAndcId(String _sid, String _iid) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        Job job=null;
        try {
            conn = Init();
            String sql = "select * from job where sid = ? and iid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _sid);
            pstmt.setString(2, _iid);
            rs = pstmt.executeQuery();   //获取结果集
            if (rs.next())               //此时只有一个记录
            {
                job = new Job(
                        rs.getString("sid"),
                        rs.getString("iid"),
                        rs.getString("job"),
                        rs.getInt("scflag")
                );
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {                //打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,pstmt,rs);  //PreparedStatement继承了Statement接口，所以可以传入PreparedStatement对象到 Statement stat 中
        }
        return job;
    }

    @Override
    public List<Job> queryJobByName(String _sname) {
        List<Job> result = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            conn = Init();
            String sql = "select * from job j " +
                    "join student st on j.sid = st.sid " +
                    "where st.sname like ?";             //通过内联接进行查询
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + _sname + "%");
            rs = pstmt.executeQuery(); // 获取结果集
            while (rs.next()) {
                Job job = new Job(
                        rs.getString("sid"),
                        rs.getString("iid"),
                        rs.getString("job"),
                        rs.getInt("scflag")
                );
                result.add(job);
            }
        } catch (SQLException e) {
            System.out.println("数据库连接失败，检查sql语句");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally { // 打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn, pstmt, rs); // PreparedStatement继承了Statement接口，所以可以传入PreparedStatement对象到 Statement stat 中
        }
        return result;
    }

    @Override
    public int insertJob(Job _job) {
        int flag=0;
        Connection conn = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "insert into Job values(?,?,?,?)"; //一定要按顺序
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _job.getSid());
            pstmt.setString(2, _job.getIid());
            pstmt.setString(3, _job.getJob());
            pstmt.setInt(4, _job.getScflag());
            if(pstmt.executeUpdate()>0){
                flag=1;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("检查是否重复插入?");
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {                //打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,pstmt);  //没有结果集，无需关闭rs
        }
        return flag;
    }

    @Override
    public int deleteJobBysId(String _sid) {
        int flag=0;
        Connection conn = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "delete from job where sid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _sid);
            if(pstmt.executeUpdate()>0){
                flag=1;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("检查是否重复删除?");
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {                //打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,pstmt);  //没有结果集，无需关闭rs
        }
        return flag;
    }

    @Override
    public int deleteJobByiId(String _iid) {
        int flag=0;
        Connection conn = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "delete from job where iid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _iid);
            if(pstmt.executeUpdate()>0){
                flag=1;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("检查是否重复删除?");
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {                //打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,pstmt);  //没有结果集，无需关闭rs
        }
        return flag;
    }

    @Override
    public int deleteJob(String _sid, String _iid) {
        int flag=0;
        Connection conn = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "delete from job where sid = ? and iid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _sid);
            pstmt.setString(2, _iid);
            if(pstmt.executeUpdate()>0){
                flag=1;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("检查是否重复删除?");
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {                //打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,pstmt);  //没有结果集，无需关闭rs
        }
        return flag;
    }

    @Override
    public List<JobDto> queryJobDtoAll() {
        List<JobDto> result = new ArrayList<>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Init();
            stat = conn.createStatement();
//            String sql = "select sc.sid as sid, " +
//                    "sc.iid as iid, " +
//                    "stu.sname as sname, " +
//                    "cour.cname as cname, " +
//                    "sc.job as job " +
//                    "from job as sc " +
//                    "inner join student as stu on sc.sid = stu.sid " +
//                    "inner join course as cour on sc.iid = cour.iid";

            //# 创建数据库视图：是一张虚表(就是由上面的sql语句构成的一张表)
            //# 用于 快捷 查询多张表的数据（复杂/复合查询）（减少重复性）
            //直接查询数据库视图，不需要再写sql语句，使代码更简洁
            String sql = "select * from jobView";
            System.out.println("SQL语句：" + sql);
            rs = stat.executeQuery(sql);//获取结果集
            while (rs.next()){
                JobDto jobDto = new JobDto(
                        rs.getString("sid"),
                        rs.getString("iid"),
                        rs.getString("sname"),
                        rs.getString("iname"),
                        rs.getString("job")
                );
                result.add(jobDto);
            }
        }
        catch (SQLException e) {
            System.out.println("数据库连接失败，检查sql语句");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {//打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,stat,rs);
        }
        return result;
    }

    @Override
    public List<JobDto> queryJobDtoStuBySid(String _sid) {
        List<JobDto> result = new ArrayList<>();
        PreparedStatement pstmt=null;
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Init();
            //# 创建数据库视图：是一张虚表(就是由上面的sql语句构成的一张表)
            //# 用于 快捷 查询多张表的数据（复杂/复合查询）（减少重复性）
            //直接查询数据库视图，不需要再写sql语句，使代码更简洁
            String sql = "select * from jobView where sid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _sid);
            System.out.println("SQL语句：" + sql);
            rs = pstmt.executeQuery(); // 获取结果集
            while (rs.next()){
                JobDto jobDto = new JobDto(
                        rs.getString("sid"),
                        rs.getString("iid"),
                        rs.getString("sname"),
                        rs.getString("iname"),
                        rs.getString("job")
                );
                result.add(jobDto);
            }
        }
        catch (SQLException e) {
            System.out.println("数据库连接失败，检查sql语句");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {//打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,stat,rs);
        }
        return result;
    }

    @Override
    public int editJob(Job _job) {
        int flag=0;
        Connection conn = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "update Job set job=?, scflag=? where sid=? and iid=?"; //一定要按顺序,不允许修改sid和iid
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _job.getJob());
            pstmt.setInt(2, _job.getScflag());
            pstmt.setString(3, _job.getSid());
            pstmt.setString(4, _job.getIid());
            if(pstmt.executeUpdate()>0){
                flag=1;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("检查是否重复?");
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {                //打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,pstmt);  //没有结果集，无需关闭rs
        }
        return flag;
    }
}
