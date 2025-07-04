package me.NoobEric.testaddon.weapon;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.NoobEric.testaddon.ItemGroups;
import me.NoobEric.testaddon.Researches;
import me.NoobEric.testaddon.TestAddon;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class TetanusSword extends SlimefunItem {
    public TetanusSword(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public static void reg(){
        SlimefunItemStack itemStack = new SlimefunItemStack("TETANUS_SWORD", Material.NETHERITE_SWORD,"破伤风之剑","","破伤风","§7test");
        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(Enchantment.LUCK, 1, true);

//        AttributeModifier speedModifier = new AttributeModifier(
//                UUID.randomUUID(),
//                "ATTACK_SPEED_MODIFIER",
//                2.5,
//                AttributeModifier.Operation.ADD_NUMBER,
//                EquipmentSlot.HAND
//        );
//        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,speedModifier);

//        AttributeModifier damageModifier = new AttributeModifier(
//                UUID.randomUUID(),
//                "DAMAGE_MODIFIER",
//                4.0,
//                AttributeModifier.Operation.ADD_NUMBER,
//                EquipmentSlot.HAND
//        );
//        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,damageModifier);

//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        itemStack.setItemMeta(meta);
        ItemStack[] recipe = { new ItemStack(Material.OXIDIZED_COPPER), SlimefunItems.ENCHANTMENT_RUNE, new ItemStack(Material.OXIDIZED_COPPER),
                SlimefunItems.NECROTIC_SKULL, new ItemStack(Material.NETHERITE_SWORD), SlimefunItems.NECROTIC_SKULL,
                new ItemStack(Material.EXPOSED_COPPER), SlimefunItems.ENCHANTMENT_RUNE, new ItemStack(Material.EXPOSED_COPPER) };

        TetanusSword sword= new TetanusSword(ItemGroups.Group1,itemStack,RecipeType.ANCIENT_ALTAR,recipe);
        sword.register(TestAddon.instance);
        Researches.tetanusSwordResearch.addItems(sword);
    }

    @Override
    public void preRegister() {
//        ItemUseHandler itemUseHandler = this::OnItemUseRightClick;
//        addItemHandler(itemUseHandler);
    }

//    @Override
//    public void postRegister() {
//        ItemStack item = this.getItem().clone();
//        ItemMeta meta = item.getItemMeta();
//        meta.addEnchant(Enchantment.DAMAGE_ALL,1,true);
//        item.setItemMeta(meta);
//    }


}
