package org.plugin.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.plugin.service.Message_Service;

public class Form_Event implements Listener {
    Message_Service message = new Message_Service();
    @EventHandler
    public void form(PlayerInteractEvent event){

        Player player = event.getPlayer();
        Action action = event.getAction();
        Block block = event.getClickedBlock();

        if (action == Action.RIGHT_CLICK_BLOCK && block.getType() == Material.FARMLAND) {
            ItemStack handItem = player.getInventory().getItemInMainHand();

            if (handItem.getType() == Material.WHEAT_SEEDS) {
                event.setCancelled(true); // 이벤트 취소

                Block crop = block.getRelative(BlockFace.UP);
                crop.setType(Material.WHEAT);
                player.sendMessage("§a밀 씨앗을 심었습니다!");
            }
        }

    }

}
