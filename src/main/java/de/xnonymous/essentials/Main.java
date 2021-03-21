package de.xnonymous.essentials;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import de.xnonymous.essentials.commands.CommandRegistry;
import de.xnonymous.essentials.config.ConfigRegistry;
import de.xnonymous.essentials.cookie.CookieClickerManager;
import de.xnonymous.essentials.eco.EcoManager;
import de.xnonymous.essentials.hologram.HologramManager;
import de.xnonymous.essentials.listener.*;
import de.xnonymous.essentials.warp.WarpManager;
import fr.minuskube.inv.InventoryManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class Main extends JavaPlugin {


    @Getter
    private static Main instance;

    private CookieClickerManager cookieClickerManager;
    private HologramManager hologramManager;
    private ProtocolManager protocolManager;
    private InventoryManager inventoryManager;
    private EcoManager ecoManager;
    private WarpManager warpManager;

    private CommandRegistry commandRegistry;
    private ConfigRegistry configRegistry;

    private String prefix;

    @Override
    public void onEnable() {
        instance = this;

        this.protocolManager = ProtocolLibrary.getProtocolManager();
        this.commandRegistry = new CommandRegistry();
        this.configRegistry = new ConfigRegistry();
        this.prefix = " §8§l| §7";
        this.warpManager = new WarpManager();
        this.inventoryManager = new InventoryManager(this);
        this.inventoryManager.init();
        this.hologramManager = new HologramManager();
        this.cookieClickerManager = new CookieClickerManager();
        this.ecoManager = new EcoManager();

        registerListener();
    }

    @Override
    public void onDisable() {
        hologramManager.onDisable();
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);
        pluginManager.registerEvents(new PlayerChatListener(), this);
        pluginManager.registerEvents(new EntityDamageListener(), this);
        pluginManager.registerEvents(new PlayerSwapHandItemsListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new InventoryDragListener(), this);
        pluginManager.registerEvents(new InventoryCloseListener(), this);
        pluginManager.registerEvents(new PlayerLoginListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new PlayerBlockPlaceListener(), this);
        pluginManager.registerEvents(new PlayerBlockBreakListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
    }

}
