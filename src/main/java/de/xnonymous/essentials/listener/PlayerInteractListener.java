package de.xnonymous.essentials.listener;

import de.xnonymous.essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Main.getInstance().getCommandRegistry().getObjects().forEach(command -> command.onPlayerInteract(event));
    }

}
