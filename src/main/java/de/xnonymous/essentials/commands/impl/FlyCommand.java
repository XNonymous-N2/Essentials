package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;

public class FlyCommand extends Command {
    public FlyCommand() {
        super("fly", "Helps you to fly!", true, "fly [user]", "flight");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        if (args.length == 0) {
            if (!isPlayer(commandSender)) return true;

            Player player = (Player) commandSender;
            fly(player);
            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;

            fly(player);
            if (player.getAllowFlight())
                sendMessage(commandSender, getText("fly-toggled-on").replaceAll("%player%", player.getName()));
            else
                sendMessage(commandSender, getText("fly-toggled-off").replaceAll("%player%", player.getName()));
            return true;
        }

        return false;
    }

    private void fly(Player player) {
        player.setAllowFlight(!player.getAllowFlight());
        player.setFlying(player.getAllowFlight());
        if (player.getAllowFlight())
            sendMessage(player, getText("flight-on"));
        else
            sendMessage(player, getText("flight-off"));
    }
}
