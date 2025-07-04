package me.NoobEric.testaddon;

import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.services.UpdaterService;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.GrapplingHookListener;
import me.NoobEric.testaddon.block.FireCake;
import me.NoobEric.testaddon.block.SweetCake;
import me.NoobEric.testaddon.event.MyListener;
import me.NoobEric.testaddon.event.ThrowableSwordListener;
import me.NoobEric.testaddon.item.GoUpScroll;
import me.NoobEric.testaddon.item.HackCore;
import me.NoobEric.testaddon.item.IronPotion;
import me.NoobEric.testaddon.item.MinusSoup;
import me.NoobEric.testaddon.other.MyUpdaterService;
import me.NoobEric.testaddon.weapon.BouncySword;
import me.NoobEric.testaddon.weapon.StickySword;
import me.NoobEric.testaddon.weapon.TetanusSword;
import me.NoobEric.testaddon.weapon.ThrowableSword;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

import javax.annotation.Nonnull;


public class TestAddon extends JavaPlugin implements SlimefunAddon {
    public static TestAddon instance;
    private final ThrowableSwordListener throwableSwordListener = new ThrowableSwordListener();
//    private final MyUpdaterService updaterService = new MyUpdaterService(this, getDescription().getVersion(), getFile());


    public static @Nonnull ThrowableSwordListener getThrowableSwordListener() {
        return instance.throwableSwordListener;
    }

    @Override
    public void onEnable() {
        instance = this;
        // 从 config.yml 中读取插件配置
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // 你可以在这里添加自动更新功能
//            updaterService.start();
        }

        FireCake.reg();
        SweetCake.reg();
        GoUpScroll.reg();
        HackCore.reg();
        StickySword.reg();
        BouncySword.reg();
        TetanusSword.reg();
//        ThrowableSword.reg();
        MinusSoup.reg();
        IronPotion.reg();

        getServer().getPluginManager().registerEvents(new MyListener(),this);
//        getServer().getPluginManager().registerEvents(new ThrowableSwordListener(),this);

        Researches.stickySwordResearch.register();
        Researches.bouncySwordResearch.register();
        Researches.tetanusSwordResearch.register();
//        Researches.throwableSwordResearch.register();
        Researches.cakeResearch.register();
        Researches.hackCoreResearch.register();
        Researches.potionResearch.register();
    }

    @Override
    public void onDisable() {
        // 禁用插件的逻辑...
    }

    @Override
    public String getBugTrackerURL() {
        // 你可以在这里返回你的问题追踪器的网址，而不是 null
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * 你需要返回对你插件的引用。
         * 如果这是你插件的主类，只需要返回 "this" 即可。
         */
        return this;
    }

}
