package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.config.impl.HomeConfig;
import de.xnonymous.essentials.config.impl.IPConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.ArrayList;

public class CheckIPCommand extends Command {
    public CheckIPCommand() {
        super("checkip", "Checks a users IP address", true, "checkip <user>");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;

            IPConfig ipConfig = ((IPConfig) Main.getInstance().getConfigRegistry().getByClass(IPConfig.class));
            ArrayList<String> names = new ArrayList<>();
            ipConfig.getCfg().getKeys(false).stream().filter(s -> ipConfig.getCfg().getString(s).equals(player.getAddress().getAddress().getHostAddress())).forEach(names::add);
            sendMessage(commandSender, "IP: " + (commandSender.hasPermission("essentials.command.checkip.seeip") ? player.getAddress().getAddress().getHostAddress() : "CENSORED"));
            sendMessage(commandSender, "Names:§e " + String.join(", ", names));
            if (!Bukkit.getServer().getOnlineMode()) {
                sendMessage(commandSender, "The online mode is deactivated.");
                sendMessage(commandSender, "The IP addresses may be incorrect.");
            }
            return true;
        }

        return false;
    }

    @Override
    public void onPlayerLogin(PlayerLoginEvent event) {
        IPConfig ipConfig = ((IPConfig) Main.getInstance().getConfigRegistry().getByClass(IPConfig.class));

        ipConfig.getCfg().set(event.getPlayer().getName(), event.getAddress().getHostAddress());
        ipConfig.save();
    }
}
