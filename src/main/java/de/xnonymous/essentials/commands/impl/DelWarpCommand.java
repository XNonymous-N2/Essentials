package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.config.impl.WarpsConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarpCommand extends Command {
    public DelWarpCommand() {
        super("delwarp", "Deletes a warp", true, "delwarp <warp>", "deletewarp", "removewarp");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            WarpsConfig warpConfig = (WarpsConfig) Main.getInstance().getConfigRegistry().getByClass(WarpsConfig.class);
            warpConfig.getCfg().set(args[0], ((Player) commandSender).getLocation());
            warpConfig.save();
            Main.getInstance().getWarpManager().getWarps().removeIf(warp -> warp.getName().equalsIgnoreCase(args[0]));
            sendMessage(commandSender, getText("warp-deleted").replaceAll("%warp%", args[0]));
            return true;
        }
        return false;
    }
}
