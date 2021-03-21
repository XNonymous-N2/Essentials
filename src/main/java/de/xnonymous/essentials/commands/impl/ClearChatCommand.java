package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Random;

public class ClearChatCommand extends Command {
    public ClearChatCommand() {
        super("ClearChat", "Clears the chat", true, "clearchat", "cc", "chatclear", "cleanchat");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        Random random = new Random();
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (!player.hasPermission("essentials.clear.bypass"))
                for (int i = 0; i < 141; i++) {
                    sendMessage(player, "§" + random.nextInt(9) + "§" + random.nextInt(9) + "§" + random.nextInt(9) + "§" + random.nextInt(9) + "§" + random.nextInt(9) + "§" + random.nextInt(9));
                }
            else
                sendMessage(player, "Bypassing chat clear.");
            sendMessage(player, getText("after-clear").replaceAll("%player%", commandSender.getName()));
        });

        return true;
    }
}
