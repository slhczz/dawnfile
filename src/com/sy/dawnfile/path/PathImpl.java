package com.sy.dawnfile.path;

import java.sql.SQLException;

public interface PathImpl {
    public String queryPath(String uuid) throws SQLException;
    public boolean addPath(String uuid, String user_path) throws SQLException;
}
