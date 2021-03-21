package de.xnonymous.essentials.commands;

import de.xnonymous.essentials.commands.impl.*;
import de.xnonymous.essentials.structure.NameableRegistry;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CommandRegistry extends NameableRegistry<Command> {

    public CommandRegistry() {
        registerAll();
    }

    private void registerAll() {
        register(new HealCommand());
        register(new GamemodeCommand());
        register(new AfkCommand());
        register(new BroadcastCommand());
        register(new EssentialsCommand());
        register(new DamageCommand());
        register(new FlyCommand());
        register(new KickCommand());
        register(new FeedCommand());
        register(new TeleportCommand());
        register(new MSGCommand());
        register(new SpeedCommand());
        register(new GodCommand());
        register(new KillCommand());
        register(new SeenCommand());
        register(new ClearChatCommand());
        register(new RenameCommand());
        register(new CraftCommand());
        register(new AnvilCommand());
        register(new EnderchestCommand());
        register(new SignCommand());
        register(new TpaCommand());
        register(new TpacceptCommand());
        register(new SetHomeCommand());
        register(new HomeCommand());
        register(new DelHomeCommand());
        register(new InvseeCommand());
        register(new BanCommand());
        register(new UnbanCommand());
        register(new CheckIPCommand());
        register(new InfoCommand());
        register(new SetWarpCommand());
        register(new DelWarpCommand());
        register(new WarpCommand());
        register(new VanishCommand());
        register(new ClearInventoryCommand());
        register(new PowerToolCommand());
        register(new SmiteCommand());
        register(new SkullCommand());
        register(new CookieClickerCommand());
        register(new MoneyCommand());
        register(new PayCommand());
        register(new EcoCommand());
        register(new MathGame());


        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            getObjects().forEach(command -> {
                BukkitCommand bukkitCommand = new BukkitCommand(command.getName()) {
                    @Override
                    public boolean execute(CommandSender commandSender, String s, String[] strings) {

                        if (!commandSender.hasPermission("essentials.command." + command.getName().toLowerCase())) {
                            sendMessage(commandSender, getText("no-perm"));
                            return false;
                        }

                        if (!(commandSender instanceof Player) && !command.isConsole()) {
                            sendMessage(commandSender, getText("not-player"));
                            return false;
                        }

                        if (!command.onExecute(commandSender, strings)) {
                            sendMessage(commandSender, getText("syntax-error").replaceAll("%syntax%", command.getSyntax()));
                            return false;
                        }

                        return true;
                    }
                };
                bukkitCommand.setDescription(command.getDescription());
                bukkitCommand.setUsage(command.getSyntax());
                bukkitCommand.setPermission("essentials.command." + command.getName().toLowerCase());
                bukkitCommand.setAliases(Arrays.asList(command.getAlias()));

                commandMap.register("Essentials", bukkitCommand);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        getObjects().forEach(Command::onLoad);
    }

}
