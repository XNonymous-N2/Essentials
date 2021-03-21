package de.xnonymous.essentials.commands.impl;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Random;

public class MathGame extends Command {
    public MathGame() {
        super("mathgame", "Manage the chat game \"MathGame\"", true, "mathgame <on/off>", "mg");
    }

    private String last = "";
    private int answer = -1;

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        return false;
    }

    @Override
    public void onLoad() {
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            Random random = new Random();
            int a = random.nextInt(1000);
            int b = random.nextInt(1000);
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            boolean c = random.nextBoolean();
            String op = (c ? "+" : "-");
            try {
                answer = (int) engine.eval(Math.max(a, b) + op + Math.min(a, b));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            String complete = Math.max(a, b) + " " + op + " " + Math.min(a, b);
            last = complete;
            sendMessage(getText("mathgame").replaceAll("%mathgame%", complete));
        }, 20 * 10);
    }

    @Override
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().equalsIgnoreCase(String.valueOf(answer)) && answer != -1) {
            answer = -1;
            event.setCancelled(true);
            sendMessage(getText("mathgame-solved").replaceAll("%player%", event.getPlayer().getName()));
            Main.getInstance().getEcoManager().getEcoUser(event.getPlayer().getUniqueId()).addMoney(100);
            onLoad();
        }
    }

    @Override
    public void onJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            if (answer != -1)
                sendMessage(event.getPlayer(), getText("mathgame-join").replaceAll("%mathgame%", last));
        }, 20 * 3);
    }
}
