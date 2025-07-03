package me.NoobEric.testaddon;

import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import org.bukkit.NamespacedKey;

public class Researches {
    static NamespacedKey stickySwordKey = new NamespacedKey(TestAddon.instance,"sticky_sword_research");
    public static Research stickySwordResearch = new Research(stickySwordKey,145144,"真 抢夺",20);

    static NamespacedKey bouncySwordKey = new NamespacedKey(TestAddon.instance,"bouncy_sword_research");
    public static Research bouncySwordResearch = new Research(bouncySwordKey,145145,"击落你的盔甲",18);

    static NamespacedKey cakeKey = new NamespacedKey(TestAddon.instance,"cake_research");
    public static Research cakeResearch = new Research(cakeKey,145146,"来吃点绝对无害的蛋糕吧",15);

    static NamespacedKey hackCoreKey = new NamespacedKey(TestAddon.instance,"hack_core_research");
    public static Research hackCoreResearch = new Research(hackCoreKey,145146,"数据修改",25);

}
