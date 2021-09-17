package me.ohryann.SolarX.listeners;

import me.ohryann.SolarX.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

public class CooldownListener implements Listener {

    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void blockplace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();


        if (!plugin.cdtime.containsKey(uuid)) {
            if (block.getType().equals(Material.DIAMOND_BLOCK)) {
                plugin.cdtime.put(uuid, plugin.mastercd);
            }
        } else {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You still have " + ChatColor.BLUE + plugin.cdtime.get(uuid) + ChatColor.RED + "seconds left of cooldown!");
        }
    }
}
