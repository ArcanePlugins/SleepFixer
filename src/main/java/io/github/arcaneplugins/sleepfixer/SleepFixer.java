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

package io.github.arcaneplugins.sleepfixer;

import io.github.arcaneplugins.sleepfixer.listener.BedEnterListener;
import io.github.arcaneplugins.sleepfixer.listener.JoinListener;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * @author lokka30
 * @since v1.0.0
 */
public class SleepFixer extends JavaPlugin {

    private YamlConfiguration settings;

    @Override
    public void onEnable() {
        final long startTime = System.currentTimeMillis();

        initSettingsFile();
        initListeners();
        initMetrics();

        final float durationMillis = System.currentTimeMillis() - startTime;
        final float durationSecs = Math.round(durationMillis * 100f) / 100f;
        getLogger().info("Start-up complete (took " + durationSecs + "ms).");
    }

    @Override
    public void onDisable() {
        final long startTime = System.currentTimeMillis();

        purgeSleepingIgnored();

        final float durationMillis = System.currentTimeMillis() - startTime;
        final float durationSecs = Math.round(durationMillis * 100f) / 100f;
        getLogger().info("Shut-down complete (took " + durationSecs + "ms).");
    }

    private void initSettingsFile() {
        final String fileName = "settings.yml";
        final File file = new File(getDataFolder(), fileName);

        if (!file.exists()) {
            saveResource(fileName, false);
        }

        setSettings(YamlConfiguration.loadConfiguration(file));
    }

    private void initListeners() {
        final Collection<Listener> listeners = Arrays.asList(
                new BedEnterListener(this),
                new JoinListener()
        );

        listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void initMetrics() {
        final Metrics metrics = new Metrics(this, 6935);

        // This chart displays how many servers use the 'clear weather on sleep' setting
        metrics.addCustomChart(new SimplePie("clears_weather_on_sleep", () ->
                Boolean.toString(settings().getBoolean("on-sleep.clear-weather", true))));

        // This chart displays how many servers use the 'clear insomnia on sleep' setting
        metrics.addCustomChart(new SimplePie("clears_insomnia_on_sleep", () ->
                Boolean.toString(settings().getBoolean("on-sleep.clear-insomnia", true))));

        // This chart displays how many servers use the debug setting, and how many
        metrics.addCustomChart(new SimplePie("debug_categories_enabled", () ->
                Integer.toString(settings().getStringList("debug").size())));
    }

    private void setSettings(
            final YamlConfiguration settings
    ) {
        this.settings = Objects.requireNonNull(settings, "settings");
    }

    private void purgeSleepingIgnored() {
        if (Bukkit.getOnlinePlayers().isEmpty()) {
            return;
        }

        getLogger().info("Removing 'sleeping ignored' status for online players...");

        for (final Player player : Bukkit.getOnlinePlayers()) {
            player.setSleepingIgnored(false);
        }
    }

    public YamlConfiguration settings() {
        return this.settings;
    }
}
