package com.sy.dawnfile.path;

public class Path {

    private String uuid;
    private String user_path;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUser_path() {
        return user_path;
    }

    public void setUser_path(String user_path) {
        this.user_path = user_path;
    }

    @Override
    public String toString() {
        return "Path{" +
                "uuid='" + uuid + '\'' +
                ", user_path='" + user_path + '\'' +
                '}';
    }
}
