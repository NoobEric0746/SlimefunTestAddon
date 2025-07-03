package me.NoobEric.testaddon.block;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import me.NoobEric.testaddon.ItemGroups;
import me.NoobEric.testaddon.Researches;
import me.NoobEric.testaddon.TestAddon;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class SweetCake extends SlimefunItem {

    public SweetCake(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public static void reg(){
        ItemStack[] recipe = { new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD),
                            null, new ItemStack(Material.DIAMOND), null,
                            new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD) };

        SlimefunItemStack itemStack = new SlimefunItemStack("SWEET_CAKE", Material.CAKE,"美好祭坛","","看似是宴席，实则是饲料","§7一口回满饱食度与饱腹度","§7那么代价是什么呢?");
        SweetCake cake = new SweetCake(ItemGroups.Group1,itemStack,RecipeType.ENHANCED_CRAFTING_TABLE,recipe);
        cake.register(TestAddon.instance);
        Researches.cakeResearch.addItems(cake);
    }

    @Override
    public void preRegister() {
        BlockUseHandler blockUseHandler = this::onBlockRightClick;
        addItemHandler(blockUseHandler);
    }

    private void onBlockRightClick(PlayerRightClickEvent event) {
//        event.cancel();
        Player player = event.getPlayer();
        if(player.getFoodLevel()>=20)return;
        player.setFoodLevel(19);
        player.setSaturation(20);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,9999999,255));
        player.sendMessage("§a你好 §4待宰的肥猪");
        Location loc = event.getClickedBlock().get().getLocation();
        player.getWorld().spawnParticle(Particle.ASH,loc,40,0.5,0.5,0.5);
        BlockStorage.clearBlockInfo(loc);
        loc.getBlock().setType(Material.AIR);
    }
}
