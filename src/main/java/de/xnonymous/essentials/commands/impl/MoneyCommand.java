package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.eco.EcoUser;
import de.xnonymous.essentials.utils.UUIDFetcher;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MoneyCommand extends Command {
    public MoneyCommand() {
        super("money", "Shows your money", true, "money [user]", "bal", "balance");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            if (!isPlayer(commandSender)) return true;
            Player player = ((Player) commandSender);
            EcoUser ecoUser = Main.getInstance().getEcoManager().getEcoUser(player.getUniqueId());
            sendMessage(commandSender, getText("balance").replaceAll("%money%", String.valueOf(ecoUser.getBal())));
            return true;
        }
        if (args.length == 1) {
            if (!commandSender.hasPermission("essentials.command.money.other")) {
                sendMessage(commandSender, getText("no-perm"));
                return true;
            }
            UUID uuid = UUIDFetcher.getUUID(args[0]);
            if (uuid == null) {
                sendMessage(commandSender, getText("never-played"));
                return true;
            }
            EcoUser ecoUser = Main.getInstance().getEcoManager().getEcoUser(uuid);
            if (ecoUser == null) {
                sendMessage(commandSender, getText("never-played"));
                return true;
            }
            sendMessage(commandSender, getText("balance-other").replaceAll("%target%", args[0]).replaceAll("%money%", String.valueOf(ecoUser.getBal())));
            return true;
        }
        return false;
    }
}
