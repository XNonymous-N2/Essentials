package de.xnonymous.essentials.commands;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.config.Config;
import de.xnonymous.essentials.structure.INameable;
import de.xnonymous.essentials.utils.ChatUtilsHook;
import de.xnonymous.essentials.utils.CommandHelper;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;

@Getter
@Setter
public class Command extends CommandHelper implements INameable {

    private String name;
    private String description;
    private String[] alias;
    private String syntax;
    private boolean console;

    public Command(String name, String description, boolean console, String syntax, String... alias) {
        this.name = name;
        this.description = description;
        this.alias = alias;
        this.syntax = syntax;
        this.console = console;
    }

    public boolean onExecute(CommandSender commandSender, String[] args) {
        return false;
    }

    public void onLoad() {

    }

    public void onQuit(PlayerQuitEvent event) {

    }

    public void onMove(PlayerMoveEvent event) {

    }

    public void onChat(AsyncPlayerChatEvent event) {

    }

    public void onEntityDamage(EntityDamageEvent event) {

    }

    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {

    }

    public void onInventoryDrag(InventoryDragEvent event) {

    }

    public void onInventoryClick(InventoryClickEvent event) {

    }

    public void onInventoryClose(InventoryCloseEvent event) {

    }

    public void onPlayerLogin(PlayerLoginEvent event) {

    }

    public void onPlayerInteract(PlayerInteractEvent event) {

    }

    public void onBlockPlace(BlockPlaceEvent event) {

    }

    public void onBlockBreak(BlockBreakEvent event) {

    }

    public void onJoin(PlayerJoinEvent event) {

    }

}
