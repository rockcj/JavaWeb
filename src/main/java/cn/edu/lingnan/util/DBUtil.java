package cn.edu.lingnan.util;

import java.sql.*;
import java.util.Map;

//将工具类写成单例模式，保证有且仅有一个连接
public class DBUtil {
    public static Connection Conn=null;
    public static DBUtil dbUtil=null;

    public static String driver = null;
    public static String url = null;
    public static String user = null;
    public static String password = null;

    public static String xmlPath ="database.conf.xml";   //还原
    //public static String xmlPath ="src/main/resources/database.conf.xml";

    //静态代码块，在类加载时执行，只执行一次
    static
    {
        //若要使用相对路径则要先获取当前程序所运行的路径:
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        /* [当前线程 的 上下文类加载器 的 资源 的 当前路径] */
        xmlPath = basePath + xmlPath;//构成绝对路径  //还原

        //System.out.println("[Debug 04] " + xmlPath);
        Map<String,String> map = XMLParser.parser(xmlPath);
        driver = map.get("driver");
        url = map.get("url");
        user = map.get("user");
        password = map.get("password");
        //System.out.println("[Debug 040] " + driver + " " + url + " " + user + " " + password);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("[Debug 042] 驱动加载失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //构造方法私有化，保证单例模式
    //懒汉式单例
    private DBUtil() //可用于初始化操作
    {
        //只让本类的getDBUtil()方法调用，其他地方不能调用
    }

    public static DBUtil getDBUtil()//l懒汉式单例
    {
        if(dbUtil==null)
        {
            dbUtil=new DBUtil();
        }
        return dbUtil;
    }
    public static Connection Init() throws Exception
    {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        if (conn == null) {
            System.out.println("数据库连接失败");
            return null;
        } else {
            System.out.println("数据库连接成功");
            return conn;
        }
    }

    //PreparedStatement继承了Statement接口，所以可以传入PreparedStatement对象到 Statement stat 中
    //当然也可以使用方法重载解决该问题
    public static void Close(Connection conn, Statement stat, ResultSet rs)
    {
        try {
            if(rs != null)
                rs.close();
            if(stat != null)
                stat.close();
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("关闭数据库连接失败");
        }
    }
    //方法重载
    public static void Close(Connection conn, Statement stat)
    {
        try {
            if(stat != null)
                stat.close();
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("关闭数据库连接失败");
        }
    }
}
