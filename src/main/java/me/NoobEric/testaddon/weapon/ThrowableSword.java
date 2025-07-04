package me.NoobEric.testaddon.weapon;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import me.NoobEric.testaddon.ItemGroups;
import me.NoobEric.testaddon.Researches;
import me.NoobEric.testaddon.TestAddon;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.UUID;

public class ThrowableSword extends SlimefunItem {
    public ThrowableSword(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }
    public static final SlimefunItemStack itemStack = new SlimefunItemStack("THROWABLE_SWORD", Material.IRON_SWORD,"飞刀","","test","§7test");

    public static void reg(){
        ItemMeta meta = itemStack.getItemMeta();

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
        ItemStack[] recipe = { new ItemStack(Material.SLIME_BLOCK), SlimefunItems.ENCHANTMENT_RUNE, new ItemStack(Material.SLIME_BLOCK),
                SlimefunItems.HOOK, new ItemStack(Material.DIAMOND_SWORD), SlimefunItems.HOOK,
                new ItemStack(Material.SLIME_BLOCK), SlimefunItems.ENCHANTMENT_RUNE, new ItemStack(Material.SLIME_BLOCK) };

        ThrowableSword sword= new ThrowableSword(ItemGroups.Group1,itemStack,RecipeType.ANCIENT_ALTAR,recipe);
        sword.register(TestAddon.instance);
        Researches.throwableSwordResearch.addItems(sword);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::OnItemUseRightClick;
        addItemHandler(itemUseHandler);
    }

    private void OnItemUseRightClick(PlayerRightClickEvent event) {
        Player plaer = event.getPlayer();
        UUID uuid = plaer.getUniqueId();

        if (!event.getClickedBlock().isPresent() && !TestAddon.getThrowableSwordListener().isGrappling(uuid)) {
            event.cancel();

            if (plaer.getInventory().getItemInOffHand().getType() == Material.BOW) {
                // Cancel, to fix dupe #740
                return;
            }

            ItemStack item = event.getItem();

            if (item.getType() == Material.IRON_SWORD) {
                // If consume on use is enabled, consume one item
                ItemUtils.consumeItem(item, false);
            }

            Vector direction = plaer.getEyeLocation().getDirection().multiply(2.0);
            Arrow arrow = plaer.getWorld().spawn(plaer.getEyeLocation().add(direction.getX(), direction.getY(), direction.getZ()), Arrow.class);
            arrow.setShooter(plaer);
            arrow.setVelocity(direction);

            Bat bat = (Bat) plaer.getWorld().spawnEntity(plaer.getLocation(), EntityType.BAT);
            bat.setCanPickupItems(false);
            bat.setAI(false);
            bat.setSilent(true);
            bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 100000));
            bat.setLeashHolder(arrow);

            boolean state = item.getType() != Material.SHEARS;
            TestAddon.getThrowableSwordListener().addThrowableSword(plaer, arrow, bat, state, new IntRangeSetting(this, "despawn-seconds", 0, 60, Integer.MAX_VALUE).getValue(), true);
        }
    }

}
