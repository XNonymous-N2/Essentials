package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.utils.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SeenCommand extends Command {
    public SeenCommand() {
        super("seen", "Shows you information from a player", true, "seen <user>", "whois", "lastonline", "who");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length != 1)
            return false;
        UUID uuid = UUIDFetcher.getUUID(args[0]);

        if (uuid == null) {
            sendMessage(commandSender, getText("never-played"));
            return true;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);

        sendMessage(commandSender, "Name: §e" + offlinePlayer.getName());
        sendMessage(commandSender, "UUID: §e" + offlinePlayer.getUniqueId().toString());
        if (!offlinePlayer.isOnline())
            sendMessage(commandSender, "Last join: §e" + convertTime(offlinePlayer.getLastPlayed()));
        sendMessage(commandSender, "Online: §e" + offlinePlayer.isOnline());
        sendMessage(commandSender, "First time played: §e" + convertTime(offlinePlayer.getFirstPlayed()));
        sendMessage(commandSender, "Banned: §e" + offlinePlayer.isBanned());
        sendMessage(commandSender, "Whitelisted: §e" + offlinePlayer.isWhitelisted());

        return true;
    }

}
