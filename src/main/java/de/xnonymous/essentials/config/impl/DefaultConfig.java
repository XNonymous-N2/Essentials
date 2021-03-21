package de.xnonymous.essentials.config.impl;

import de.xnonymous.essentials.config.Config;

public class DefaultConfig extends Config {
    public DefaultConfig() {
        super("config");
    }

    private String time = null;

    @Override
    public void onStart() {
        getCfg().addDefault("date-format", "dd.MM.yyyy HH:mm:ss");
        getCfg().addDefault("sign-format", "&7[nl]&7Signed at &e%date%[nl]&7from &e%player%");
        getCfg().options().header("Essentials by XNonymous\n\nIf you need help please direct message me!\nDiscord: サルバ#2286\n\n[nl] for a new line\n\n");
        getCfg().options().copyHeader(true);
        getCfg().options().copyDefaults(true);
        save();
    }

    public String getTime() {
        return time == null ? time = getCfg().getString("date-format") : time;
    }
}
