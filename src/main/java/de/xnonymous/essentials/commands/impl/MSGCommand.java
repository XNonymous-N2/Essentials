package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MSGCommand extends Command {
    public MSGCommand() {
        super("msg", "Whisper a person", true, "msg <user> <message>", "w", "whisper", "message");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        if (args.length > 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;

            args[0] = "";
            sendMessage(player, getText("msg").replaceAll("%player%", commandSender.getName()).replaceAll("%target%", player.getName()).replaceAll("%message%", String.join(" ", args)));
            sendMessage(commandSender, getText("msg").replaceAll("%player%", commandSender.getName()).replaceAll("%target%", player.getName()).replaceAll("%message%", String.join(" ", args)));

            return true;
        }

        return false;
    }
}
