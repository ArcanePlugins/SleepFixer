package io.github.lokka30.sleepfixer;

import io.github.lokka30.sleepfixer.commands.SleepFixerCommand;
import io.github.lokka30.sleepfixer.listeners.BedEnterListener;
import io.github.lokka30.sleepfixer.listeners.JoinListener;
import io.github.lokka30.sleepfixer.utils.LogLevel;
import io.github.lokka30.sleepfixer.utils.Utils;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SleepFixer extends JavaPlugin {

    public Utils utils;

    public PluginManager pluginManager;

    @Override
    public void onLoad() {
        utils = new Utils();

        pluginManager = getServer().getPluginManager();
    }

    @Override
    public void onEnable() {
        utils.log(LogLevel.INFO, "--- [Enabling Began] ---");

        utils.log(LogLevel.INFO, "Checking compatibility...");
        if (checkCompatibility()) {

            utils.log(LogLevel.INFO, "Registering events...");
            registerEvents();

            utils.log(LogLevel.INFO, "Registering commands...");
            registerCommands();

            utils.log(LogLevel.INFO, "--- [Enabling Complete] ---");
        } else {
            utils.log(LogLevel.INFO, "--- [Enabling Cancelled] ---");

            pluginManager.disablePlugin(this);
        }
    }

    /*

    If methods need to be ran on disable, uncomment this section.
    Otherwise, it will serve no purpose sending disable status messages.

    @Override
    public void onDisable() {
        utils.log(LogLevel.INFO, "--- [Disabling Began] ---");

        utils.log(LogLevel.INFO, "--- [Disabling Complete] ---");
    }
    */

    private boolean checkCompatibility() {

        //Check server version (possible incompatibility)
        final String currentServerVersion = getServer().getVersion();
        final String getRecommendedServerVersion = utils.getRecommendedServerVersion();
        if (currentServerVersion.contains(getRecommendedServerVersion)) {
            utils.log(LogLevel.INFO, "&7Compatibility Check: You are running a recommended server version.");
        } else {
            utils.log(LogLevel.WARNING, "&7Compatibility Check: You are not running a recommended server version! You will not receive support from the author doing so.");
        }

        //No dependencies, so checkCompatibility will always return true.
        return true;
    }

    public void registerEvents() {
        pluginManager.registerEvents(new BedEnterListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
    }

    public void registerCommands() {
        Objects.requireNonNull(getCommand("sleepfixer")).setExecutor(new SleepFixerCommand(this));
    }
}
