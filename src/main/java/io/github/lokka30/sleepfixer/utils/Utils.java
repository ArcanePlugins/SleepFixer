package io.github.lokka30.sleepfixer.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.logging.Logger;

public class Utils {

    public String getRecommendedServerVersion() {
        return "1.15";
    }

    public String colorize(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public void log(final LogLevel level, String msg) {
        final Logger logger = Bukkit.getLogger();
        msg = colorize("&8[&7SleepFixer&8] &7" + msg);

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
