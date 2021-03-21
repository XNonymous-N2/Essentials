package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.eco.EcoUser;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand extends Command {
    public PayCommand() {
        super("pay", "Gives a user money", false, "pay <user> <howmuch>");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 2) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;
            if (!isNumber(commandSender, args[1])) return true;
            Integer number = getNumber(args[1]);
            EcoUser target = Main.getInstance().getEcoManager().getEcoUser(player.getUniqueId());
            EcoUser p = Main.getInstance().getEcoManager().getEcoUser(((Player) commandSender).getUniqueId());
            if (!p.hasEnough(number)) {
                sendMessage(commandSender, getText("pay-not-enough"));
                return true;
            }
            p.removeMoney(number);
            target.addMoney(number);
            for (String s : getText("payment-complete").split("\\[nl]")) {
                sendMessage(commandSender, s.replaceAll("%money%", String.valueOf(p.getBal()))
                        .replaceAll("%howmuch%", args[1]));
            }
            for (String s : getText("payment-complete-target").split("\\[nl]")) {
                sendMessage(player, s.replaceAll("%money%", String.valueOf(target.getBal()))
                        .replaceAll("%howmuch%", args[1]));
            }
            return true;
        }

        return false;
    }
}
