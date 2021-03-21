package de.xnonymous.essentials.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.xnonymous.essentials.Main;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.logging.Level;

@Getter
@Setter
public class ChatUtilsHook {

    public static HashMap<String, String> textList = new HashMap<>();

    public ChatUtilsHook() {

    }

    public void sendMessage(String message) {
        Bukkit.broadcastMessage(Main.getInstance().getPrefix() + message);
    }

    public void sendMessage(CommandSender commandSender, String message) {
        message = message.replaceAll("%player%", commandSender.getName());
        commandSender.sendMessage(Main.getInstance().getPrefix() + message);
    }

    public String getText(String name) {
        return textList.get(name).replaceAll("&", "§");
    }

    public void sendMessage(String message, String permission) {
        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(permission)).forEach(player -> player.sendMessage(Main.getInstance().getPrefix() + message));
        Bukkit.getLogger().log(Level.WARNING, Main.getInstance().getPrefix() + message);
    }

    public void sendHover(Player player, String hover) {
        PacketContainer chat = new PacketContainer(PacketType.Play.Server.CHAT);
        chat.getChatTypes().write(0, EnumWrappers.ChatType.GAME_INFO);
        chat.getChatComponents().write(0, WrappedChatComponent.fromText(hover));
        sendPackets(player, chat);
    }

    public void sendSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 20f, 20f);
    }

    private void sendPackets(Player player, PacketContainer packetContainer) {
        try {
            Main.getInstance().getProtocolManager().sendServerPacket(player, packetContainer);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
