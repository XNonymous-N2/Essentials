package de.xnonymous.essentials.config.impl;

import de.xnonymous.essentials.config.Config;

import java.util.ArrayList;

public class InfoConfig extends Config {
    public InfoConfig() {
        super("infos");
    }

    @Override
    public void onStart() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("This is one of the default information.");
        strings.add("Here you can write this kind of information that you want.");
        strings.add("I hope you enjoying this plugin.");
        getCfg().addDefault("info", strings);
        getCfg().options().copyDefaults(true);
        save();
    }
}
