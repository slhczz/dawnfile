package com.sy.dawnfile.path;

import com.sy.dawnfile.util.JdbcUtil;
import jdk.nashorn.internal.scripts.JD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class PathIf extends Path implements PathImpl {
    @Override
    public String queryPath(String uuid) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select * from dawn_user_path where uuid = ?";
        Path query = qr.query(sql, new BeanHandler<Path>(Path.class), uuid);
        if(query != null){
            return query.getUser_path();
        }
        return null;
    }

    @Override
    public boolean addPath(String uuid, String user_path) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "insert into dawn_user_path values(?,?)";
        int update = qr.update(sql, uuid, user_path);
        if(update > 0){
            return true;
        }
        return false;
    }
}
