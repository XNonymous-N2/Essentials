package de.xnonymous.essentials.cookie;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.config.impl.CookieClickersConfig;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class CookieClickerManager {

    private ArrayList<CookieClicker> cookieClickers = new ArrayList<>();

    public CookieClickerManager() {
        CookieClickersConfig config = (CookieClickersConfig) Main.getInstance().getConfigRegistry().getByClass(CookieClickersConfig.class);
        config.getCfg().getKeys(false).forEach(s -> {
            if (config.getCfg().get(s) instanceof Location) {
                Location location = (Location) config.getCfg().get(s);
                cookieClickers.add(new CookieClicker(UUID.fromString(s), location));
                location.getBlock().setType(Material.GOLD_BLOCK);
            }
        });
    }

    public boolean isLocation(Location location) {
        return cookieClickers.stream().anyMatch(cookieClicker -> cookieClicker.getLocation().equals(location));
    }

    public CookieClicker getCookieClicker(Location location) {
        return cookieClickers.stream().filter(cookieClicker -> cookieClicker.getLocation().getBlockX() == location.getBlockX()
                && cookieClicker.getLocation().getBlockY() == location.getBlockY()
                && cookieClicker.getLocation().getBlockZ() == location.getBlockZ()).findFirst().orElse(null);
    }

    public void addCookieClicker(CookieClicker cookieClicker) {
        cookieClickers.add(cookieClicker);
    }

    public void removeCookieClicker(CookieClicker cookieClicker) {
        cookieClickers.remove(cookieClicker);
    }

}
