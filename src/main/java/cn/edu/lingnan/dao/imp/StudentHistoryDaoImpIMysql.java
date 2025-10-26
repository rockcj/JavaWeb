package cn.edu.lingnan.dao.imp;

import cn.edu.lingnan.dao.StudentHistoryDao;
import cn.edu.lingnan.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static cn.edu.lingnan.util.DBUtil.Close;
import static cn.edu.lingnan.util.DBUtil.Init;

public class StudentHistoryDaoImpIMysql implements StudentHistoryDao {
    @Override
    public int insertStudent(Student _student) {
        int flag=0;
        PreparedStatement pstmt=null;
        Connection conn = null;
        try {
            conn = Init();
            String sql = "insert into studenthistory values(?,?,?,?,?)"; //一定要按顺序
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _student.getSid());
            pstmt.setString(2, _student.getSname());
            pstmt.setString(3, _student.getSpassword());
            pstmt.setInt(4, _student.getSright());
            pstmt.setInt(5, _student.getStflag());
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
    public Student queryStudentById(String _sid) {
        ResultSet rs = null; //结果集
        Connection conn = null;
        Student stu = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "select * from studenthistory where sid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _sid);
            rs = pstmt.executeQuery();   //获取结果集

            //改进，优化，使用if判断，因为只有1条记录，将原来的rs.next()改为rs即可
            if(!rs.next()){
                System.out.println("无该学生对象");
                return stu;
            }
            if(rs!=null){           //只有1条记录,rs已经移动到了next的位置
                stu = new Student(
                        rs.getString("sid"),
                        rs.getString("sname"),
                        rs.getString("spassword"),
                        rs.getInt("sright"),
                        rs.getInt("stflag")
                );
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("数据库连接失败，检查sql语句");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {                //打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,pstmt,rs);  //PreparedStatement继承了Statement接口，所以可以传入PreparedStatement对象到 Statement stat 中
        }
        return stu;
    }
}
