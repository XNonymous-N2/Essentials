package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.config.impl.WarpsConfig;
import de.xnonymous.essentials.warp.Warp;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand extends Command {
    public SetWarpCommand() {
        super("setwarp", "Creates a warp at your position", false, "setwarp <name>", "createwarp");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            WarpsConfig warpConfig = (WarpsConfig) Main.getInstance().getConfigRegistry().getByClass(WarpsConfig.class);
            warpConfig.getCfg().set(args[0], ((Player) commandSender).getLocation());
            warpConfig.save();
            Main.getInstance().getWarpManager().getWarps().add(new Warp(args[0], ((Player) commandSender).getLocation()));
            sendMessage(commandSender, getText("warp-created").replaceAll("%warp%", args[0]));
            return true;
        }
        return false;
    }
}
