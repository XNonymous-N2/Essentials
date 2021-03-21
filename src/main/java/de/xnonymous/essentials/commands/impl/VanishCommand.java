package de.xnonymous.essentials.commands.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class VanishCommand extends Command {
    public VanishCommand() {
        super("vanish", "No one can see you", false, "vanish", "v");
    }

    private final ArrayList<Player> players = new ArrayList<>();

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        if (!players.contains(player))
            hide(player);
        else
            show(player);
        return true;
    }

    @Override
    public void onPlayerLogin(PlayerLoginEvent event) {
        players.forEach(this::hide);
    }

    @Override
    public void onQuit(PlayerQuitEvent event) {
        if (players.contains(event.getPlayer()))
            show(event.getPlayer());
    }

    private void hide(Player player) {
        WrappedGameProfile gameProfile = WrappedGameProfile.fromPlayer(player);
        PlayerInfoData playerInfoData = new PlayerInfoData(gameProfile, 20, EnumWrappers.NativeGameMode.NOT_SET, WrappedChatComponent.fromText(player.getDisplayName()));
        PacketContainer packetRemove = Main.getInstance().getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        packetRemove.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
        packetRemove.getPlayerInfoDataLists().write(0, Collections.singletonList(playerInfoData));
        sendPacket(packetRemove);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.canSee(player))
                onlinePlayer.hidePlayer(player);
        }
        players.add(player);
        sendMessage(player, getText("vanish-hide"));
    }

    private void show(Player player) {
        WrappedGameProfile gameProfile = WrappedGameProfile.fromPlayer(player);
        PlayerInfoData playerInfoData = new PlayerInfoData(gameProfile, 20, EnumWrappers.NativeGameMode.NOT_SET, WrappedChatComponent.fromText(player.getDisplayName()));
        PacketContainer packetAdd = Main.getInstance().getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        packetAdd.getPlayerInfoDataLists().write(0, Collections.singletonList(playerInfoData));
        packetAdd.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        sendPacket(packetAdd);
        players.remove(player);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.showPlayer(player);
        }
        sendMessage(player, getText("vanish-show"));
    }

    private void sendPacket(PacketContainer packet) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            try {
                Main.getInstance().getProtocolManager().sendServerPacket(player, packet);
            } catch (InvocationTargetException e) {
                sendMessage("Could not send packet" + e);
            }
        });
    }
}
