package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.config.impl.HomeConfig;
import javafx.print.PageLayout;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import javax.sound.midi.ShortMessage;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class SetHomeCommand extends Command {
    public SetHomeCommand() {
        super("sethome", "Sets your home at your currently position", false, "sethome [name]");
    }


    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        Player player = ((Player) commandSender);
        HomeConfig homeConfig = ((HomeConfig) Main.getInstance().getConfigRegistry().getByClass(HomeConfig.class));


        if (args.length == 0) {
            if (reachedLimit(player.getUniqueId(), "home")) {
                sendMessage(player, getText("home-limit"));
                return true;
            }
            homeConfig.getCfg().set(player.getUniqueId().toString() + ".home", player.getLocation());
            sendMessage(player, getText("set-home").replaceAll("%name%", "home"));
            homeConfig.save();
            return true;
        }
        if (args.length == 1) {
            args[0] = args[0].toLowerCase();
            if (reachedLimit(player.getUniqueId(), args[0])) {
                sendMessage(player, getText("home-limit"));
                return true;
            }
            homeConfig.getCfg().set(player.getUniqueId().toString() + "." + args[0], player.getLocation());
            sendMessage(player, getText("set-home").replaceAll("%name%", args[0]));
            homeConfig.save();
            return true;
        }

        return false;
    }

    private boolean reachedLimit(UUID uuid, String var) {
        HomeConfig homeConfig = ((HomeConfig) Main.getInstance().getConfigRegistry().getByClass(HomeConfig.class));
        ConfigurationSection configurationSection = homeConfig.getCfg().getConfigurationSection(uuid.toString());
        if (configurationSection == null)
            return false;
        Set<String> keys = configurationSection.getKeys(false);
        return keys.size() >= homeConfig.getCfg().getInt("home-limit") && !keys.contains(var);
    }
}
