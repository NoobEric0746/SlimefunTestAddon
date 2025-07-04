package me.NoobEric.testaddon.item;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.NoobEric.testaddon.ItemGroups;
import me.NoobEric.testaddon.Researches;
import me.NoobEric.testaddon.TestAddon;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.awt.*;

public class IronPotion extends SlimefunItem {
    public IronPotion(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public static void reg(){
        SlimefunItemStack itemStack = new SlimefunItemStack("IRON_POTION", Material.POTION,"铁皮药水","","铁");

        PotionMeta meta = (PotionMeta) itemStack.getItemMeta();
        meta.setColor(Color.GRAY);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemStack.setItemMeta(meta);

        ItemStack[] recipe = { new ItemStack(Material.IRON_BLOCK), SlimefunItems.LAVA_CRYSTAL , new ItemStack(Material.IRON_BLOCK),
                                SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.GLASS_BOTTLE), SlimefunItems.MAGIC_LUMP_2,
                                new ItemStack(Material.IRON_BLOCK), SlimefunItems.LAVA_CRYSTAL , new ItemStack(Material.IRON_BLOCK) };

        IronPotion potion = new IronPotion(ItemGroups.Group1,itemStack,RecipeType.MAGIC_WORKBENCH,recipe);
        potion.register(TestAddon.instance);
        Researches.potionResearch.addItems(potion);
    }

    @Override
    public void preRegister() {
        ItemConsumptionHandler itemConsumptionHandler = this::OnItemConsumed;
        addItemHandler(itemConsumptionHandler);
    }

    private void OnItemConsumed(PlayerItemConsumeEvent event, Player player, ItemStack itemStack) {
        event.setCancelled(true);
        player.getInventory().setItem(event.getHand(), new ItemStack(Material.GLASS_BOTTLE));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,60*20,3));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,60*20,0));

    }

}
