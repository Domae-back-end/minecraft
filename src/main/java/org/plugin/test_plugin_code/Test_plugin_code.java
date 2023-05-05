package org.plugin.test_plugin_code;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugin.Events.PlayerJoin_Event;
import org.plugin.command.Item_Command;
import org.plugin.command.Money_Command;
import org.plugin.db.DB_Connect;
import org.plugin.Events.Form_Event;

public final class Test_plugin_code extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerJoin_Event(), this);
        getServer().getPluginManager().registerEvents(new Form_Event(), this);
        getCommand("돈").setExecutor(new Money_Command());
        getCommand("아이템").setExecutor(new Item_Command());

    }

    @Override
    public void onDisable() {
    }
}
