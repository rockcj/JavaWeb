package cn.edu.lingnan.dao.imp;

import cn.edu.lingnan.dao.JobHistoryDao;
import cn.edu.lingnan.pojo.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static cn.edu.lingnan.util.DBUtil.Close;
import static cn.edu.lingnan.util.DBUtil.Init;

public class JobHistoryDaoImpIMysql implements JobHistoryDao {
    @Override
    public int insertScoreHistory(Job _score) {
        int flag=0;
        Connection conn = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "insert into ScoreHistory values(?,?,?,?)"; //一定要按顺序
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _score.getSid());
            pstmt.setString(2, _score.getIid());
            pstmt.setString(3, _score.getJob());
            pstmt.setInt(4, _score.getScflag());
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
}
