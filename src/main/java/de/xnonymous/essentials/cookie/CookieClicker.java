package de.xnonymous.essentials.cookie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CookieClicker {

    private UUID uuid;
    private Location location;

}
