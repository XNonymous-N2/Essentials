package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import java.util.ArrayList;
import java.util.UUID;

public class InvseeCommand extends Command {
    public InvseeCommand() {
        super("invsee", "See the inventory of a player", false, "invsee <user>");
    }

    private final ArrayList<UUID> uuids = new ArrayList<>();

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length != 1)
            return false;
        Player player = ((Player) commandSender);
        Player player1 = Bukkit.getPlayer(args[0]);
        if (!isOnline(commandSender, player1)) return true;

        player.openInventory(player1.getInventory());
        uuids.add(player.getUniqueId());
        return true;
    }

    @Override
    public void onInventoryDrag(InventoryDragEvent event) {
        if (uuids.contains(event.getWhoClicked().getUniqueId()))
            event.setCancelled(true);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        if (uuids.contains(event.getWhoClicked().getUniqueId()))
            event.setCancelled(true);
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent event) {
        uuids.removeIf(uuid -> event.getPlayer().getUniqueId().equals(uuid));
    }
}
