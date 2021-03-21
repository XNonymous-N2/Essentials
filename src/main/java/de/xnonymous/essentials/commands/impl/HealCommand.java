package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand extends Command {
    public HealCommand() {
        super("heal", "Heals you full!", true, "heal [user]", "h", "health");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            if (!isPlayer(commandSender)) return true;
            ((Player) commandSender).setHealth(((Player) commandSender).getHealthScale());
            ((Player) commandSender).setFoodLevel(20);
            sendMessage(commandSender, getText("heal-complete"));
            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;
            sendMessage(player, getText("heal-complete"));
            sendMessage(commandSender, getText("healed-complete"));
            player.setHealth(player.getHealthScale());
            player.setFoodLevel(20);
            return true;
        }

        return false;
    }
}
