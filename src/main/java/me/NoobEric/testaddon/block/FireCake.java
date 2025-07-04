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
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Cake;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FireCake extends SlimefunItem {

    public FireCake(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public static void reg(){
        /*
         * 2. 创建一个 SlimefunItemStack
         * 这个类是 ItemStack 的扩展，拥有多个构造函数
         * 重要：每个物品都得有一个独一无二的ID
         */
        SlimefunItemStack slimefunItem = new SlimefunItemStack("COOL_DIAMOND", Material.DIAMOND, "&4炫酷的钻石", "&c+20% 炫酷");

        /*
         * 3. 创建配方
         * 这个配方是一个拥有9个ItemStack的数组。
         * 它代表了一个3x3的有序合成配方。
         * 该配方所需的机器将在后面通过RecipeType指定。
         */
        ItemStack[] recipe = {null , new ItemStack(Material.BLAZE_POWDER), null,
                new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.CAKE), new ItemStack(Material.BLAZE_POWDER),
                        null, new ItemStack(Material.BLAZE_POWDER), null };

        /*
         * 4. 注册物品
         * 现在，你只需要注册物品
         * RecipeType.ENHANCED_CRAFTING_TABLE 代表
         * 该物品将在增强型工作台中合成。
         * 来自粘液科技本体的配方类型将会自动将配方添加到对应的机器中。
         */
//        SlimefunItem item = new SlimefunItem(itemGroup, slimefunItem, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
//        item.register(this);
        SlimefunItemStack itemStack = new SlimefunItemStack("FIRE_CAKE", Material.CAKE,"烈焰蛋糕","",ChatColor.GOLD + "烫!");
        FireCake cake = new FireCake(ItemGroups.Group1,itemStack,RecipeType.MAGIC_WORKBENCH,recipe);
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
        player.setFireTicks(5*20);
        player.sendMessage(ChatColor.GOLD+"锟斤拷烫烫烫");
        Location loc = event.getClickedBlock().get().getLocation();
        player.getWorld().spawnParticle(Particle.FLAME,loc,10,0.5,0.5,0.5);
//        Block block = loc.getBlock();
//        Cake cakeData = (Cake) block.getBlockData();
//        if(cakeData.getBites()==6){
//
//        }
        BlockStorage.clearBlockInfo(loc);
    }
}
