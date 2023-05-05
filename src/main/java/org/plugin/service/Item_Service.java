package org.plugin.service;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.plugin.db.DB_Connect;
import org.plugin.model.Item;
import org.plugin.tools.Item_Option;

import java.util.ArrayList;

public class Item_Service {

    DB_Connect db = DB_Connect.getInstance();

    public ArrayList<Item> select_all() {
        ArrayList<Item> item_list = db.select_items();

        return item_list;
    }

    public ItemStack getItem(long item_pid) {
        Item item = db.find_onItem(item_pid);

        ArrayList<String> lore = new ArrayList<>();
        if(item.getItem_info().contains("&")){
            String[] lore_list = item.getItem_info().split("&");
            for(String lore_str : lore_list){
                lore.add(lore_str);
            }
        }else{
            lore.add(item.getItem_info());
        }

        return new Item_Option().setSlot(
                Material.valueOf(item.getItem_type()), item.getItem_name(), lore
        );
    }

}
