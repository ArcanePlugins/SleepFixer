/*
 * Copyright (c) 2020-2021 lokka30, All Rights Reserved.
 *
 * SleepFixer: The go-to simple and robust one-player-sleep plugin for SpigotMC servers.
 *   Description and download: <https://www.spigotmc.org/resources/sleepfixer.76746/>
 *   Source code and documentation: <https://github.com/lokka30/SleepFixer>
 *
 * Use of this source code is governed by the GNU AGPL v3.0 license that can be found in the repository's LICENSE.md file.
 */

package me.lokka30.sleepfixer;

import me.lokka30.microlib.files.YamlConfigFile;
import me.lokka30.microlib.maths.QuickTimer;
import me.lokka30.sleepfixer.listeners.BedEnterListener;
import me.lokka30.sleepfixer.listeners.JoinListener;
import me.lokka30.sleepfixer.misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author lokka30
 * @since v1.0.0
 */
public class SleepFixer extends JavaPlugin {

    public final YamlConfigFile settings = new YamlConfigFile(this, new File(getDataFolder(), "settings.yml"));

    @Override
    public void onEnable() {
        Utils.LOGGER.info("&fInitiating start-up sequence...");
        final QuickTimer timer = new QuickTimer();

        Utils.LOGGER.info("Loading files...");
        loadFiles();

        Utils.LOGGER.info("Registering listeners...");
        registerListeners();

        Utils.LOGGER.info("&fStart-up complete, took &b" + timer.getTimer() + "ms&7.");
    }

    @Override
    public void onDisable() {
        Utils.LOGGER.info("&fInitiating shut-down sequence...");
        final QuickTimer timer = new QuickTimer();

        // If any shut-down things need to be added, put them here.

        Utils.LOGGER.info("&fShut-down complete, took &b" + timer.getTimer() + "ms&7.");
    }

    void loadFiles() {
        // Save & load settings.yml
        try {
            settings.load();
        } catch (IOException ex) {
            Utils.LOGGER.error("Unable to load &bsettings.yml&7! &8" + ex.getMessage());
        }

        // Replace & save license.txt
        saveResource("license.txt", true);
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new BedEnterListener(this), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }
}
