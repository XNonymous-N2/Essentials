package de.xnonymous.essentials.listener;

import de.xnonymous.essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onWrite(AsyncPlayerChatEvent event) {
        Main.getInstance().getCommandRegistry().getObjects().forEach(command -> command.onChat(event));
    }

}
