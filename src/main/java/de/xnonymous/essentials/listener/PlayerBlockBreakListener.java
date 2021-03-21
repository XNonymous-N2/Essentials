package de.xnonymous.essentials.listener;

import de.xnonymous.essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Main.getInstance().getCommandRegistry().getObjects().forEach(command -> command.onBlockBreak(event));
    }

}
