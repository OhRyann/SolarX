package me.ohryann.SolarX;

import me.ohryann.SolarX.enchantments.PoisonSwordEC;
import me.ohryann.SolarX.listeners.CooldownListener;
import me.ohryann.SolarX.listeners.PlayerJoinListener;
import me.ohryann.SolarX.listeners.PlayerQuitListener;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    public HashMap<UUID, Integer> cdtime = new HashMap<UUID, Integer>();
    public PoisonSwordEC ench = new PoisonSwordEC(101);

    public int mastercd = 5;


    public void onEnable() {

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new CooldownListener(), this);
        getServer().getPluginManager().registerEvents(ench, this);


        loadEnchantments();
        loadConfig();
        cdrunnable();
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();

    }

    @SuppressWarnings("unchecked")
    public void onDisable(){
        try{
            Field byIdField = Enchantment.class.getDeclaredField("byId");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byIdField.setAccessible(true);
            byNameField.setAccessible(true);
            HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
            HashMap<Integer, Enchantment> byName = (HashMap<Integer, Enchantment>) byNameField.get(null);

            if(byId.containsKey(ench.getId())){
                byId.remove(ench.getId());

            }
            if(byName.containsKey(ench.getName())){
                byName.remove(ench.getName());
            }
        } catch (Exception ignored) {

        }
    }

    private void loadEnchantments() {
        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Enchantment.registerEnchantment(ench);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cdrunnable() {
        new BukkitRunnable() {

            @Override
            public void run() {

                if (cdtime.isEmpty()) {
                    return;
                }
                for (UUID uuid : cdtime.keySet()) {
                    int timeleft = cdtime.get(uuid);

                    if (timeleft <= 0) {
                        cdtime.remove(uuid);
                    } else {
                        cdtime.put(uuid, timeleft - 1);
                    }


                }
            }
        }.runTaskTimer(this, 0, 20);
    }

}
