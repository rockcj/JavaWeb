package cn.edu.lingnan.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis工具类，用于管理SqlSession
 */
public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 加载MyBatis配置文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load MyBatis configuration", e);
        }
    }

    /**
     * 获取SqlSession
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 获取SqlSession（自动提交）
     * @return SqlSession
     */
    public static SqlSession getSqlSession(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }

    /**
     * 获取Mapper接口
     * @param mapperClass Mapper接口类
     * @param <T> 泛型类型
     * @return Mapper接口实例
     */
    public static <T> T getMapper(Class<T> mapperClass) {
        SqlSession sqlSession = getSqlSession();
        return sqlSession.getMapper(mapperClass);
    }

    /**
     * 获取Mapper接口（自动提交）
     * @param mapperClass Mapper接口类
     * @param autoCommit 是否自动提交
     * @param <T> 泛型类型
     * @return Mapper接口实例
     */
    public static <T> T getMapper(Class<T> mapperClass, boolean autoCommit) {
        SqlSession sqlSession = getSqlSession(autoCommit);
        return sqlSession.getMapper(mapperClass);
    }

    /**
     * 关闭SqlSession
     * @param sqlSession SqlSession
     */
    public static void closeSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    /**
     * 提交并关闭SqlSession
     * @param sqlSession SqlSession
     */
    public static void commitAndCloseSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * 回滚并关闭SqlSession
     * @param sqlSession SqlSession
     */
    public static void rollbackAndCloseSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}