package org.plugin.service;

import org.bukkit.entity.Player;
import org.plugin.db.DB_Connect;

public class Money_Service {

    DB_Connect db = DB_Connect.getInstance();

    public int check_Money(String name){
        return db.select_nameMoney(name);
    }

    public void admin_chageMoney(String name, int money){
        db.update_money(name, money);
    }
    public void admin_minMoney(String name, int money){

    }
    public void admin_addMoney(String name, int money){

    }

}
