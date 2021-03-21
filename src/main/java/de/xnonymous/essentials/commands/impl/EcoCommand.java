package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.eco.EcoUser;
import de.xnonymous.essentials.utils.UUIDFetcher;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class EcoCommand extends Command {
    public EcoCommand() {
        super("eco", "Admin panel for the economy", true, "eco <reset/give/set/remove> <user> [bal]", "economy", "admineco");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 2 || args.length == 3) {
            UUID uuid = UUIDFetcher.getUUID(args[1]);
            if (uuid == null) {
                sendMessage(commandSender, getText("never-played"));
                return true;
            }
            EcoUser ecoUser = Main.getInstance().getEcoManager().getEcoUser(uuid);
            if (ecoUser == null) {
                sendMessage(commandSender, getText("never-played"));
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "reset":
                    ecoUser.reset();
                    sendMessage(commandSender, getText("eco-reset").replaceAll("%target%", args[1]));
                    break;
                case "give":
                    if (args.length == 3) {
                        if (!isNumber(commandSender, args[2])) return true;
                        Integer number = getNumber(args[2]);
                        ecoUser.addMoney(number);
                        sendMessage(commandSender, getText("eco-give").replaceAll("%target%", args[1]).replaceAll("%money%", args[2]));
                        break;
                    }
                    return false;
                case "remove":
                    if (args.length == 3) {
                        if (!isNumber(commandSender, args[2])) return true;
                        Integer number = getNumber(args[2]);
                        ecoUser.removeMoney(number);
                        sendMessage(commandSender, getText("eco-remove").replaceAll("%target%", args[1]).replaceAll("%money%", args[2]));
                        break;
                    }
                    return false;
                case "set":
                    if (args.length == 3) {
                        if (!isNumber(commandSender, args[2])) return true;
                        Integer number = getNumber(args[2]);
                        ecoUser.setMoney(number);
                        sendMessage(commandSender, getText("eco-set").replaceAll("%target%", args[1]).replaceAll("%money%", args[2]));
                        break;
                    }
                    return false;
                default:
                    return false;
            }
            return true;
        }
        return false;
    }
}
