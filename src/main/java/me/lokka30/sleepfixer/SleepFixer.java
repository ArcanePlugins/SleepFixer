/*
 * Copyright (c) 2020-2023 lokka30, All Rights Reserved.
 * This file is/was part of the SleepFixer resource, licensed under GNU AGPL v3.
 * For more information, see <https://github.com/lokka30/SleepFixer>.
 */

package me.lokka30.sleepfixer;

import me.lokka30.microlib.files.YamlConfigFile;
import me.lokka30.microlib.maths.QuickTimer;
import me.lokka30.sleepfixer.listener.BedEnterListener;
import me.lokka30.sleepfixer.listener.JoinListener;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author lokka30
 * @since v1.0.0
 */
public class SleepFixer extends JavaPlugin {

    public final YamlConfigFile settings = new YamlConfigFile(
        this,
        new File(getDataFolder(), "settings.yml")
    );

    @Override
    public void onEnable() {
        final QuickTimer timer = new QuickTimer(TimeUnit.MILLISECONDS);

        loadFiles();
        registerListeners();

        getLogger().info("Running misc procedures...");
        loadMetrics();

        getLogger().info("Start-up complete (took " + timer.getDuration() + "ms).");
    }

    @Override
    public void onDisable() {
        final QuickTimer timer = new QuickTimer(TimeUnit.MILLISECONDS);

        // If any shut-down things need to be added, put them here.

        getLogger().info("Shut-down complete (took " + timer.getDuration() + "ms).");
    }

    private void loadFiles() {
        getLogger().info("Loading files...");

        // Save & load settings.yml
        try {
            settings.load();
        } catch (IOException ex) {
            getLogger().severe("Unable to load settings.yml: " + ex.getMessage() + "; Stack trace:");
            ex.printStackTrace();
        }
    }

    private void registerListeners() {
        getLogger().info("Registering listeners...");

        Bukkit.getPluginManager().registerEvents(new BedEnterListener(this), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }

    private void loadMetrics() {
        final Metrics metrics = new Metrics(this, 6935);

        // This chart displays how many servers use the 'clear weather on sleep' setting
        metrics.addCustomChart(new SimplePie("clears_weather_on_sleep", () ->
                Boolean.toString(settings.getConfig().getBoolean("on-sleep.clear-weather", true))));

        // This chart displays how many servers use the 'clear insomnia on sleep' setting
        metrics.addCustomChart(new SimplePie("clears_insomnia_on_sleep", () ->
                Boolean.toString(settings.getConfig().getBoolean("on-sleep.clear-insomnia", true))));

        // This chart displays how many servers use the debug setting, and how many
        metrics.addCustomChart(new SimplePie("debug_categories_enabled", () ->
                Integer.toString(settings.getConfig().getStringList("debug").size())));
    }
}
