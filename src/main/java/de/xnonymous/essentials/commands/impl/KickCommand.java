package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand extends Command {
    public KickCommand() {
        super("kick", "Kicks a player from this server", true, "kick <user> [reason]", "ciao");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        if (args.length != 0) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;

            if (args.length == 1)
                player.kickPlayer(getText("default-kick"));
            else
                player.kickPlayer(String.join(" ", args).replaceAll("&", "§").replaceAll(args[0], ""));

            sendMessage(commandSender, getText("has-kicked").replaceAll("%player%", player.getName()));
            return true;
        }

        return false;
    }
}
