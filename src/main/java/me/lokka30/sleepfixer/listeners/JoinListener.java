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
    public void onJoin(final PlayerJoinEvent event) {
        event.getPlayer().setSleepingIgnored(true);
    }
}
