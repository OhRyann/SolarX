package me.ohryann.SolarX.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PoisonSwordEC extends Enchantment implements Listener {

    public PoisonSwordEC (int id){
        super(id);
    }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player ){
            Player player = (Player) event.getDamager();

            ItemStack mainhand = player.getInventory().getItemInHand();

            if(mainhand.containsEnchantment(this)){
                player.getWorld().createExplosion(event.getEntity().getLocation(), 1, false);
            }
        }
    }


    @Override
    public String getName() {
        return "Poison Strike";
    }

    @Override
    public int getId(){
        return 101;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }
}
