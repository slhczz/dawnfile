package com.sy.dawnfile.file;

import com.sy.dawnfile.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileIf extends Files implements FileImpl {
    @Override
    public boolean addFile(String filename,String hostname, String filepath, String fileouter) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "insert into dawn_file (filename,hostname,filepath,fileouter,crteatedata,fileurl) values(?,?,?,?,?,?)";
        String s = UUID.randomUUID().toString();
        int i = s.lastIndexOf("-");
        String url = s.substring(i+1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        int update = qr.update(sql, filename, hostname,filepath, fileouter,df.format(new Date()),url);
        if(update>0){
            return true;
        }
        return false;
    }

    @Override
    public Files queryPath(String filename,String fileouter) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select * from dawn_file where hostname = ? and fileouter = ?";
        Files file = qr.query(sql, new BeanHandler<Files>(Files.class), filename,fileouter);
        if(file != null){
            return file;
        }
        return null;
    }

    @Override
    public ArrayList<Files> queryAll(String fileouter) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select * from dawn_file where fileouter = ?";
        List<Files> files = qr.query(sql, new BeanListHandler<Files>(Files.class), fileouter);
        if(files != null){
            return (ArrayList<Files>) files;
        }
        return null;
    }
}
