package com.sy.dawnfile.util;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

    private static DruidDataSource dds;

    static {
        dds = new DruidDataSource();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        Properties p = new Properties();
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dds.setDriverClassName(p.getProperty("driverClassName"));
        dds.setUrl(p.getProperty("url"));
        dds.setUsername(p.getProperty("username"));
        dds.setPassword(p.getProperty("password"));
        dds.setMaxActive(Integer.parseInt(p.getProperty("maxActive")));


    }

    public static Connection getConnection(){
        try {
            return dds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSource(){
        return dds;
    }

    public static void close(PreparedStatement pstmt, Connection conn){
        try {
            if(pstmt != null){
                pstmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs,PreparedStatement pstmt, Connection conn){
        try {
            if(rs != null){
                rs.close();
            }
            if(pstmt != null){
                pstmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
