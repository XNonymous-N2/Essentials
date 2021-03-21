package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.config.ConfigRegistry;
import de.xnonymous.essentials.config.impl.BanConfig;
import de.xnonymous.essentials.utils.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class BanCommand extends Command {
    public BanCommand() {
        super("ban", "Bans a user", true, "ban <user> [reason]", "punish");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            ban(commandSender, args, getText("ban-default").replaceAll("\\[nl]", "\n"));
            return true;
        }
        if (args.length > 1) {
            String[] real = args.clone();
            real[0] = "";
            ban(commandSender, args, String.join(" ", real).replaceAll("&", "§").replaceAll("\\[nl]", "\n"));
            return true;
        }

        return false;
    }

    @Override
    public void onPlayerLogin(PlayerLoginEvent event) {
        try {
            BanConfig banConfig = (BanConfig) Main.getInstance().getConfigRegistry().getByClass(BanConfig.class);

            Set<String> keys = banConfig.getCfg().getKeys(false);

            if (banConfig.getCfg().getString(event.getPlayer().getUniqueId().toString() + ".reason") != null)
                event.disallow(PlayerLoginEvent.Result.KICK_BANNED, banConfig.getCfg().getString(event.getPlayer().getUniqueId().toString() + ".reason").replaceAll("%name%", event.getPlayer().getName()));

            for (String key : keys) {
                if (banConfig.getCfg().getString(key + ".ip").equals(event.getAddress().getHostAddress()))
                    event.disallow(PlayerLoginEvent.Result.KICK_BANNED, banConfig.getCfg().getString(key + ".reason").replaceAll("%name%", event.getPlayer().getName()));
            }
        } catch (Exception ignored) {

        }
    }

    private void ban(CommandSender commandSender, String[] args, String reason) {
        BanConfig banConfig = (BanConfig) Main.getInstance().getConfigRegistry().getByClass(BanConfig.class);
        Player player = Bukkit.getPlayer(args[0]);
        UUID uuid = UUIDFetcher.getUUID(args[0]);

        if (uuid == null) {
            sendMessage(commandSender, getText("never-played"));
            return;
        }

        banConfig.getCfg().set(uuid.toString() + ".reason", reason);
        banConfig.getCfg().set(uuid.toString() + ".ip", player == null ? "none" : (Bukkit.getServer().getOnlineMode() ? player.getAddress().getAddress().getHostAddress() : "serverisofflinemode"));
        banConfig.save();
        if (player != null)
            player.kickPlayer(banConfig.getCfg().getString(uuid.toString() + ".reason").replaceAll("%name%", player.getName()));
        sendMessage(getText("banned").replaceAll("%name%", commandSender.getName()).replaceAll("%target%", args[0]), "essentials.command.ban.see");
    }
}
