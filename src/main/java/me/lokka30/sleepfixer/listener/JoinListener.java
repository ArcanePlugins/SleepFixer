/*
 * Copyright (c) 2020-2022 lokka30, All Rights Reserved.
 * This file is/was part of the SleepFixer resource, licensed under GNU AGPL v3.
 * For more information, see <https://github.com/lokka30/SleepFixer>.
 */

package me.lokka30.sleepfixer.listener;

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
