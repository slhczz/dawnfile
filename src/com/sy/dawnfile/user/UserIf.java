package com.sy.dawnfile.user;

import com.sy.dawnfile.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @Author: Administrator
 * @Date: 2018/12/6 19:52
 * @Version 1.0
 */
public class UserIf extends User implements UserImpl {
    @Override
    public boolean register(String phone, String email, String password,String uuid) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "insert into dawn_user(phone,password,email,username,uuid) values(?,?,?,?,?)";
        int update = qr.update(sql, phone, password, email, ("sy" + phone),uuid);
        if(update > 0){
            return true;
        }
        return false;
    }

    @Override
    public User login(String mobile, String password) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select * from dawn_user where phone = ? || email = ?  and password = ?";
//        System.out.println(mobile+":"+password);
        User user = qr.query(sql, new BeanHandler<User>(User.class), mobile, mobile, password);
        if(user != null){
            return user;
        }
        return null;
    }

    @Override
    public String queryUuid(String account) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select * from dawn_user where phone = ? || email =?";
        User user = qr.query(sql, new BeanHandler<User>(User.class),account,account );
        if(user != null){
            return user.getUuid();
        }
        return null;
    }
}
