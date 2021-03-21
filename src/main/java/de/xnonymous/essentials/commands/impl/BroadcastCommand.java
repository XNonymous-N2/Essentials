package de.xnonymous.essentials.commands.impl;


import de.xnonymous.essentials.commands.Command;
import org.bukkit.command.CommandSender;

public class BroadcastCommand extends Command {
    public BroadcastCommand() {
        super("broadcast", "Send a message for everyone", true, "broadcast <message>", "alert", "all");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length != 0) {
            sendMessage(String.join(" ", args).replaceAll("&", "§"));
            return true;
        }
        return false;
    }
}
