package org.plugin.model;

import org.plugin.db.DB_Connect;

import java.util.HashMap;

public class Message {

    private String player="";
    private String hello_info=" 님 서버에 오신걸 환영합니다.";
    private String first_info=" 님 서버에 처음 오신걸 환영합니다.";
    private String leave_info=" 님 서버에 나가셨습니다.";

    private String info="[!]";

    public Message(){
        DB_Connect db = DB_Connect.getInstance();
        info = db.message_list("info");
    }


    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getHello_info() {
        return hello_info;
    }
    public void setHello_info(String hello_info) {
        this.hello_info = hello_info;
    }

    public String getFirst_info() {
        return first_info;
    }

    public void setFirst_info(String first_info) {
        this.first_info = first_info;
    }

    public String getLeave_info() {
        return leave_info;
    }

    public void setLeave_info(String leave_info) {
        this.leave_info = leave_info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
