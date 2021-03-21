package de.xnonymous.essentials.warp;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.config.impl.WarpsConfig;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.ArrayList;

@Getter
@Setter
public class WarpManager {

    private final ArrayList<Warp> warps = new ArrayList<>();

    public WarpManager() {
        WarpsConfig warpsConfig = (WarpsConfig) Main.getInstance().getConfigRegistry().getByClass(WarpsConfig.class);
        warpsConfig.getCfg().getKeys(false).forEach(s -> warps.add(new Warp(s, (Location) warpsConfig.getCfg().get(s))));
    }

}
