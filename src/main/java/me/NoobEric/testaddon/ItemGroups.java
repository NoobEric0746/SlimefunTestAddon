package me.NoobEric.testaddon;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class ItemGroups {
    static ItemStack group1Item = new CustomItemStack(Material.DIAMOND, "&4附属分类");

    // 给你的分类提供一个独一无二的ID
    static NamespacedKey Group1Id = new NamespacedKey(TestAddon.instance, "NoobUtils");
    public static ItemGroup Group1 = new ItemGroup(Group1Id, group1Item);

}
