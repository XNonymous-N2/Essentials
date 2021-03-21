package de.xnonymous.essentials.hologram;

import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.config.Config;
import de.xnonymous.essentials.config.impl.HologramConfig;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class HologramManager {

    private final ArrayList<Hologram> holograms = new ArrayList<>();
    private final HologramConfig hologramConfig;

    public HologramManager() {
        hologramConfig = (HologramConfig) Main.getInstance().getConfigRegistry().getByClass(HologramConfig.class);

        hologramConfig.getCfg().getKeys(false).forEach(s -> {
            UUID uuid = UUID.fromString(s);
            String header = hologramConfig.getCfg().getString(s + ".header");
            List<String> lines = hologramConfig.getCfg().getStringList(s + ".lines");
            Location location = ((Location) hologramConfig.getCfg().get(s + ".loc"));
            addHologram(new Hologram(uuid, header, location, lines.toArray(new String[0])));
        });
    }

    public void addHologramAndConfig(Hologram hologram) {
        FileConfiguration cfg = hologramConfig.getCfg();
        String uuid = hologram.getUuid().toString();
        ArrayList<String> lines = new ArrayList<>();
        int i = 0;
        while (hologram.getLine(i) != null) {
            lines.add(hologram.getLine(i));
            i++;
        }
        cfg.set(uuid + ".header", hologram.getHeader());
        cfg.set(uuid + ".lines", lines);
        cfg.set(uuid + ".loc", hologram.getLocation());
        hologramConfig.save();
        addHologram(hologram);
    }

    public void removeHologram(UUID uuid) {
        HologramConfig config1 = (HologramConfig) Main.getInstance().getConfigRegistry().getByClass(HologramConfig.class);
        config1.getCfg().set(uuid.toString() + ".header", null);
        config1.getCfg().set(uuid.toString() + ".lines", null);
        config1.getCfg().set(uuid.toString() + ".loc", null);
        config1.save();
        System.out.println(uuid.toString());
        holograms.stream().filter(hologram -> hologram.getUuid().equals(uuid)).findFirst().ifPresent(Hologram::delete);
        holograms.removeIf(hologram -> hologram.getUuid().equals(uuid));
    }

    private void addHologram(Hologram hologram) {
        holograms.add(hologram);
    }

    public void onDisable() {
        holograms.forEach(Hologram::delete);
    }

}
