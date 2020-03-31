package io.github.lokka30.sleepfixer.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedEnterListener implements Listener {

    @EventHandler
    public void onBedEnter(final PlayerBedEnterEvent e) {
        if (!e.isCancelled() && e.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
            e.getPlayer().getWorld().setThundering(false);
            e.getPlayer().getWorld().setStorm(false);
        }
    }
}
