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
        if(args.length == 0) {
            sender.sendMessage(" ");
            sender.sendMessage(instance.utils.colorize("&b&lSleepFixer &bv" + instance.getDescription().getVersion() + "&7 by &3&olokka30&7."));
            sender.sendMessage(" ");
            sender.sendMessage(instance.utils.colorize("&f&nSpigotMC Resource Page:"));
            sender.sendMessage(instance.utils.colorize("&8https://www.spigotmc.org/resources/%E2%99%A6-sleepfixer-%E2%99%A6-1-7-10-1-15-2.76746/"));
            sender.sendMessage(" ");
        } else {
            sender.sendMessage(instance.utils.colorize("&b&lSleepFixer: &7Usage: &b/" + label));
        }
        return true;
    }
}
