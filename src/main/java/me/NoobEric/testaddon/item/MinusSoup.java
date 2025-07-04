package me.NoobEric.testaddon.item;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.NoobEric.testaddon.ItemGroups;
import me.NoobEric.testaddon.TestAddon;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MinusSoup extends SlimefunItem {
    public MinusSoup(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public static void reg(){
        SlimefunItemStack itemStack = new SlimefunItemStack("MINUS_SOUP", Material.BEETROOT_SOUP,"减法汤","","健胃消食");

        ItemStack[] recipe = { null , new ItemStack(Material.FERMENTED_SPIDER_EYE) , null,
                null, new ItemStack(Material.BEETROOT_SOUP), null,
                null, new ItemStack(Material.ROTTEN_FLESH), null };

        MinusSoup soup = new MinusSoup(ItemGroups.Group1,itemStack,RecipeType.ENHANCED_CRAFTING_TABLE,recipe);
        soup.register(TestAddon.instance);
    }

    @Override
    public void preRegister() {
        ItemConsumptionHandler itemConsumptionHandler = this::OnItemConsumed;
        addItemHandler(itemConsumptionHandler);
    }

    private void OnItemConsumed(PlayerItemConsumeEvent event, Player player, ItemStack itemStack) {
        event.setCancelled(true);
        player.getInventory().setItem(event.getHand(), new ItemStack(Material.BOWL));
        player.setFoodLevel(Math.max(player.getFoodLevel() - 6,0));
    }

}
