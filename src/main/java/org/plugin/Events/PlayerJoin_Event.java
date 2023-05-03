package org.plugin.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.plugin.db.DB_Connect;
import org.plugin.model.User;
import org.plugin.service.Message_Service;
import org.plugin.service.UserLeave_Service;

public class PlayerJoin_Event implements Listener {

    DB_Connect db = DB_Connect.getInstance();
    Message_Service message = new Message_Service();

    @EventHandler
    public void join_player(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(db.check_user(""+p.getUniqueId())){
            User user = new User();
            user.setUser_name(p.getName());
            user.setUser_uuid(""+p.getUniqueId());
            db.insert_user(user);
            message.first_message(p.getName());
        }else{
            message.join_message(p.getName());
        }

    }

    @EventHandler
    public void leave_player(PlayerQuitEvent e){
        Player p = e.getPlayer();
        new UserLeave_Service().user_leave(p.getUniqueId()+"");
        message.leave_message(p.getName());
    }

}
