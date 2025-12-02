package cn.edu.lingnan.dao.imp;

import cn.edu.lingnan.dao.StudentDao;
import cn.edu.lingnan.pojo.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static cn.edu.lingnan.util.DBUtil.Close;
import static cn.edu.lingnan.util.DBUtil.Init;

public class StudentDaoImpIMysql implements StudentDao {
    boolean flag = false;
    Connection conn = null;
    Statement stat = null;
    ResultSet rs = null; //结果集

    @Override
    public Student queryStudentByNameAndPassword(String _sname, String _spassword) {
        //try里面定义的都是局部变量
        Student student = null;
        try {
            conn = Init();
            stat = conn.createStatement();
            String sql = "select * from student where sname = '" + _sname + "' and spassword = '" + _spassword + "'";
            rs = stat.executeQuery(sql);//获取结果集
            if (rs.next())//判断结果集是否为空，因为结果集指针是指向查询结果的第一个元素之前，所以要判断下一个(next)是否为空，java这么设计是为了在遍历结果集时，可以使用 while (rs.next()) 循环来逐行处理数据，这种设计使得代码逻辑更加简洁和直观
            {
                student = new Student(
                        rs.getString("sid"),
                        rs.getString("sname"),
                        rs.getString("spassword"),
                        rs.getInt("sright"),
                        rs.getInt("stflag")
                );
            }
//            String sql = "select * from student where sname = ? and spassword = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, _sname);
//            pstmt.setString(2, _spassword);
//            ResultSet rs = pstmt.executeQuery();//使用prepareStatement进行预编译安全性更高，可以使用?占位符
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {//打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,stat,rs);
        }
        return student;
    }

    @Override
    public List<Student> queryAllStudentAll(int log) {
        List<Student> result = new ArrayList<>();
        try {
            conn = Init();
            stat = conn.createStatement();
            String sql = "select * from student";
            if(log == 0)
            {
                sql = sql+" where COALESCE(stflag, 0) = 0";
            } else if (log == 1) {
                sql = sql+" where stflag = 1";
            }
            rs = stat.executeQuery(sql);//获取结果集
            while (rs.next()){
                Student student = new Student(
                        rs.getString("sid"),
                        rs.getString("sname"),
                        rs.getString("spassword"),
                        rs.getInt("sright"),
                        rs.getInt("stflag")
                );
                result.add(student);
            }
        }
         catch (Exception e) {
            throw new RuntimeException(e);
        } finally {//打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,stat,rs);
        }
        return result;
    }

    @Override
    public Student queryStudentById(String _sid) {
        Student stu = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "select * from student where sid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _sid);
            rs = pstmt.executeQuery();   //获取结果集
//            if(!rs.next()){
//                System.out.println("无改学生对象");//判断不能放这里，会导致指针提前位移，循环结束
//            }
//            while (rs.next()){           //只有1条记录
//                stu = new Student(
//                        rs.getString("sid"),
//                        rs.getString("sname"),
//                        rs.getString("spassword"),
//                        rs.getInt("sright"),
//                        rs.getInt("stflag")
//                );
//            }

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

    @Override
    public List<Student> queryStudentByName(String _sname) {
        List<Student> result = new ArrayList<>();
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            pstmt = conn.prepareStatement("select * from student where sname like ?");//不要再这里加%
            pstmt.setString(1, "%"+_sname+"%");        //模糊查询不要忘记加 % ，否则为精确查询
            rs = pstmt.executeQuery();   //获取结果集                   //System.out.println("sql=" + pstmt);可以看到执行的sql语句，检查是否写错
            while (rs.next()){
                Student student = new Student(
                        rs.getString("sid"),
                        rs.getString("sname"),
                        rs.getString("spassword"),
                        rs.getInt("sright"),
                        rs.getInt("stflag")
                );
                result.add(student);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {//打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,pstmt,rs);
        }
        return result;
    }

    @Override
    public int insertStudent(Student _student) {
        int flag=0;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "insert into student values(?,?,?,?,?)"; //一定要按顺序
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
    public int deleteStudentById(String _sid) {
        int flag=0;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            //事务处理
            conn.setAutoCommit(false);

            String sql = "delete from student where sid = ?"; //一定要按顺序
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _sid);
            pstmt.executeUpdate();

            conn.setAutoCommit(true);//提交事务
            flag=1;
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("检查是否重复删除?");
            try {
                conn.rollback();//报错则回滚事务
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
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
    public int editStudentPassword(String _sid, String _NewPassword) {
        int flag=0;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "update Student set spassword=? where sid=?"; //一定要按顺序,不允许修改sid和iid
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _NewPassword);
            pstmt.setString(2, _sid);
            if(pstmt.executeUpdate()>0){
                flag=1;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("检查sql语句");
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
    public int editStudentName(String _sid, String _NewName) {
        int flag=0;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "update Student set sname=? where sid=?"; //一定要按顺序,不允许修改sid和iid
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _NewName);
            pstmt.setString(2, _sid);
            if(pstmt.executeUpdate()>0){
                flag=1;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("检查sql语句");
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
    public int editStudentFlag(String _sid, int _NewFlag) {
        int flag=0;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "update Student set stflag=? where sid=?"; //一定要按顺序,不允许修改sid和iid
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, _NewFlag);
            pstmt.setString(2, _sid);
            if(pstmt.executeUpdate()>0){
                flag=1;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("检查sql语句");
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
    public int updateStudent(Student _student) {
        int flag=0;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "update Student set sname=?,spassword=?,sright=?,stflag=? where sid=?"; //一定要按顺序,不允许修改sid和iid
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _student.getSname());
            pstmt.setString(2, _student.getSpassword());
            pstmt.setInt(3, _student.getSright());
            pstmt.setInt(4, _student.getStflag());
            pstmt.setString(5, _student.getSid());
            if(pstmt.executeUpdate()>0){
                flag=1;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("检查sql语句");
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
