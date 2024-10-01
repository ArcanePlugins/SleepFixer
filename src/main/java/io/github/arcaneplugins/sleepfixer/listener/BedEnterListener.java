/*
 * SleepFixer: Lightweight Bukkit plugin for enabling one-player-sleep and more
 * Copyright (C) Lachlan Adamson 2020 - 2024.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.arcaneplugins.sleepfixer.listener;

import io.github.arcaneplugins.sleepfixer.util.CompatUtil;
import io.github.arcaneplugins.sleepfixer.SleepFixer;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author lokka30
 * @since v1.0.0
 */
public class BedEnterListener implements Listener {

    private final SleepFixer plugin;

    public BedEnterListener(
            final @NotNull SleepFixer plugin
    ) {
        this.plugin = Objects.requireNonNull(plugin, "plugin");
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
        final Player player = event.getPlayer();

        // We only care if the player has successfully entered their bed.
        if (!event.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
            return;
        }

        // Only apply these changes in the overworld (Environment.NORMAL) or CUSTOM environment worlds.
        if (player.getWorld().getEnvironment() != World.Environment.NORMAL &&
                player.getWorld().getEnvironment() != World.Environment.CUSTOM
        ) {
            return;
        }

        // Clear weather in the world (if enabled).
        if (plugin().settings().getBoolean("on-sleep.clear-weather", true)) {
            player.getWorld().setThundering(false);
            player.getWorld().setStorm(false);
        }

        // Clear insomnia for all players in the world (if enabled).
        // Only 1.9+ servers have the insomnia statistic for the Phantom entities
        if (plugin().settings().getBoolean("on-sleep.clear-insomnia", true) &&
                CompatUtil.serverVerHasInsomniaStatistic()
        ) {
            // Reset sleep statistic for all players in the same world. This has the effect of clearing
            // all players' insomnia.
            player.getWorld().getPlayers().forEach(onlinePlayer ->
                    onlinePlayer.setStatistic(Statistic.TIME_SINCE_REST, 0)
            );
        }
    }

    private SleepFixer plugin() {
        return Objects.requireNonNull(plugin, "plugin");
    }
}
