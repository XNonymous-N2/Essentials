package de.xnonymous.essentials.listener;

import de.xnonymous.essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Main.getInstance().getCommandRegistry().getObjects().forEach(command -> command.onJoin(event));
        Main.getInstance().getEcoManager().tryRegister(event.getPlayer().getUniqueId());
    }

}
