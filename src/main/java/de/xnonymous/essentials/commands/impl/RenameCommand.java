package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand extends Command {
    public RenameCommand() {
        super("Rename", "Renames a item", false, "rename <name>");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        if (args.length >= 1) {
            Player player = (Player) commandSender;

            ItemStack itemInMainHand = player.getInventory().getItemInHand();
            ItemMeta itemMeta = itemInMainHand.getItemMeta();

            if (itemMeta == null) {
                sendMessage(player, getText("rename-error"));
                return true;
            }

            itemMeta.setDisplayName(String.join(" ", args).replaceAll("&", "§"));

            itemInMainHand.setItemMeta(itemMeta);
            return true;
        }

        return false;
    }
}
