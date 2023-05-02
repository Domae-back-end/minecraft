package org.plugin.service;

import org.bukkit.entity.Player;
import org.plugin.db.DB_Connect;
import org.plugin.model.User;

import java.util.ArrayList;

public class Money_Service {

    DB_Connect db = DB_Connect.getInstance();

    public int check_Money(String name) {
        return db.select_nameMoney(name);
    }

    public void admin_chageMoney(String name, int money) {
        db.update_money(name, money);
    }

    public int admin_minMoney(String name, int money) {
        int user_money = db.select_nameMoney(name);

        if (user_money < money) {
            return 2;
        }
        db.update_money(name, user_money - money);
        return 1;
    }

    public void admin_addMoney(String name, int money) {
        int user_money = db.select_nameMoney(name);
        db.update_money(name, user_money + money);

    }

    public boolean check_userName(String name){
        return db.check_userName(name);
    }

    public boolean user_throwMoney(String myName, String name, int money){
        int user_money = db.select_nameMoney(myName);
        int opponent_money = db.select_nameMoney(name);

        if(user_money < money){
            return false;
        }
        db.update_money(name,  opponent_money+money);
        return true;
    }

    public ArrayList<User> user_winner(){
        return db.money_winner();
    }


}
