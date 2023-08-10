/*
 * Copyright (c) 2020-2022 lokka30, All Rights Reserved.
 * This file is/was part of the SleepFixer resource, licensed under GNU AGPL v3.
 * For more information, see <https://github.com/lokka30/SleepFixer>.
 */

package me.lokka30.sleepfixer.listener;

import me.lokka30.sleepfixer.SleepFixer;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author lokka30
 * @since v1.0.0
 */
public class BedEnterListener implements Listener {

    private final SleepFixer main;
    public BedEnterListener(
        final @NotNull SleepFixer main
    ) {
        this.main = main;
    }

    /**
     * @param event Event fired whenever players enter beds.
     * @author lokka30
     * @since v1.0.0
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onBedEnter(
        final PlayerBedEnterEvent event
    ) {
        if (!event.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK))
            return;

        final Player player = event.getPlayer();

        // Only apply these changes in the overworld (Environment.NORMAL)
        if(player.getWorld().getEnvironment() != World.Environment.NORMAL)
            return;

        // Clear weather in the world.
        if (main.settings.getConfig().getBoolean("on-sleep.clear-weather", true)) {
            player.getWorld().setThundering(false);
            player.getWorld().setStorm(false);
        }

        // Clear insomnia for all players in the world.
        if (main.settings.getConfig().getBoolean("on-sleep.clear-insomnia", true)) {

            // Only 1.9+ servers have the insomnia statistic for the Phantom entities
            try {
                Statistic.valueOf("TIME_SINCE_REST");

                // ok, it exists, let's continue

                // Reset sleep statistic for all players in the same world.
                player.getWorld().getPlayers().forEach(onlinePlayer ->
                    onlinePlayer.setStatistic(Statistic.TIME_SINCE_REST, 0)
                );

            } catch(IllegalArgumentException ignored) {}
        }
    }
}
