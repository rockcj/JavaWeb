package cn.edu.lingnan.dao.imp;

import cn.edu.lingnan.dao.ItemHistoryDao;
import cn.edu.lingnan.pojo.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static cn.edu.lingnan.util.DBUtil.Close;
import static cn.edu.lingnan.util.DBUtil.Init;


//用于删除时存储历史记录
public class ItemHistoryDaoImpIMysql implements ItemHistoryDao {
    @Override
    public int insertItemHistory(Item _item) {
        int flag=0;
        PreparedStatement pstmt=null;
        Connection conn = null;
        try {
            conn = Init();
            String sql = "insert into itemhistory values(?,?,?)"; //一定要按顺序
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _item.getIid());
            pstmt.setString(2, _item.getIname());
            pstmt.setInt(3, _item.getIflag());
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
    public Item queryItemById(String _iid) {
        Item it = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "select * from itemhistory where iid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _iid);
            rs = pstmt.executeQuery();   //获取结果集
            if(!rs.next()){
                System.out.println("无该项目对象");
                return it;
            }
            if(rs!=null){           //只有1条记录,rs已经移动到了next的位置
                it = new Item(
                        rs.getString("iid"),
                        rs.getString("iname"),
                        rs.getInt("iflag")
                );
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {                //打开、关闭都相当于栈，最后打开的，最先关，最先打开的，最后关（相当于进出栈）
            Close(conn,pstmt,rs);  //PreparedStatement继承了Statement接口，所以可以传入PreparedStatement对象到 Statement stat 中
        }
        return it;
    }
}
