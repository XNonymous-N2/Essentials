package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand extends Command {
    public SpeedCommand() {
        super("speed", "Lets you fly/walk faster", false, "speed <1-10>");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            if (!isNumber(commandSender, args[0])) return true;

            Player player = ((Player) commandSender);
            float n = getNumber(args[0]);

            if (n <= 10 && n >= 1) {
                boolean a = false;
                n = n / 10;
                if (player.isFlying()) {
                    a = true;
                    player.setFlySpeed(n);
                } else {
                    player.setWalkSpeed(n);
                }
                sendMessage(player, getText("speed").replaceAll("%number%", String.valueOf(n * 10)).replaceAll("%type%", a ? getText("speed-type-fly") : getText("speed-type-walk")));
                return true;
            }
        }
        return false;
    }
}
