package org.plugin.service;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.plugin.model.Message;

public class Message_Service {

    Message message = new Message();

    public String getInfo(){
        return message.getInfo();
    }
    public void first_message(String name){
        message.setPlayer(name);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(message.getFirst_info());
        }
    }
    public void join_message(String name){
        message.setPlayer(name);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(message.getHello_info());
        }
    }
    public void leave_message(String name){
        message.setPlayer(name);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(message.getLeave_info());
        }
    }

    public void send_all_message(String message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(message);
        }
    }

}
