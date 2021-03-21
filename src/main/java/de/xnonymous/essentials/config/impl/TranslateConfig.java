package de.xnonymous.essentials.config.impl;


import de.xnonymous.essentials.config.Config;
import de.xnonymous.essentials.utils.ChatUtilsHook;

import java.util.HashMap;

public class TranslateConfig extends Config {

    private final HashMap<String, String> t = new HashMap<>();

    public TranslateConfig() {
        super("translate");
    }

    @Override
    public void onStart() {
        t.put("not-player", "You must be a player to execute this command.");
        t.put("heal-complete", "You have been successfully healed.");
        t.put("syntax-error", "Wrong syntax: /%syntax%");
        t.put("target-not-online", "This player is not online!");
        t.put("healed-complete", "This player has been healed.");
        t.put("change-gamemode", "Your gamemode has been changed.");
        t.put("changed-gamemode", "Player gamemode has been changed.");
        t.put("afk", "You now afk.");
        t.put("un-afk", "You now back.");
        t.put("afk-all", "%player% is now afk.");
        t.put("un-afk-all", "%player% is now back.");
        t.put("no-perm", "Seem like you have not enough perms to execute this command.");
        t.put("flight-on", "Now you can fly like an angle.");
        t.put("flight-off", "Disabled fly.");
        t.put("fly-toggled-on", "Enabled fly for %player%.");
        t.put("fly-toggled-off", "Disabled fly for %player%.");
        t.put("default-kick", "You got kicked.");
        t.put("has-kicked", "%player% has been kicked.");
        t.put("food", "Here is your food!");
        t.put("food-other", "%player% has now full food!");
        t.put("teleport", "You have been teleported to %to%");
        t.put("msg", "&8[&e%player%&7 -> &e%target%&8] &7%message%");
        t.put("speed", "Your speed has been updated! %type% is now %number%");
        t.put("speed-type-walk", "Walk");
        t.put("speed-type-fly", "Fly");
        t.put("god-on", "God mode has been enabled.");
        t.put("god-off", "God mode has been disabled.");
        t.put("god-on-target", "God mode from %player% has been enabled.");
        t.put("god-off-target", "God mode from %player% has been disabled.");
        t.put("never-played", "This player has never played on this server.");
        t.put("after-clear", "Your chat has been cleaned by %player%.");
        t.put("rename-error", "Please hold a item in your main hand.");
        t.put("tpa-already-send", "You already send a tpa request.");
        t.put("tpa-time-expired", "Your tpa request timed out.");
        t.put("tpa-send", "You send a tpa request to %player%.[nl]In 60 seconds the request expires");
        t.put("tpa-send-other", "%player% send you a tpa request.[nl]Accept it with /tpaccept");
        t.put("tpa-accept", "%player% has accepted your tpa request.");
        t.put("tpa-accept-other", "You accepted %player%'s teleport request.");
        t.put("tpa-not", "You have no pending tpa requests.");
        t.put("set-home", "Home created with name %name%.");
        t.put("home-limit", "I'm sorry but you reached your home limit.");
        t.put("no-homes", "Seems like you have no home.");
        t.put("home", "Teleported you to %name%.");
        t.put("home-deleted", "You deleted your home with name %name%.");
        t.put("ban-default", "&e%name%&7 you have been banned.[nl][nl]&eWrong ban?[nl]&7Report it on our teamspeak!");
        t.put("banned", "%target% has been banned from %name%.");
        t.put("ban-pardon", "%target% has been un-banned from %name%.");
        t.put("warp-created", "%warp% has been created.");
        t.put("warp-deleted", "%warp% has been deleted.");
        t.put("warp-teleport", "I teleported you to %warp%.");
        t.put("warp-not-exist", "%warp% does not exist.");
        t.put("warp-title", "&8» &7Warp menu");
        t.put("warp-bevor", "&ePrevious page");
        t.put("warp-next", "&eNext page");
        t.put("warp-close", "&4Close");
        t.put("warp-closelore", "&ePage &8» &c%page%");
        t.put("warp-chest-design", "&8» &c%warp%");
        t.put("vanish-show", "You now shown everyone can see you.");
        t.put("vanish-hide", "You now hidden no one can see you.");
        t.put("clear-inv-complete", "Your inventory has been cleaned.");
        t.put("clear-inv-complete-other", "Inventory from %target% has been cleaned.");
        t.put("powertool-removed", "%item% has no powertool more.");
        t.put("powertool-no", "%item% has no powertool.");
        t.put("powertool-apply", "Bound /%command% to %item%.");
        t.put("smite-air", "Cannot smite AIR.");
        t.put("smite", "Smite!");
        t.put("inventory-full", "Sorry but your inventory is full!");
        t.put("cookieclicker-abort", "Cookieclicker placing has been abort!");
        t.put("cookieclicker-started", "Next gold block you placing is a cookie clicker!");
        t.put("cookieclicker-placed", "Placed a cookie clicker at your position!");
        t.put("balance", "Balance: %money%");
        t.put("balance-other", "Balance from %target%: %money%");
        t.put("pay-not-enough", "Sorry but you dont have enough money.");
        t.put("payment-complete", "&7[&4-&7] %howmuch%[nl]&7New balance: %money%");
        t.put("payment-complete-target", "&7[&a+&7] %howmuch% (from %player%)[nl]&7New balance: %money%");
        t.put("eco-reset", "Reseted cash from %target%");
        t.put("eco-give", "Gived %target% %money%$");
        t.put("eco-remove", "Removed %target% %money%$");
        t.put("eco-set", "Setted %target% %money%$");
        t.put("mathgame", "Solve this task and earn money: %mathgame%");
        t.put("mathgame-solved", "This task has been solved by %player%.");
        t.put("mathgame-join", "Currently theres a mathgame! Solve %mathgame% and earn cash!");

        t.forEach((s, s2) -> {
            getCfg().addDefault(s, s2);
        });
        getCfg().options().header("Essentials by XNonymous\n\nIf you need help please direct message me!\nDiscord: サルバ#2286\n\n");
        getCfg().options().copyHeader(true);
        getCfg().options().copyDefaults(true);
        save();
        getCfg().getKeys(false).forEach(s -> {
            ChatUtilsHook.textList.put(s, getCfg().getString(s));
        });
    }
}
