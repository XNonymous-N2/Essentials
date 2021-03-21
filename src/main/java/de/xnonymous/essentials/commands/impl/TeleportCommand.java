package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class TeleportCommand extends Command {
    public TeleportCommand() {
        super("teleport", "Teleports you to a player or coordinates", true, "teleport <user or x/y/z> [user]", "tp");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isPlayer(commandSender)) return true;
            if (!isOnline(commandSender, player)) return true;

            ((Player) commandSender).teleport(player);
            sendMessage(commandSender, getText("teleport").replaceAll("%to%", player.getName()));
            return true;
        }

        if (args.length == 2) {
            Player player1 = Bukkit.getPlayer(args[0]);
            Player player2 = Bukkit.getPlayer(args[1]);
            if (!isOnline(commandSender, player1)) return true;
            if (!isOnline(commandSender, player2)) return true;

            player1.teleport(player2);
            sendMessage(player1, getText("teleport").replaceAll("%to%", player2.getName()));
            return true;
        }

        if (args.length == 3) {
            teleport(commandSender, ((Player) commandSender), args[0], args[1], args[2]);
            return true;
        }

        if (args.length == 4) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;

            teleport(commandSender, player, args[1], args[2], args[3]);
            return true;
        }

        return false;
    }

    private void teleport(CommandSender commandSender, Player player, String x1, String y1, String z1) {
        if (!isPlayer(commandSender)) return;
        if (!isNumber(commandSender, x1)) return;
        if (!isNumber(commandSender, y1)) return;
        if (!isNumber(commandSender, z1)) return;

        int x = getNumber(x1);
        int y = getNumber(y1);
        int z = getNumber(z1);

        player.teleport(new Location(((Player) commandSender).getWorld(), x, y, z));
        sendMessage(player, getText("teleport").replaceAll("%to%", x + " " + y + " " + z));
    }

}
