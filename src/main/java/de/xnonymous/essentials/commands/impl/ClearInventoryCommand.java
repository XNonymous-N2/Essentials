package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventoryCommand extends Command {
    public ClearInventoryCommand() {
        super("clearinventory", "Clears your inventory", true, "clearinventory [user]", "ci", "clearinv");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            if (!isPlayer(commandSender)) return true;
            Player player = ((Player) commandSender);
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            sendMessage(player, getText("clear-inv-complete"));
            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            sendMessage(commandSender, getText("clear-inv-complete-other").replaceAll("%target%", player.getName()));
            sendMessage(commandSender, getText("clear-inv-complete"));
            return true;
        }
        return false;
    }
}
