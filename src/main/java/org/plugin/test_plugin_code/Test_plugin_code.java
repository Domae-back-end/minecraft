package org.plugin.test_plugin_code;

import org.bukkit.plugin.java.JavaPlugin;
import org.plugin.Events.PlayerJoin_Event;
import org.plugin.db.DB_Connect;
import org.plugin.Events.Form_Event;

public final class Test_plugin_code extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerJoin_Event(), this);
        getServer().getPluginManager().registerEvents(new Form_Event(), this);
        getCommand("돈").setExecutor(this);

    }

    @Override
    public void onDisable() {
        DB_Connect.getInstance().close_db();
    }
}
