package org.plugin.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Chat_Event implements Listener {


    @EventHandler
    public void chatting(AsyncPlayerChatEvent e) throws IOException {
        Player p = e.getPlayer();
    }


}
