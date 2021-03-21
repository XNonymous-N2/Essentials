package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CraftCommand extends Command {
    public CraftCommand() {
        super("Craft", "Opens a workbench", false, "craft", "workbench", "wb", "craft");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        ((Player) commandSender).openWorkbench(((Player) commandSender).getLocation(), true);
        return true;
    }
}
