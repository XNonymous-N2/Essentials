package de.xnonymous.essentials.commands.impl;

import com.comphenix.protocol.PacketType;
import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand extends Command {
    public GamemodeCommand() {
        super("gamemode", "Set your gamemode", true, "gamemode <0/1/2/3> [user]", "gm");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            if (!isPlayer(commandSender)) return true;
            Player player = (Player) commandSender;
            changeGM(player, args[0]);
            sendMessage(commandSender, getText("change-gamemode"));
            return true;
        }
        if (args.length == 2) {
            Player player = Bukkit.getPlayer(args[1]);
            if (!isOnline(commandSender, player)) return true;
            changeGM(player, args[0]);
            sendMessage(commandSender, getText("changed-gamemode"));
            sendMessage(player, getText("change-gamemode"));
            return true;
        }

        return false;
    }

    private void changeGM(Player player, String gm) {
        switch (gm) {

            case "0":
                player.setGameMode(GameMode.SURVIVAL);
                break;

            case "1":
                player.setGameMode(GameMode.CREATIVE);
                break;

            case "2":
                player.setGameMode(GameMode.ADVENTURE);
                break;

            case "3":
                player.setGameMode(GameMode.SPECTATOR);
                break;

        }
    }

}
