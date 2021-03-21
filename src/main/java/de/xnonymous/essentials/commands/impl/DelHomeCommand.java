package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.config.impl.HomeConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class DelHomeCommand extends Command {
    public DelHomeCommand() {
        super("delhome", "Deletes a home", false, "delhome <name>", "deletehome");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        if (args.length == 1) {
            HomeConfig homeConfig = ((HomeConfig) Main.getInstance().getConfigRegistry().getByClass(HomeConfig.class));
            Player player = ((Player) commandSender);
            String home = args[0].toLowerCase();

            homeConfig.getCfg().set(player.getUniqueId().toString() + "." + home, null);
            sendMessage(player, getText("home-deleted").replaceAll("%name%", home));
            homeConfig.save();
            return true;
        }

        return false;
    }
}
