package me.ohryann.SolarX.listeners;

import me.ohryann.SolarX.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sun.swing.MenuItemCheckIconFactory;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = event.getPlayer().getUniqueId();
        int cdtime = plugin.getConfig().getInt(uuid + ".Cooldown_left");
        if(cdtime == 0){
            return;
        } else {
            plugin.cdtime.put(uuid, cdtime);
        }

    }
    @EventHandler
    public void onJoinEC(PlayerJoinEvent event){
        Player player = event.getPlayer();

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE + plugin.ench.getName() + " I");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.GREEN + "Poison Strike");
        item.setItemMeta(meta);

        item.addUnsafeEnchantment(plugin.ench, 1);
        player.getInventory().addItem(item);
    }

}
