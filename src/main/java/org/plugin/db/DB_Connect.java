package org.plugin.db;

import org.plugin.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DB_Connect {

    private static DB_Connect db;

    private final String jdbc = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/minecraft";
    private final String uid = "root";
    private final String pwd = "1234";

    Connection con;
    Statement smt;
    ResultSet rs;

    public ArrayList<User> money_winner(){
        ArrayList<User> user_winner_list = new ArrayList<>();

        try {
            rs = smt.executeQuery("select user_name,user_money, RANK() OVER (ORDER BY user_money DESC) 순위 from user");
            int count = 0;
            while(rs.next()){
                if(count == 10){
                    break;
                }
                count++;
                User user = new User();
                user.setUser_name(rs.getString("user_name"));
                user.setUser_money(rs.getInt("user_money"));
                user_winner_list.add(user);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return user_winner_list;

    }

    public boolean check_userName(String name){

        try {
            rs = smt.executeQuery("select * from user where user_name = '"+name+"'");
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return false;
    }

    public int select_nameMoney(String name){

        try {
            rs = smt.executeQuery("select user_money from user where user_name = '"+name+"'");
            if(rs.next()){
                return rs.getInt("user_money");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return -1;
    }

    public void update_money(String name, int money){

        try {
            smt.execute("update user set user_money = "+money+" where user_name = '"+name+"'");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public String message_list(String message_type){
        try {
            rs = smt.executeQuery("select * from info_message where message_title = '"+message_type+"'");
            if(rs.next()){
                return rs.getString("message_title");
            }
        }catch (Exception e){
            System.out.println(e);
        }


        return "잘못된 메세지 접근입니다.";
    }
    public boolean check_user(String uuid){
        try {
            rs = smt.executeQuery("select * from user where user_uuid = '"+uuid+"'");
            if(rs.next()){
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return true;
    }

    public void insert_user(User user){
        try{
            smt.execute("insert into user(user_uuid,user_name,user_money) values ('"+user.getUser_uuid()+"','"+user.getUser_name()+"',0)");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void close_db(){
        try {
            if (rs != null) rs.close();
            if (smt != null) smt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DB_Connect() {
        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, uid, pwd);
            smt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static DB_Connect getInstance() {
        if (db == null) {
            db = new DB_Connect();
        }
        return db;
    }

}
