package me.NoobEric.testaddon.event;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

public class MyListener implements Listener {
    Random rand = new Random();

    @EventHandler
    public void OnItemAttackLeftClick(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player player && event.getEntity() instanceof LivingEntity entity){

            ItemStack item = player.getInventory().getItemInMainHand();
            SlimefunItem sfItem = SlimefunItem.getByItem(item);
            SlimefunItem sfItemOffhand = SlimefunItem.getByItem(player.getInventory().getItemInOffHand());
            if(sfItem != null && sfItem.getId().equals("STICKY_SWORD")){
                if(!sfItem.canUse(player,false))return;
                double tmp=0;
                if(sfItemOffhand.getId().equals("HACK_CORE")){
                    tmp=0.5;
                }
                if(rand.nextDouble()>0.15+tmp)return;
                EntityEquipment equip = entity.getEquipment();
                if(equip!=null && equip.getItemInMainHand().getType() != Material.AIR){
                    if(player.getInventory().firstEmpty()==-1){
                        player.sendMessage(ChatColor.RED + "背包已满");
                        return;
                    }

                    ItemStack is = equip.getItemInMainHand();
                    player.getInventory().addItem(is.clone());
                    equip.setItemInMainHand(null);
                    player.sendMessage("§a黏!");
                    entity.getWorld().spawnParticle(Particle.SLIME,entity.getLocation(),40,0.5,0.5,0.5);
                }
            }

            if(sfItem != null && sfItem.getId().equals("BOUNCY_SWORD")){
                if(!sfItem.canUse(player,false))return;
                double tmp=0;
                if(sfItemOffhand.getId().equals("HACK_CORE")){
                    tmp=0.5;
                }
                if(rand.nextDouble()>0.3+tmp)return;
                EntityEquipment equip = entity.getEquipment();
                if(equip!=null && equip.getChestplate().getType() != Material.AIR){
                    ItemStack is = equip.getChestplate();
                    Location loc = entity.getLocation();
                    loc.getWorld().dropItem(loc,is);
                    equip.setChestplate(null);
                }
                KnockBack(player,entity,2.0);
                player.sendMessage("§a弹!");
                entity.getWorld().spawnParticle(Particle.SLIME,entity.getLocation(),40,0.5,0.5,0.5);
            }

        }
    }
    private void KnockBack(Player damager,LivingEntity victim,double strength){

        // 计算基础方向
        Vector direction = victim.getLocation().toVector()
                .subtract(damager.getLocation().toVector())
                .normalize();

        // 叠加附魔效果
        ItemStack weapon = damager.getInventory().getItemInMainHand();
        if (weapon.containsEnchantment(Enchantment.KNOCKBACK)) {
            strength += weapon.getEnchantmentLevel(Enchantment.KNOCKBACK) * 0.3;
        }

        // 应用抗性
        double resistance = victim.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getValue();
        direction.multiply(strength * (1.0 - resistance)).setY(0.2);

        // 执行击退
        victim.setVelocity(direction);
    }

}
