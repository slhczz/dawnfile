package com.sy.dawnfile.user;

import java.sql.SQLException;

/**
 * @Author: Administrator
 * @Date: 2018/12/6 19:49
 * @Version 1.0
 */
public interface UserImpl {

    public boolean register(String phone, String email, String password, String uuid) throws SQLException;
    public User login(String mobile, String password) throws SQLException;
    public String queryUuid(String account) throws SQLException;


}
