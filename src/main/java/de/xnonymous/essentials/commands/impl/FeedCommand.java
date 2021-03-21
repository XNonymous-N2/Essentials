package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand extends Command {
    public FeedCommand() {
        super("feed", "Gives you food", true, "feed [user]");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        if (args.length == 0) {
            if (!isPlayer(commandSender)) return true;

            ((Player) commandSender).setFoodLevel(20);
            sendMessage(commandSender, getText("food"));

            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;

            player.setFoodLevel(20);
            sendMessage(player, getText("food"));
            sendMessage(commandSender, getText("food-other").replaceAll("%player%", player.getName()));
            return true;
        }

        return false;
    }
}
