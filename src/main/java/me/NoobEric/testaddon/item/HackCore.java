package me.NoobEric.testaddon.item;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
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

        ItemStack[] recipe = { SlimefunItems.MAGIC_LUMP_3, SlimefunItems.BATTERY , SlimefunItems.MAGIC_LUMP_3,
        SlimefunItems.POWER_CRYSTAL, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.POWER_CRYSTAL,
                        SlimefunItems.MAGIC_LUMP_3, SlimefunItems.BATTERY, SlimefunItems.MAGIC_LUMP_3 };

        HackCore core = new HackCore(ItemGroups.Group1,itemStack,RecipeType.ANCIENT_ALTAR,recipe);
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
