package cn.edu.lingnan.dao.imp;

import cn.edu.lingnan.dao.ItemDao;
import cn.edu.lingnan.pojo.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static cn.edu.lingnan.util.DBUtil.Close;
import static cn.edu.lingnan.util.DBUtil.Init;


public class ItemDaoImpIMysql implements ItemDao {
    @Override
    public List<Item> queryAllItem() {
        List<Item> result = new ArrayList<>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Init();
            stat = conn.createStatement();
            String sql = "select * from item";
            rs = stat.executeQuery(sql);//获取结果集
            while (rs.next()){
                Item it = new Item(
                        rs.getString("iid"),
                        rs.getString("iname"),
                        rs.getInt("iflag")
                );
                result.add(it);
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
    public Item queryItemById(String _iid) {
        Item it = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "select * from item where iid = ?";
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

    @Override
    public List<Item> queryItemByName(String _iname) {
        List<Item> result = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "select * from item where iname like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%"+_iname+"%");
            rs = pstmt.executeQuery();   //获取结果集
            while (rs.next()){           //只有1条记录
                Item item = new Item(
                        rs.getString("iid"),
                        rs.getString("iname"),
                        rs.getInt("iflag")
                );
                result.add(item);
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
    public int insertItem(Item _item) {
        int flag=0;
        PreparedStatement pstmt=null;
        Connection conn = null;
        try {
            conn = Init();
            String sql = "insert into item values(?,?,?)"; //一定要按顺序
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
    public int deleteItemById(String _iid) {
        int flag=0;
        Connection conn = null;
        PreparedStatement pstmt=null;
        try {
            conn = Init();
            String sql = "delete from item where iid = ?"; //一定要按顺序
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
    public int editItem(Item _item) {
        int flag=0;
        PreparedStatement pstmt=null;
        Connection conn = null;
        try {
            conn = Init();
            String sql = "update item set iname=?, iflag=? where iid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, _item.getIname()); // 第一个参数对应iname
            pstmt.setInt(2, _item.getIflag());    // 第二个参数对应iflag
            pstmt.setString(3, _item.getIid());   // 第三个参数对应where条件
            if(pstmt.executeUpdate()>0){
                flag=1;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        } catch (SQLException e) {
            System.out.println("检查id是否存在?");
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
