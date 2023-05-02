package org.plugin.model;

public class User {

    private long user_pid;
    private String user_uuid;
    private String user_name;
    private int user_money;

    public int getUser_money() {
        return user_money;
    }

    public void setUser_money(int user_money) {
        this.user_money = user_money;
    }

    public long getUser_pid() {
        return user_pid;
    }

    public void setUser_pid(long user_pid) {
        this.user_pid = user_pid;
    }

    public String getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(String user_uuid) {
        this.user_uuid = user_uuid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
