package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand  extends Command {

    public KillCommand() {
        super("kill", "Kills you or a player", true, "kill [user]", "dead");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            if (!isPlayer(commandSender)) return true;
            ((Player) commandSender).setHealth(0);
            sendMessage(commandSender, "Ouch");
            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;
            player.setHealth(0);
            sendMessage(commandSender, "Ouch");
            return true;
        }
        return false;
    }
}
