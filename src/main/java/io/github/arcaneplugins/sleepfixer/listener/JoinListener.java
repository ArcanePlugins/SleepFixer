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

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author lokka30
 * @since v1.0.0
 */
public class JoinListener implements Listener {

    /**
     * @param event fired when a player joins the server
     * @author lokka30
     * @since v1.0.0
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onJoin(
            final PlayerJoinEvent event
    ) {
        event.getPlayer().setSleepingIgnored(true);
    }
}
