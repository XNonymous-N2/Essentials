package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class EssentialsCommand extends Command {
    public EssentialsCommand() {
        super("essentials", "Useful essentials help", true, "essentials <help/reload> [page]", "e");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        /*
        System.out.println(Main.getInstance().getCommandRegistry().getObjects().size());

        int a = 0;
        for (int i = 0; i < 12; i++) {e hel
            System.out.println(getCommands(i).size());
            a += getCommands(i).size();
        }
        System.out.println(a);
        */
        if (args.length == 1 || args.length == 2) {
            switch (args[0]) {
                case "help":
                    int num = args.length == 2 ? (getNumber(args[1]) - 1) : 0;
                    int max = Math.round(((float) Main.getInstance().getCommandRegistry().getObjects().size() / 3));
                    List<Command> commands = getCommands(num);
                    if (commands == null) {
                        sendMessage(commandSender, "§eInvalid page.");
                        sendMessage(commandSender, "§e/essentials help <" + (num + 1) + "/" + max + ">");
                        break;
                    }
                    sendMessage(commandSender, "§7 ");
                    sendMessage(commandSender, "§b§lHelp:");
                    sendMessage(commandSender, "§7 ");
                    commands.forEach(command -> {
                        sendMessage(commandSender, "§e/" + command.getSyntax());
                        sendMessage(commandSender, command.getDescription() + ", §c" + Arrays.toString(command.getAlias()));
                        sendMessage(commandSender, "Permission: §eessentials.command." + command.getName().toLowerCase());
                        sendMessage(commandSender, " ");
                    });
                    if (num == 0) {
                        sendMessage(commandSender, "§a§l[] optional");
                        sendMessage(commandSender, "§a§l<> must be there");
                    }
                    sendMessage(commandSender, "Current Page: §e" + (num + 1));
                    sendMessage(commandSender, "§e/essentials help <" + (num + 1) + "/" + max + ">");
                    sendMessage(commandSender, "§lEssentials was programmed by XNonymous.");
                    break;
                case "reload":
                    Main.getInstance().getConfigRegistry().reload();
                    sendMessage(commandSender, "Reloaded Essentials!");
                    break;
                default:
                    return false;
            }

            return true;
        }

        return false;
    }

    private List<Command> getCommands(int i) {
        List<Command> objects = Main.getInstance().getCommandRegistry().getObjects();
        try {
            return objects.subList(i * 3, i * 3 + 3);
        } catch (Exception e) {
            try {
                return objects.subList(i * 3, i * 3 + 2);
            } catch (Exception e1) {
                try {
                    return objects.subList(i * 3, i * 3 + 1);
                } catch (Exception e3) {
                    return null;
                }
            }
        }
    }
}
