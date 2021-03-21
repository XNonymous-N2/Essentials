package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.inventorys.CookieClickerInventory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.UUID;

public class AfkCommand extends Command {
    public AfkCommand() {
        super("afk", "Sets you afk", false, "afk", "away", "nothere");
    }

    private final ArrayList<UUID> afk = new ArrayList<>();

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        if (afk.contains(player.getUniqueId())) {
            removeFromAFK(player);
        } else {
            afk.add(player.getUniqueId());
            sendMessage(player, getText("afk"));
            sendMessage(getText("afk-all").replaceAll("%player%", player.getName()));
        }
        return true;
    }


    @Override
    public void onQuit(PlayerQuitEvent event) {
        afk.removeIf(uuid -> uuid.equals(event.getPlayer().getUniqueId()));
    }

    @Override
    public void onMove(PlayerMoveEvent event) {
        removeFromAFK(event.getPlayer());
    }

    @Override
    public void onChat(AsyncPlayerChatEvent event) {
        removeFromAFK(event.getPlayer());
    }

    private void removeFromAFK(Player player) {
        if (afk.contains(player.getUniqueId())) {
            afk.removeIf(uuid -> uuid.equals(player.getUniqueId()));
            sendMessage(getText("un-afk-all").replaceAll("%player%", player.getName()));
            sendMessage(player, getText("un-afk"));
        }
    }
}
