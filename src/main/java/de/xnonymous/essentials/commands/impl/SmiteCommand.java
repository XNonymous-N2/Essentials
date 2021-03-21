package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

public class SmiteCommand extends Command {
    public SmiteCommand() {
        super("smite", "Spawns a Smite at your position", true, "smite [user]");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        if (args.length == 0) {
            if (!isPlayer(commandSender)) return true;
            Player player = (Player) commandSender;
            Block block = getTargetBlock(player, 300);
            if (block.getType().equals(Material.AIR)) {
                sendMessage(commandSender, getText("smite-air"));
                return true;
            }
            player.getWorld().strikeLightning(block.getLocation());
            sendMessage(commandSender, getText("smite"));
            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;
            player.getWorld().strikeLightning(player.getLocation());
            sendMessage(commandSender, getText("smite"));
            return true;
        }

        return false;
    }

    public final Block getTargetBlock(Player player, int range) {
        BlockIterator iter = new BlockIterator(player, range);
        Block lastBlock = iter.next();
        while (iter.hasNext()) {
            lastBlock = iter.next();
            if (lastBlock.getType() == Material.AIR) {
                continue;
            }
            break;
        }
        return lastBlock;
    }

}
