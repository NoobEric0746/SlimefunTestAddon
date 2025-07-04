package me.NoobEric.testaddon.item;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.NoobEric.testaddon.ItemGroups;
import me.NoobEric.testaddon.TestAddon;
import me.NoobEric.testaddon.block.FireCake;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GoUpScroll extends SlimefunItem {
    public GoUpScroll(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public static void reg(){
        SlimefunItemStack itemStack = new SlimefunItemStack("GO_UP_SCROLL", Material.PAPER,"卷轴:通天术","","传送到头顶的方块上");
        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
        itemStack.setAmount(4);

        ItemStack[] recipe = { null, new ItemStack(Material.PAPER), null,
                new ItemStack(Material.PAPER), SlimefunItems.ENDER_RUNE, new ItemStack(Material.PAPER),
                null , new ItemStack(Material.PAPER), null };

        GoUpScroll scroll = new GoUpScroll(ItemGroups.Group1,itemStack,RecipeType.ENHANCED_CRAFTING_TABLE,recipe);
        scroll.register(TestAddon.instance);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::OnItemUseRightClick;
        addItemHandler(itemUseHandler);
    }
    private void OnItemUseRightClick(PlayerRightClickEvent event) {
        event.cancel();
        if(event.getHand() == EquipmentSlot.OFF_HAND){return;}
        Player player = event.getPlayer();
        boolean flag = false;
        Location pLoc = player.getLocation();
        for(float i=2;i<128;i+=0.2){
            Location loc = new Location(player.getWorld(),pLoc.getX(),pLoc.getY()+i,pLoc.getZ());
            particle(i*2,loc, player.getWorld());
            if(isSafe(loc)){
                if(flag){
                    Slimefun.runSync(() -> player.teleport(loc), 1);

                    event.getItem().setAmount(event.getItem().getAmount()-1);
                    return;
                }
            }
            else{
                flag=true;
            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
        if(flag==false){
            player.sendMessage(ChatColor.RED+"128格内没有方块");
        }
    }
    private boolean isSafe(Location loc){
        return loc.getBlock().getType()== Material.AIR ||
                loc.getBlock().getType()== Material.CAVE_AIR;
    }
    private void particle(double w, Location loc, World world){
        float d=1;
        Location loc1 = new Location(world,loc.getX()+Math.cos(w)*d,loc.getY(),loc.getZ()+Math.sin(w));
        Location loc2 = new Location(world,loc.getX()-Math.cos(w)*d,loc.getY(),loc.getZ()-Math.sin(w));
        world.spawnParticle(Particle.VILLAGER_HAPPY,loc1,1);
        world.spawnParticle(Particle.VILLAGER_HAPPY,loc2,1);
    }
}
