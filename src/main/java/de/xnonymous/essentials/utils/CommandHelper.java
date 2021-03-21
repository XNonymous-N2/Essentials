package de.xnonymous.essentials.utils;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.config.impl.DefaultConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandHelper extends ChatUtilsHook {

    public boolean isPlayer(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            sendMessage(commandSender, getText("not-player"));
            return false;
        }
        return true;
    }

    public boolean isOnline(CommandSender commandSender, Player player) {
        if (player == null) {
            sendMessage(commandSender, getText("target-not-online"));
            return false;
        }
        return true;
    }

    public boolean isNumber(CommandSender commandSender, String num) {
        try {
            Integer.valueOf(num);
            return true;
        } catch (Exception ignored) {
            if (commandSender != null)
                sendMessage(commandSender, num + " is not a valid number!");
            return false;
        }

    }

    public Integer getNumber(String num) {
        if (isNumber(null, num))
            return Integer.valueOf(num);
        return 0;
    }

    public String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat(((DefaultConfig) Main.getInstance().getConfigRegistry().getByClass(DefaultConfig.class)).getTime());
        return format.format(date);
    }

}
