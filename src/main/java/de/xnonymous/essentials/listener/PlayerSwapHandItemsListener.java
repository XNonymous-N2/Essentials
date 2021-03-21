package de.xnonymous.essentials.listener;

import de.xnonymous.essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandItemsListener implements Listener {

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        Main.getInstance().getCommandRegistry().getObjects().forEach(command -> command.onPlayerSwapHandItems(event));
    }

}
