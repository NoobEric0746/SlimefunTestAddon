package me.NoobEric.testaddon;

import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import org.bukkit.NamespacedKey;

public class Researches {
    static NamespacedKey stickySwordKey = new NamespacedKey(TestAddon.instance,"sticky_sword_research");
    public static Research stickySwordResearch = new Research(stickySwordKey,145144,"真 抢夺",20);

    static NamespacedKey bouncySwordKey = new NamespacedKey(TestAddon.instance,"bouncy_sword_research");
    public static Research bouncySwordResearch = new Research(bouncySwordKey,145145,"击落你的盔甲",18);

    static NamespacedKey tetanusSwordKey = new NamespacedKey(TestAddon.instance,"tetanus_sword_research");
    public static Research tetanusSwordResearch = new Research(tetanusSwordKey,145149,"破伤风之力",30);

    static NamespacedKey cakeKey = new NamespacedKey(TestAddon.instance,"cake_research");
    public static Research cakeResearch = new Research(cakeKey,145146,"来吃点绝对无害的蛋糕吧",15);

    static NamespacedKey hackCoreKey = new NamespacedKey(TestAddon.instance,"hack_core_research");
    public static Research hackCoreResearch = new Research(hackCoreKey,145148,"数据修改",25);

    static NamespacedKey potionKey = new NamespacedKey(TestAddon.instance,"potion_research");
    public static Research potionResearch = new Research(potionKey,145150,"该吃药了",15);

    static NamespacedKey throwableSwordKey = new NamespacedKey(TestAddon.instance,"throwable_sword_research");
    public static Research throwableSwordResearch = new Research(throwableSwordKey,145151,"小李飞刀",30);

}
