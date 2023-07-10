package com.ying.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid数据源: alibaba开源的数据库连接池。功能最强大，支持监听功能。
 */
public class DBUtil {
    private static DataSource dataSource = null;

    static {
        try {
            Properties prop = new Properties();
            InputStream in = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("druid.properties");
            prop.load(in);

            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeAll(Connection conn, Statement st, ResultSet rs) {
        try{
            if(rs!=null)rs.close();
            if(st!=null)st.close();
            if(conn!=null)conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
