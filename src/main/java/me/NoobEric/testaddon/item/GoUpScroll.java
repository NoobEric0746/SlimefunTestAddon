package me.NoobEric.testaddon.item;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.NoobEric.testaddon.ItemGroups;
import me.NoobEric.testaddon.TestAddon;
import me.NoobEric.testaddon.block.FireCake;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GoUpScroll extends SlimefunItem {
    public GoUpScroll(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public static void reg(){
        SlimefunItemStack itemStack = new SlimefunItemStack("GO_UP_SCROLL", Material.PAPER,"Go Up","","weee~");

        ItemStack[] recipe = { new ItemStack(Material.PAPER), null, new ItemStack(Material.PAPER),
                null, new ItemStack(Material.DIAMOND), null,
                new ItemStack(Material.PAPER), null, new ItemStack(Material.PAPER) };

        GoUpScroll scroll = new GoUpScroll(ItemGroups.Group1,itemStack,RecipeType.ENHANCED_CRAFTING_TABLE,recipe);
        scroll.register(TestAddon.instance);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::OnItemUseRightClick;
        addItemHandler(itemUseHandler);
    }
    private void OnItemUseRightClick(PlayerRightClickEvent event) {
        Player player = event.getPlayer();
        boolean flag = false;
        Location pLoc = player.getLocation();
        for(float i=2;i<128;i+=0.2){
            Location loc = new Location(player.getWorld(),pLoc.getX(),pLoc.getY()+i,pLoc.getZ());
            particle(i*2,loc, player.getWorld());
            if(isSafe(loc)){
                if(flag){
                    player.teleport(loc);
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
            player.sendMessage(ChatColor.RED+"No Block");
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
