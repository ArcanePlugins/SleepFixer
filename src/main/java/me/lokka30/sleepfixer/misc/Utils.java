/*
 * Copyright (c) 2020-2021 lokka30, All Rights Reserved.
 *
 * SleepFixer: The go-to simple and robust one-player-sleep plugin for SpigotMC servers.
 *   Description and download: <https://www.spigotmc.org/resources/sleepfixer.76746/>
 *   Source code and documentation: <https://github.com/lokka30/SleepFixer>
 *
 * Use of this source code is governed by the GNU AGPL v3.0 license that can be found in the repository's LICENSE.md file.
 */

package me.lokka30.sleepfixer.misc;

import me.lokka30.microlib.messaging.MicroLogger;

/**
 * @author lokka30
 * @since unknown -- re-coded in v2.0.0
 */
public class Utils {

    /**
     * @author lokka30
     * @since v2.0.0
     */
    private Utils() {
        throw new UnsupportedOperationException();
    }

    /**
     * author: lokka30
     *
     * @since v2.0.0
     */
    public static final MicroLogger LOGGER = new MicroLogger("&b&lSleepFixer: &7");

}
