package de.xnonymous.essentials.eco;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.config.impl.EcoConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class EcoUser {

    private final UUID user;
    private double bal;

    public void removeMoney(double money) {
        bal -= money;
        save();
    }

    public void addMoney(double money) {
        bal += money;
        save();
    }

    public void setMoney(double money) {
        bal = money;
        save();
    }

    public void reset() {
        bal = 0;
        save();
    }

    public boolean hasEnough(double d) {
        if (d <= 0)
            return false;
        return bal >= d;
    }

    private void save() {
        EcoConfig ecoConfig = (EcoConfig) Main.getInstance().getConfigRegistry().getByClass(EcoConfig.class);
        ecoConfig.getCfg().set(user.toString(), bal);
        ecoConfig.save();
    }

}
