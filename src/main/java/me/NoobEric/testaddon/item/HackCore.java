package me.NoobEric.testaddon.item;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.NoobEric.testaddon.ItemGroups;
import me.NoobEric.testaddon.Researches;
import me.NoobEric.testaddon.TestAddon;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HackCore extends SlimefunItem {
    public HackCore(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public static void reg(){
        SlimefunItemStack itemStack = new SlimefunItemStack("HACK_CORE", Material.CONDUIT,"骇客核心","","§7+50%判定成功率");

        ItemStack[] recipe = { new ItemStack(Material.PAPER), null, new ItemStack(Material.PAPER),
                null, new ItemStack(Material.DIAMOND), null,
                new ItemStack(Material.PAPER), null, new ItemStack(Material.PAPER) };

        HackCore core = new HackCore(ItemGroups.Group1,itemStack,RecipeType.ENHANCED_CRAFTING_TABLE,recipe);
        core.register(TestAddon.instance);
        Researches.hackCoreResearch.addItems(core);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::OnItemUseRightClick;
        addItemHandler(itemUseHandler);
    }
    private void OnItemUseRightClick(PlayerRightClickEvent event) {
        event.cancel();
    }
}
