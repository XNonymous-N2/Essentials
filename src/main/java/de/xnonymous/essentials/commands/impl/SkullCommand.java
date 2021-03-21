package de.xnonymous.essentials.commands.impl;

import com.cryptomorin.xseries.XMaterial;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.utils.ItemBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkullCommand extends Command {
    public SkullCommand() {
        super("skull", "Gives you from a player the head", false, "skull <user>", "head");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            Player player = (Player) commandSender;
            try {
                player.getInventory().addItem(ItemBuilder.builder()
                        .color(3)
                        .material(XMaterial.PLAYER_HEAD.parseMaterial())
                        .skull(args[0]).build().toItemStack());
            }catch (Exception ignored) {
                sendMessage(player, getText("inventory-full"));
            }
            return true;
        }
        return false;
    }

}
