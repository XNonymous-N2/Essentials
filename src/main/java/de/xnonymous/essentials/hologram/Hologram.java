package de.xnonymous.essentials.hologram;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import de.xnonymous.essentials.utils.ColorUtils;

@Getter
public class Hologram {

	private final String header;
	private final Location location;
	private final ArrayList<ArmorStand> holoEntities;
	private final UUID uuid;
	
	public Hologram(String header, Location location, String... lines) {
		holoEntities = new ArrayList<>();
		this.location = location;
		this.header = header;
		setupHologram(header, location);
		uuid = UUID.randomUUID();
		if(lines == null)
			return;
		for(String line : lines)
			addLine(line);
	}
	public Hologram(UUID uuid, String header, Location location, String... lines) {
		this.uuid = uuid;
		holoEntities = new ArrayList<>();
		this.location = location;
		this.header = header;
		setupHologram(header, location);
		if(lines == null)
			return;
		for(String line : lines)
			addLine(line);
	}

	private void setupHologram(String header, Location location) {
		if (location == null)
			return;
		ArmorStand headerStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
		headerStand.setCustomName(ColorUtils.getColoredText(header));
		headerStand.setCustomNameVisible(true);
		headerStand.setGravity(false);
		headerStand.setVisible(false);
		
		holoEntities.add(headerStand);
	}
	
	public void addLine(String line) {
		Location headerLocation = holoEntities.get(0).getLocation().clone();
		ArmorStand lineStand = (ArmorStand) headerLocation.getWorld().spawnEntity(headerLocation.add(0, -0.28 * holoEntities.size(), 0), EntityType.ARMOR_STAND);
		lineStand.setCustomName(ColorUtils.getColoredText(line));
		lineStand.setCustomNameVisible(true);
		lineStand.setGravity(false);
		lineStand.setVisible(false);

		holoEntities.add(lineStand);
	}

	public void setLine(int line, String text) {
		if(line < 1 || line > holoEntities.size())
			return;
		ArmorStand lineStand = holoEntities.get(line - 1);
		lineStand.setCustomName(ColorUtils.getColoredText(text));
		lineStand.setCustomNameVisible(true);
		lineStand.setGravity(false);
		lineStand.setVisible(false);
	}
	
	public void removeLine(int line) {
		if(line < 1 || line > holoEntities.size())
			return;
		ArmorStand lineStand = holoEntities.get(line - 1);
		lineStand.remove();

		holoEntities.remove(lineStand);
		update();
	}
	
	public String getLine(int line) {
		if(line < 1 || line > holoEntities.size())
			return null;
		return holoEntities.get(line - 1).getCustomName();
	}

	public String getStrippedLine(int line) {
		if(line < 1 || line > holoEntities.size())
			return null;
		return ColorUtils.getStrippedText(holoEntities.get(line - 1).getCustomName());
	}
	
	public void move(Location newLocation) {
		holoEntities.get(0).teleport(newLocation);
		update();
	}

	public void update() {
		Location headerLocation = holoEntities.get(0).getLocation().clone();
		for(int x = 1; x < holoEntities.size(); x++) {
			ArmorStand lineStand = holoEntities.get(x);
			lineStand.teleport(headerLocation.add(0, -0.28, 0));
		}
	}
	
	public void delete() {
		for(Entity e : holoEntities)
			e.remove();
	}

}