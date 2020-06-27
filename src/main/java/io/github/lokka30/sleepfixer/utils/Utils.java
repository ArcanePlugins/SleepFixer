package io.github.lokka30.sleepfixer.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Utils {

    public List<String> getSupportedServerVersions() { return Arrays.asList("1.16", "1.15", "1.14", "1.13", "1.12", "1.11", "1.10", "1.9", "1.8", "1.7"); }

    public String colorize(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public void log(final LogLevel level, String msg) {
        final Logger logger = Bukkit.getLogger();
        msg = colorize("&b&lSleepFixer: &7" + msg);

        switch (level) {
            case INFO:
                logger.info(msg);
                return;
            case WARNING:
                logger.warning(msg);
                return;
            case SEVERE:
                logger.severe(msg);
                return;
            default:
                throw new IllegalStateException("Unmanaged LogLevel " + level.toString() + " - msg: " + msg);
        }
    }
}
