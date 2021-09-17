package me.ohryann.SolarX.listeners;

import me.ohryann.SolarX.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {

    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler

    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        plugin.getConfig().set(uuid + ".Cooldown_left", plugin.cdtime.get(uuid));
        plugin.saveConfig();
        plugin.cdtime.remove(uuid);
    }
}
