/*
 * SleepFixer: Lightweight Bukkit plugin for enabling one-player-sleep and more
 * Copyright (C) Lachlan Adamson 2024.
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

package io.github.arcaneplugins.sleepfixer.util;

import org.bukkit.Statistic;

public class CompatUtil {

    private CompatUtil() {
        throw new IllegalStateException("Instantiation of utility class");
    }

    /**
     * Check if the server's MC version supports the insomnia statistic that SF requires to activate its insomnia-
     * clearing functionality.
     *
     * @return whether the server's version implements the 'TIME_SINCE_REST' statistic
     * @author lokka30
     * @since 2.0.4
     */
    public static boolean serverVerHasInsomniaStatistic() {
        try {
            Statistic.valueOf("TIME_SINCE_REST");
        } catch (IllegalArgumentException ignored) {
            return false;
        }

        return true;
    }
}
