package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TpaCommand extends Command {
    public TpaCommand() {
        super("tpa", "Send a teleport request", false, "tpa <user>", "tprequest");
    }

    public static HashMap<UUID, UUID> users = new HashMap<>();

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length != 1)
            return false;

        Player player = (Player) commandSender;

        if (users.containsKey(player.getUniqueId())) {
            sendMessage(player, getText("tpa-already-send"));
            return true;
        }

        Player player1 = Bukkit.getPlayer(args[0]);

        if (!isOnline(commandSender, player1)) return true;

        users.put(player.getUniqueId(), player1.getUniqueId());

        for (String s : getText("tpa-send").replaceAll("%player%", player1.getName()).split("\\[nl]")) {
            sendMessage(player, s);
        }
        for (String s : getText("tpa-send-other").replaceAll("%player%", player.getName()).split("\\[nl]")) {
            sendMessage(player1, s);
        }

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            if (users.containsKey(player.getUniqueId())) {
                users.remove(player.getUniqueId());
                sendMessage(player, getText("tpa-time-expired"));
            }
        }, 20 * 60);

        return true;
    }
}
