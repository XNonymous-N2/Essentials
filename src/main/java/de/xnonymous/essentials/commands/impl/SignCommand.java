package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import de.xnonymous.essentials.config.Config;
import de.xnonymous.essentials.config.impl.DefaultConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SignCommand extends Command {
    public SignCommand() {
        super("sign", "Signs a item", false, "sign <text>", "signature");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 0)
            return false;
        Player player = (Player) commandSender;

        ItemStack itemInHand = player.getInventory().getItemInHand();
        if (itemInHand.getItemMeta() == null) {
            sendMessage(player, getText("rename-error"));
            return true;
        }
        ItemMeta a = itemInHand.getItemMeta();
        a.setDisplayName(String.join(" ", args).replaceAll("&", "§"));
        DefaultConfig defaultConfig = (DefaultConfig) Main.getInstance().getConfigRegistry().getByClass(DefaultConfig.class);
        String b = defaultConfig.getCfg().getString("sign-format");

        List<String> list = new ArrayList<>();
        for (String s : b.split("\\[nl]")) {
            list.add(s.replaceAll("&", "§").replaceAll("%date%", convertTime(System.currentTimeMillis())).replaceAll("%player%", commandSender.getName()));
        }
        a.setLore(list);

        itemInHand.setItemMeta(a);

        return true;
    }
}
