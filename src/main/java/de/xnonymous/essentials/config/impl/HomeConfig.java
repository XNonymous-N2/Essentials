package de.xnonymous.essentials.config.impl;

import de.xnonymous.essentials.config.Config;

public class HomeConfig extends Config {
    public HomeConfig() {
        super("home");
    }

    @Override
    public void onStart() {
        getCfg().addDefault("home-limit", 3);
        getCfg().options().copyDefaults(true);
        save();
    }
}
