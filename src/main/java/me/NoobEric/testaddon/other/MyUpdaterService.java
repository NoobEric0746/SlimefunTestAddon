package me.NoobEric.testaddon.other;

import io.github.bakedlibs.dough.config.Config;
import io.github.bakedlibs.dough.updater.GitHubBuildsUpdater;
import io.github.bakedlibs.dough.updater.PluginUpdater;
import io.github.bakedlibs.dough.versions.PrefixedVersion;
import io.github.thebusybiscuit.slimefun4.api.SlimefunBranch;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.NoobEric.testaddon.TestAddon;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.logging.Level;

/**
 * This Class represents our {@link PluginUpdater} Service.
 * If enabled, it will automatically connect to https://thebusybiscuit.github.io/builds/
 * to check for updates and to download them automatically.
 *
 * @author TheBusyBiscuit
 *
 */
public class MyUpdaterService {

    private final TestAddon plugin;

    private final PluginUpdater<PrefixedVersion> updater;

    private final SlimefunBranch branch;

    public MyUpdaterService(@Nonnull TestAddon plugin, @Nonnull String version, @Nonnull File file) {
        this.plugin = plugin;
        GitHubBuildsUpdater autoUpdater = null;

        try {
            autoUpdater = new GitHubBuildsUpdater(plugin, file, "TheBusyBiscuit/Slimefun4/stable", "RC - ");
        } catch (Exception x) {
            plugin.getLogger().log(Level.SEVERE, "Failed to create AutoUpdater", x);
        }

        branch = SlimefunBranch.STABLE;

        this.updater = autoUpdater;
    }

    /**
     * This method returns the branch the current build of Slimefun is running on.
     * This can be used to determine whether we are dealing with an official build
     * or a build that was unofficially modified.
     *
     * @return The branch this build of Slimefun is on.
     */
    public @Nonnull SlimefunBranch getBranch() {
        return branch;
    }

    /**
     * This method returns the build number that this is running on (or -1 if unofficial).
     * You should combine the usage with {@link #getBranch()} in order to properly see if this is
     * a development or stable build number.
     *
     * @return The build number of this Slimefun.
     */
    public int getBuildNumber() {
        if (updater != null) {
            PrefixedVersion version = updater.getCurrentVersion();
            return version.getVersionNumber();
        }

        return -1;
    }

    /**
     * This will start the {@link MyUpdaterService} and check for updates.
     * If it can find an update it will automatically be installed.
     */
    public void start() {
        if (updater != null) {
            updater.start();
        } else {
            printBorder();
            plugin.getLogger().log(Level.WARNING, "It looks like you are using an unofficially modified build of Slimefun!");
            plugin.getLogger().log(Level.WARNING, "Auto-Updates have been disabled, this build is not considered safe.");
            plugin.getLogger().log(Level.WARNING, "Do not report bugs encountered in this Version of Slimefun to any official sources.");
            printBorder();
        }
    }

    /**
     * This returns whether the {@link PluginUpdater} is enabled or not.
     * This includes the {@link Config} setting but also whether or not we are running an
     * official or unofficial build.
     *
     * @return Whether the {@link PluginUpdater} is enabled
     */
    public boolean isEnabled() {
        return Slimefun.getCfg().getBoolean("options.auto-update") && updater != null;
    }

    /**
     * This method is called when the {@link MyUpdaterService} was disabled.
     */
    public void disable() {
        printBorder();
        plugin.getLogger().log(Level.WARNING, "It looks like you have disabled auto-updates for Slimefun!");
        plugin.getLogger().log(Level.WARNING, "Auto-Updates keep your server safe, performant and bug-free.");
        plugin.getLogger().log(Level.WARNING, "We respect your decision.");

        if (branch != SlimefunBranch.STABLE) {
            plugin.getLogger().log(Level.WARNING, "If you are just scared of Slimefun breaking, then please consider using a \"stable\" build instead of disabling auto-updates.");
        }

        printBorder();
    }

    private void printBorder() {
        plugin.getLogger().log(Level.WARNING, "#######################################################");
    }

}
