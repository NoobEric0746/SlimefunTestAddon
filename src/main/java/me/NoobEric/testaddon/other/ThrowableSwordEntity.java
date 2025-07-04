package me.NoobEric.testaddon.other;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import me.NoobEric.testaddon.weapon.ThrowableSword;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

public final class ThrowableSwordEntity {

    private final boolean dropItem;
    private final boolean wasConsumed;
    private final Arrow arrow;
    private final Entity leashTarget;

    @ParametersAreNonnullByDefault
    public ThrowableSwordEntity(Player p, Arrow arrow, Entity leashTarget, boolean dropItem, boolean wasConsumed) {
        this.arrow = arrow;
        this.wasConsumed = wasConsumed;
        this.leashTarget = leashTarget;
        this.dropItem = p.getGameMode() != GameMode.CREATIVE && dropItem;
    }

    @Nonnull
    public Arrow getArrow() {
        return arrow;
    }

    public void drop(@Nonnull Location l) {
        // If a grappling hook was consumed, drop one grappling hook on the floor
        if (dropItem && wasConsumed) {
            Item item = l.getWorld().dropItem(l, ThrowableSword.itemStack.clone());
            item.setPickupDelay(16);
        }
    }

    public void remove() {
        if (arrow.isValid()) {
            arrow.remove();
        }

        if (leashTarget.isValid()) {
            leashTarget.remove();
        }
    }

}