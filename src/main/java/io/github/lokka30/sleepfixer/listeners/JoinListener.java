package io.github.lokka30.sleepfixer.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        player.setSleepingIgnored(true);
    }
}
