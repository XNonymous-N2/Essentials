package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand extends Command {
    public EnderchestCommand() {
        super("enderchest", "Opens your enderchest", false, "enderchest [user]", "ec");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            ((Player) commandSender).openInventory(((Player) commandSender).getEnderChest());
            return true;
        }
        if (args.length == 1) {
            if (!commandSender.hasPermission("essentials.command.enderchest.openother")) {
                sendMessage(commandSender, getText("no-perm"));
                return true;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;
            ((Player) commandSender).openInventory(player.getEnderChest());
            return true;
        }

        return false;
    }
}
