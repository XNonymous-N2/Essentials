package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.config.Config;
import de.xnonymous.essentials.config.impl.CookieClickersConfig;
import de.xnonymous.essentials.config.impl.HologramConfig;
import de.xnonymous.essentials.cookie.CookieClicker;
import de.xnonymous.essentials.hologram.Hologram;
import de.xnonymous.essentials.inventorys.CookieClickerInventory;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.UUID;

public class CookieClickerCommand extends Command {
    public CookieClickerCommand() {
        super("cookieclicker", "Setups a cookie clicker", false, "cookieclicker");
    }

    private final ArrayList<UUID> placer = new ArrayList<>();

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        UUID uuid = player.getUniqueId();
        if (placer.contains(uuid)) {
            placer.remove(uuid);
            sendMessage(commandSender, getText("cookieclicker-abort"));
        } else {
            placer.add(uuid);
            sendMessage(commandSender, getText("cookieclicker-started"));
        }
        return true;
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (placer.contains(uuid) && event.getBlockPlaced().getType().equals(Material.GOLD_BLOCK) && !event.isCancelled() && event.canBuild()) {
            Block block = event.getBlockPlaced();
            Location location = block.getLocation();
            Hologram hologram = (new Hologram("§e§lCookie clicker", location.clone().add(0.5, -0.5, 0.5)));
            System.out.println(hologram.getUuid().toString());
            System.out.println(hologram.getUuid().toString());
            Main.getInstance().getConfigRegistry().getByClass(CookieClickersConfig.class).getCfg().set(hologram.getUuid().toString(), block.getLocation());
            Main.getInstance().getConfigRegistry().getByClass(CookieClickersConfig.class).save();
            Main.getInstance().getHologramManager().addHologramAndConfig(hologram);
            Main.getInstance().getCookieClickerManager().addCookieClicker(new CookieClicker(hologram.getUuid(), location.clone()));
            placer.remove(uuid);
            sendMessage(player, getText("cookieclicker-placed"));
        }
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.GOLD_BLOCK) && !event.isCancelled()) {
            if (Main.getInstance().getCookieClickerManager().isLocation(event.getBlock().getLocation())) {
                CookieClicker cookieClicker = Main.getInstance().getCookieClickerManager().getCookieClicker(event.getBlock().getLocation());
                CookieClickersConfig config = (CookieClickersConfig) Main.getInstance().getConfigRegistry().getByClass(CookieClickersConfig.class);
                config.getCfg().set(cookieClicker.getUuid().toString(), null);
                config.save();
                Main.getInstance().getCookieClickerManager().removeCookieClicker(cookieClicker);
                Main.getInstance().getHologramManager().removeHologram(cookieClicker.getUuid());
                sendMessage(event.getPlayer(), "Ouh. :sadcookie:");
            }
        }
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && Main.getInstance().getCookieClickerManager().isLocation(event.getClickedBlock().getLocation())) {
            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                CookieClickersConfig config = (CookieClickersConfig) Main.getInstance().getConfigRegistry().getByClass(CookieClickersConfig.class);
                int anInt = config.getCfg().getInt(event.getPlayer().getUniqueId().toString());
                anInt += 1;
                config.getCfg().set(event.getPlayer().getUniqueId().toString(), anInt);
                config.save();
                sendSound(event.getPlayer(), Sound.UI_BUTTON_CLICK);
                sendHover(event.getPlayer(), "§7[§a+§7] §eCookie §8[§e" + anInt + "§8]");
            } else {
                CookieClickerInventory.INVENTORY.open(event.getPlayer());
            }
        }
    }
}
