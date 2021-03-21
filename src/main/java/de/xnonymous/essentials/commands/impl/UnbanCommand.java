package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.config.impl.BanConfig;
import de.xnonymous.essentials.config.impl.HomeConfig;
import de.xnonymous.essentials.utils.UUIDFetcher;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class UnbanCommand extends Command {
    public UnbanCommand() {
        super("unban", "Unbans a player", true, "unban <user>", "pardon");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        if (args.length == 1) {
            BanConfig banConfig = (BanConfig) Main.getInstance().getConfigRegistry().getByClass(BanConfig.class);
            UUID uuid = UUIDFetcher.getUUID(args[0]);

            if (uuid == null) {
                sendMessage(commandSender, getText("never-played"));
                return true;
            }
            banConfig.getCfg().set(uuid.toString(), null);
            banConfig.save();
            sendMessage(getText("ban-pardon").replaceAll("%name%", commandSender.getName()).replaceAll("%target%", args[0]), "essentials.command.ban.see");
            return true;
        }

        return false;
    }
}
