package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.UUID;

public class GodCommand extends Command {
    public GodCommand() {
        super("god", "Makes you imun against damage", true, "god [user]");
    }

    private final ArrayList<UUID> gods = new ArrayList<>();

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {

        if (args.length == 0) {
            if (!isPlayer(commandSender)) return true;

            Player player = (Player) commandSender;

            setGod(player, null);

            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!isOnline(commandSender, player)) return true;

            setGod(player, commandSender);

            return true;
        }

        return false;
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && gods.contains(event.getEntity().getUniqueId()))
            event.setCancelled(true);
    }

    private void setGod(Player player, CommandSender commandSender) {
        if (!gods.contains(player.getUniqueId())) {
            gods.add(player.getUniqueId());
            sendMessage(player, getText("god-on"));
            if (commandSender != null)
                sendMessage(commandSender, getText("god-on-target").replaceAll("%player%", player.getName()));
        }
        else {
            gods.remove(player.getUniqueId());
            sendMessage(player, getText("god-off"));
            if (commandSender != null)
                sendMessage(commandSender, getText("god-off-target").replaceAll("%player%", player.getName()));
        }
    }
}
