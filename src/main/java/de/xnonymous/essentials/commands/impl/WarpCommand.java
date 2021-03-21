package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.inventorys.WarpMenu;
import de.xnonymous.essentials.warp.Warp;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand extends Command {
    public WarpCommand() {
        super("warp", "Opens the warp menu or teleports you to them", false, "warp [warp]", "warps");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            Warp warp1 = Main.getInstance().getWarpManager().getWarps().stream().filter(warp -> warp.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null);
            if (warp1 != null) {
                ((Player) commandSender).teleport(warp1.getLocation());
                sendMessage(commandSender, getText("warp-teleport").replaceAll("%warp%", warp1.getName()));
            } else {
                sendMessage(commandSender, getText("warp-not-exist").replaceAll("%warp%", args[0]));
            }
            return true;
        }
        WarpMenu.getInventory(getText("warp-title"), getText("warp-bevor"), getText("warp-next"), getText("warp-close"), getText("warp-closelore"), getText("warp-chest-design")).open(((Player) commandSender));
        return true;
    }
}
