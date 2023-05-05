package org.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.plugin.model.Item;
import org.plugin.service.Item_Service;
import org.plugin.service.Message_Service;

import java.util.ArrayList;

public class Item_Command implements CommandExecutor {

    Message_Service message = new Message_Service();
    Item_Service itemService = new Item_Service();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if (p.isOp()) {
            if (strings.length == 0) {
                p.sendMessage(message.getInfo() + "/아이템 확인 - 생성한 아이템 목록을 볼수 있다.");
                p.sendMessage(message.getInfo() + "/아이템 받기 [index번호] - 생성한 아이템을 받을수 있다.");

            } else {
                if (strings[0].equals("확인")) {
                    ArrayList<Item> item_list = itemService.select_all();
                    for (Item item : item_list) {
                        p.sendMessage("-----------------------------------------------------");
                        p.sendMessage(message.getInfo() + " index : " + item.getItem_pid());
                        p.sendMessage(message.getInfo() + " 이름 : " + item.getItem_name());
                        p.sendMessage("-----------------------------------------------------");

                    }
                } else if (strings[0].equals("받기")) {
                    ItemStack itemStack = itemService.getItem(Long.parseLong(strings[1]));
                    p.getInventory().addItem(itemStack);
                    p.sendMessage(message.getInfo() + itemStack.getItemMeta().getDisplayName() + " 아이템을 받았습니다.");
                }
            }
        } else {
            p.sendMessage(message.getInfo() + "관리자만 사용 가능한 명령어 입니다.");
        }
        return false;
    }

}
