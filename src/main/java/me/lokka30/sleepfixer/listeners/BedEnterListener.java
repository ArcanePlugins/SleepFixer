/*
 * Copyright (c) 2020-2021 lokka30, All Rights Reserved.
 *
 * SleepFixer: The go-to simple and robust one-player-sleep plugin for SpigotMC servers.
 *   Description and download: <https://www.spigotmc.org/resources/sleepfixer.76746/>
 *   Source code and documentation: <https://github.com/lokka30/SleepFixer>
 *
 * Use of this source code is governed by the GNU AGPL v3.0 license that can be found in the repository's LICENSE.md file.
 */

package me.lokka30.sleepfixer.listeners;

import me.lokka30.microlib.other.VersionUtils;
import me.lokka30.sleepfixer.SleepFixer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

/**
 * @author lokka30
 * @since v1.0.0
 */
public class BedEnterListener implements Listener {

    private final SleepFixer main;

    public BedEnterListener(SleepFixer main) {
        this.main = main;
    }

    /**
     * @param event Event fired whenever players enter beds.
     * @author lokka30
     * @since v1.0.0
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onBedEnter(final PlayerBedEnterEvent event) {
        if (!event.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK)) return;

        final Player player = event.getPlayer();

        // Clear weather in the world.
        if (main.settings.getConfig().getBoolean("on-sleep.clear-weather", true)) {
            player.getWorld().setThundering(false);
            player.getWorld().setStorm(false);
        }

        // Clear insomnia for all players in the world.
        if (main.settings.getConfig().getBoolean("on-sleep.clear-insomnia", true)) {

            // Server must be MC 1.9 or newer
            if (!VersionUtils.isOneNine()) return;

            // Reset sleep statistic for all players in the same world.
            player.getWorld().getPlayers().forEach(onlinePlayer -> onlinePlayer.setStatistic(Statistic.TIME_SINCE_REST, 0));
        }
    }
}
