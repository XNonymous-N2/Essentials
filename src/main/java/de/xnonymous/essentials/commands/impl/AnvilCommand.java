package de.xnonymous.essentials.commands.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.InvocationTargetException;

public class AnvilCommand extends Command {
    public AnvilCommand() {
        super("Anvil", "Opens a anvil", false, "anvil");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;

        if (player != null) {
            sendMessage(commandSender, "Anvil is currently under development.");
            return true;
        }

        PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.OPEN_WINDOW);
        packetContainer.getIntegers().write(1, 7);
        packetContainer.getChatComponents().write(0, WrappedChatComponent.fromText(player.getName() + "'s anvil"));
        try {
            Main.getInstance().getProtocolManager().sendServerPacket(player, packetContainer);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return true;
    }
}
