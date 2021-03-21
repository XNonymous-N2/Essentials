package de.xnonymous.essentials.commands.impl;

import com.comphenix.protocol.PacketType;
import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DamageCommand extends Command {
    public DamageCommand() {
        super("damage", "Makes yourself or other people damage", true, "damage <damage> [user]", "hit");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            if (!isPlayer(commandSender)) return true;
            if (!isNumber(commandSender, args[0])) return true;

            Integer number = getNumber(args[0]);
            Player player = (Player) commandSender;

            player.damage(number);
            sendMessage(commandSender, "Ouch");
            return true;
        }
        if (args.length == 2) {
            Player player = Bukkit.getPlayer(args[1]);
            if (!isOnline(commandSender, player)) return true;
            if (!isNumber(commandSender, args[0])) return true;

            player.damage(getNumber(args[0]));
            sendMessage(commandSender, "Ouch");
            sendMessage(player, "Ouch");

            return true;
        }

        return false;
    }
}
