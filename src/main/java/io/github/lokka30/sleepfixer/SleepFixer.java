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
        utils.log(LogLevel.INFO, "&8+----+&f (Enabling Began) &8+----+");
        long startTime = System.currentTimeMillis();

        utils.log(LogLevel.INFO, "&8(&3Startup &8- &31&8/&33&8) &7Checking compatibility...");
        if (checkCompatibility()) {

            utils.log(LogLevel.INFO, "&8(&3Startup &8- &32&8/&33&8) &7Registering events...");
            registerEvents();

            utils.log(LogLevel.INFO, "&8(&3Startup &8- &33&8/&33&8) &7Registering commands...");
            registerCommands();

            long duration = System.currentTimeMillis() - startTime;

            utils.log(LogLevel.INFO, "&8+----+&f (Enabling Complete, took &b" + duration + "ms&f) &8+----+");
        } else {
            utils.log(LogLevel.INFO, "&8+----+&f (Enabling Cancelled) &8+----+");
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
        boolean isRunningSupportedVersion = false;
        for(String supportedVersion : utils.getSupportedServerVersions()) {
            if(currentServerVersion.contains(supportedVersion)) {
                isRunningSupportedVersion = true;
                break;
            }
        }
        if(!isRunningSupportedVersion) {
            utils.log(LogLevel.WARNING, "&8(&3Compatibility Check&8)&7 Your server version '&b" + currentServerVersion + "&7' is not supported by this version of SleepFixer. You will not receive the author's support whilst running this unsupported server configuration.");
        }

        //No hard dependencies, so checkCompatibility will always return true.
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
