package de.xnonymous.essentials.commands.impl;

import com.comphenix.protocol.PacketType;
import de.xnonymous.essentials.commands.Command;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class PowerToolCommand extends Command {


    public PowerToolCommand() {
        super("powertool", "Gives your item a special effect", false, "powertool [command]", "pt");
    }

    private final HashMap<UUID, HashMap<Material, String>> powerTools = new HashMap<>();

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = ((Player) commandSender);
        UUID uuid = player.getUniqueId();
        ItemStack itemInHand = player.getItemInHand();
        Material material = itemInHand.getType();
        if (args.length == 0) {
            if (powerTools.containsKey(uuid) && powerTools.get(uuid).containsKey(material)) {
                if (powerTools.get(uuid).size() == 1) {
                    powerTools.remove(uuid);
                } else {
                    HashMap<Material, String> materialStringHashMap = powerTools.get(uuid);
                    materialStringHashMap.remove(material);
                    powerTools.put(uuid, materialStringHashMap);
                }
                sendMessage(commandSender, getText("powertool-removed").replaceAll("%item%", material.name()));
            } else {
                sendMessage(commandSender, getText("powertool-no").replaceAll("%item%", material.name()));
            }
            return true;
        }
        HashMap<Material, String> materialStringHashMap;
        if (powerTools.containsKey(uuid)) {
            materialStringHashMap = powerTools.get(uuid);
        } else {
            materialStringHashMap = new HashMap<>();
        }
        materialStringHashMap.put(material, String.join(" ", args));
        powerTools.put(uuid, materialStringHashMap);
        sendMessage(commandSender, getText("powertool-apply").replaceAll("%item%", material.name()).replaceAll("%command%", String.join(" ", args)));
        return true;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        ItemStack itemStack = player.getItemInHand();
        Material material = itemStack.getType();
        if (powerTools.containsKey(uuid) && powerTools.get(uuid).containsKey(material)) {
            String cmd = powerTools.get(uuid).get(material);
            player.performCommand(cmd);
            event.setCancelled(true);
        }
    }
}
