package de.xnonymous.essentials.config;


import de.xnonymous.essentials.config.impl.*;
import de.xnonymous.essentials.structure.NameableRegistry;

public class ConfigRegistry extends NameableRegistry<Config> {

    public ConfigRegistry() {
        register(new TranslateConfig());
        register(new DefaultConfig());
        register(new HomeConfig());
        register(new IPConfig());
        register(new InfoConfig());
        register(new WarpsConfig());
        register(new HologramConfig());
        register(new CookieClickersConfig());
        register(new EcoConfig());



        getObjects().forEach(Config::onStart);
    }

    public void reload() {
        getObjects().forEach(Config::reload);
    }

}
