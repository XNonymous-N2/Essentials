package de.xnonymous.essentials.eco;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.config.impl.EcoConfig;

import java.util.ArrayList;
import java.util.UUID;

public class EcoManager {

    private final ArrayList<EcoUser> ecoUsers = new ArrayList<>();

    public EcoManager() {
        EcoConfig ecoConfig = (EcoConfig) Main.getInstance().getConfigRegistry().getByClass(EcoConfig.class);
        ecoConfig.getCfg().getKeys(false).forEach(s -> {
            UUID uuid = UUID.fromString(s);
            addEcoUser(new EcoUser(uuid, ecoConfig.getCfg().getDouble(s)));
        });
    }

    private void addEcoUser(EcoUser ecoUser) {
        ecoUsers.add(ecoUser);
    }

    public EcoUser getEcoUser(UUID uuid) {
        return ecoUsers.stream().filter(ecoUser -> ecoUser.getUser().equals(uuid)).findFirst().orElse(null);
    }

    public void tryRegister(UUID uuid) {
        if (getEcoUser(uuid) == null) {
            EcoUser ecoUser = new EcoUser(uuid, 0);
            addEcoUser(ecoUser);
            ecoUser.setMoney(0);
        }
    }

}
