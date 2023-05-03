package org.plugin.db;

import org.plugin.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DB_Connect {

    private static DB_Connect db;

    private final String jdbc = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/minecraft";
    private final String uid = "1234";
    private final String pwd = "1234";

    Connection con;
    Statement smt;
    ResultSet rs;

    public void leave_user(String uuid){

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, uid, pwd);
            smt = con.createStatement();
            smt.execute("update user set user_leaveday = NOW() where user_uuid = '"+uuid+"'");
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (smt != null) smt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<User> money_winner(){
        ArrayList<User> user_winner_list = new ArrayList<>();

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, uid, pwd);
            smt = con.createStatement();
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
        }finally {
            try {
                if (rs != null) rs.close();
                if (smt != null) smt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user_winner_list;

    }


    public int select_nameMoney(String name){

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, uid, pwd);
            smt = con.createStatement();
            rs = smt.executeQuery("select user_money from user where user_name = '"+name+"'");
            if(rs.next()){
                return rs.getInt("user_money");
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (smt != null) smt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    public void update_money(String name, int money){

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, uid, pwd);
            smt = con.createStatement();
            smt.execute("update user set user_money = "+money+" where user_name = '"+name+"'");
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (smt != null) smt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public String message_list(String message_type){
        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, uid, pwd);
            smt = con.createStatement();
            rs = smt.executeQuery("select * from info_message where message_type = '"+message_type+"'");
            if(rs.next()){
                return rs.getString("message_info");
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (smt != null) smt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return "잘못된 메세지 접근입니다.";
    }
    public boolean check_user(String uuid){
        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, uid, pwd);
            smt = con.createStatement();
            rs = smt.executeQuery("select * from user where user_uuid = '"+uuid+"'");
            if(rs.next()){
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (smt != null) smt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean check_userName(String name){

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, uid, pwd);
            smt = con.createStatement();
            rs = smt.executeQuery("select * from user where user_name = '"+name+"'");
            return rs.next();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (smt != null) smt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public void insert_user(User user){
        try{
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, uid, pwd);
            smt = con.createStatement();
            smt.execute("insert into user(user_uuid,user_name,user_money,user_createday) values ('"+user.getUser_uuid()+"','"+user.getUser_name()+"',0,NOW())");
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (smt != null) smt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DB_Connect getInstance() {
        if (db == null) {
            db = new DB_Connect();
        }
        return db;
    }

}
