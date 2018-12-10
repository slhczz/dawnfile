package com.sy.dawnfile.file;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface FileImpl {

    public boolean addFile(String filename,String hostname, String filepath, String fileouter) throws SQLException;
    public Files queryPath(String filename,String fileouter) throws SQLException;
    public ArrayList<Files> queryAll(String fileouter) throws SQLException;

}
