package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.config.impl.InfoConfig;
import org.bukkit.command.CommandSender;

import java.util.List;

public class InfoCommand extends Command {
    public InfoCommand() {
        super("info", "Displays server information", true, "info", "motd", "messageoftheday", "today");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        InfoConfig infoConfig = ((InfoConfig) Main.getInstance().getConfigRegistry().getByClass(InfoConfig.class));
        List<String> info = infoConfig.getCfg().getStringList("info");
        info.forEach(s -> sendMessage(commandSender, s.replaceAll("&", "§")));
        return true;
    }
}
