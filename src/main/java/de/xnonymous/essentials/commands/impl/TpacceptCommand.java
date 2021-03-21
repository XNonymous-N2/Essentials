package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class TpacceptCommand extends Command {
    public TpacceptCommand() {
        super("tpaccept", "Accept a teleport request", false, "tpaccept");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = ((Player) commandSender);
        UUID uuid = player.getUniqueId();
        if (args.length == 0) {
            if (TpaCommand.users.containsValue(uuid)) {
                AtomicReference<UUID> uuid3 = new AtomicReference<>();
                TpaCommand.users.forEach((uuid1, uuid2) -> {
                    if (uuid2 == uuid) {
                        uuid3.set(uuid1);
                        handle(uuid1, uuid2);
                    }
                });
                TpaCommand.users.remove(uuid3.get());
            }
            else
                sendMessage(player, getText("tpa-not"));
            return true;
        }

        return false;
    }

    private void handle(UUID uuid, UUID uuid1) {
        Player player = Bukkit.getPlayer(uuid);
        Player player1 = Bukkit.getPlayer(uuid1);

        if (player == null || player1 == null)
            return;

        player.teleport(player1);
        sendMessage(player, getText("tpa-accept").replaceAll("%player%", player1.getName()));
        sendMessage(player1, getText("tpa-accept-other").replaceAll("%player%", player.getName()));

    }
}
