package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.config.impl.HomeConfig;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Set;

public class HomeCommand extends Command {
    public HomeCommand() {
        super("home", "Teleports you to your home", false, "home [name]", "h");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = ((Player) commandSender);
        HomeConfig homeConfig = ((HomeConfig) Main.getInstance().getConfigRegistry().getByClass(HomeConfig.class));

        if (args.length == 0) {
            ConfigurationSection configurationSection = homeConfig.getCfg().getConfigurationSection(player.getUniqueId().toString());
            if (configurationSection == null) {
                sendMessage(player, getText("no-homes"));
                return true;
            }
            Set<String> keys = configurationSection.getKeys(false);
            if (keys.size() > 1) {
                sendMessage(player, "Homes: §e" + String.join(", ", keys));
                return true;
            }
            if (keys.size() == 0) {
                sendMessage(player, getText("no-homes"));
                return true;
            }
            teleportToHome(player, keys.stream().findFirst().orElse("home"));
            return true;
        }
        if (args.length == 1) {
            ConfigurationSection configurationSection = homeConfig.getCfg().getConfigurationSection(player.getUniqueId().toString());
            if (configurationSection == null) {
                sendMessage(player, getText("no-homes"));
                return true;
            }
            Set<String> keys = configurationSection.getKeys(false);
            if (keys.contains(args[0])) {
                teleportToHome(player, args[0]);
                return true;
            }
            sendMessage(player, "Homes: §e" + String.join(", ", keys));
            return true;
        }

        return false;
    }

    private void teleportToHome(Player player, String home) {
        HomeConfig homeConfig = ((HomeConfig) Main.getInstance().getConfigRegistry().getByClass(HomeConfig.class));
        player.teleport((Location) homeConfig.getCfg().get(player.getUniqueId().toString() + "." + home));
        sendMessage(player, getText("home").replaceAll("%name%", home));
    }
}
