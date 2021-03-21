package de.xnonymous.essentials.inventorys;

import com.cryptomorin.xseries.XMaterial;
import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.utils.ItemBuilder;
import de.xnonymous.essentials.utils.ListHandler;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotPos;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;

public class CookieClickerInventory implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder()
            .id("cookie-clicker")
            .provider(new CookieClickerInventory())
            .manager(Main.getInstance().getInventoryManager())
            .size(4, 9)
            .title("§e§lCookie Clicker Shop")
            .build();

    @Override
    public void init(Player player, InventoryContents inventoryContents) {
        Material pane = XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial();
        inventoryContents.fill(ClickableItem.empty(ItemBuilder.builder()
                .material(pane)
                .color(15)
                .displayName("§7")
                .build().toItemStack()));
        inventoryContents.set(2, 4, ClickableItem.empty(ItemBuilder.builder()
                .material(Material.ANVIL)
                .displayName("§eBuy cash")
                .lores(ListHandler.getList("§7", "§7Converts cookies into money"))
                .build().toItemStack()));
        inventoryContents.set(0, 4, ClickableItem.empty(ItemBuilder.builder()
                .material(Material.PAPER)
                .displayName("§e§lYour cookies")
                .lores(ListHandler.getList("§7", "§7Cookies: §e"))
                .build().toItemStack()));
        inventoryContents.set(2, 7, ClickableItem.empty(ItemBuilder.builder()
                .material(Material.BEACON)
                .displayName("§eCookie booster")
                .lores(ListHandler.getList("§7", "§7Buy a booster which gives", "§7you more cookies per click"))
                .build().toItemStack()));
        inventoryContents.set(2, 1, ClickableItem.empty(ItemBuilder.builder()
                .material(Material.PAPER)
                .displayName("§eClaim your offline cookies")
                .lores(ListHandler.getList("§7", "§7Mined cookies while you offline: §e"))
                .build().toItemStack()));
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {
        Material pane = XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial();
        int state = inventoryContents.property("state", 0);
        inventoryContents.setProperty("state", state + 1);

        if (state % 10 == 0) {
            String lastCookie = inventoryContents.property("lastCookie", "0|0");
            String[] split = lastCookie.split("\\|");
            inventoryContents.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]), ClickableItem.empty(ItemBuilder.builder()
                    .material(pane)
                    .color(15)
                    .displayName("§7")
                    .build().toItemStack()));
        }

        if (state % 20 != 0)
            return;
        Random random = new Random();
        int a = random.nextInt(4);
        int b = random.nextInt(9);
        Optional<ClickableItem> clickableItem = inventoryContents.get(a, b);
        ClickableItem clickableItem1 = clickableItem.get();
        if (clickableItem1.getItem().getType().equals(pane)) {
            inventoryContents.set(a, b, ClickableItem.empty(ItemBuilder.builder()
                    .material(Material.COOKIE)
                    .displayName("§eYummy")
                    .build().toItemStack()));
            inventoryContents.setProperty("lastCookie", a + "|" + b);
        }
    }
}
