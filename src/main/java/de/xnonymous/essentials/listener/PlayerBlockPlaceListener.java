package de.xnonymous.essentials.listener;

import de.xnonymous.essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerBlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Main.getInstance().getCommandRegistry().getObjects().forEach(command -> command.onBlockPlace(event));
    }
}
