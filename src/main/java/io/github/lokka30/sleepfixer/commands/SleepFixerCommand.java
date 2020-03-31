package io.github.lokka30.sleepfixer.commands;

import io.github.lokka30.sleepfixer.SleepFixer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SleepFixerCommand implements CommandExecutor {

    private SleepFixer instance;

    public SleepFixerCommand(final SleepFixer instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        sender.sendMessage(instance.utils.colorize("&a&lSleepFixer: &7This server is running &aSleepFixer v" + instance.getDescription().getVersion() + "&7, developed for &aMinecraft " + instance.utils.getRecommendedServerVersion() + "&7."));
        sender.sendMessage(instance.utils.colorize("&a&lSleepFixer: &7Credits:"));
        for (String contributor : instance.getDescription().getAuthors()) {
            sender.sendMessage(instance.utils.colorize("&8 - &7" + contributor));
        }
        return true;
    }
}
