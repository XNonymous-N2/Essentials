package de.xnonymous.essentials.inventorys;

import com.cryptomorin.xseries.XMaterial;
import de.xnonymous.essentials.Main;
import de.xnonymous.essentials.utils.ItemBuilder;
import de.xnonymous.essentials.utils.ListHandler;
import de.xnonymous.essentials.warp.Warp;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

@AllArgsConstructor
public class WarpMenu implements InventoryProvider {

    public static SmartInventory getInventory(String title, String bevor, String next, String close, String closeLore, String design) {
        return SmartInventory.builder()
                .provider(new WarpMenu(title, bevor, next, close, closeLore, design))
                .size(6, 9)
                .manager(Main.getInstance().getInventoryManager())
                .title(title)
                .id("warpmenu")
                .closeable(true)
                .build();
    }

    private final String title;
    private final String before;
    private final String next;
    private final String close;
    private final String closeLore;
    private final String design;

    @Override
    public void init(Player player, InventoryContents inventoryContents) {
        Material red = XMaterial.RED_STAINED_GLASS_PANE.parseMaterial();
        Material black = XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial();
        Pagination pagination = inventoryContents.pagination();

        ArrayList<Warp> warps = Main.getInstance().getWarpManager().getWarps();


        ClickableItem[] items = new ClickableItem[warps.size()];

        for (int i = 0; i < items.length; i++) {
            int finalI = i;
            items[i] = ClickableItem.of(ItemBuilder.builder().material(Material.PAPER).displayName(design.replaceAll("%warp%", warps.get(i).getName())).build().toItemStack(), event -> {
                event.getWhoClicked().teleport(warps.get(finalI).getLocation());
            });
        }

        pagination.setItems(items);
        pagination.setItemsPerPage(4 * 9);

        pagination.addToIterator(inventoryContents.newIterator(SlotIterator.Type.HORIZONTAL, 1, 0).allowOverride(false));

        while (inventoryContents.firstEmpty().isPresent()) {
            inventoryContents.set(inventoryContents.firstEmpty().get(), ClickableItem.empty(ItemBuilder.builder().material(black)
                    .color(14).build().toItemStack()));
        }

        inventoryContents.fillRow(0, ClickableItem.empty(ItemBuilder.builder().material(red)
                .color(15).build().toItemStack()));
        inventoryContents.fillRow(5, ClickableItem.empty(ItemBuilder.builder().material(red)
                .color(15).build().toItemStack()));

        inventoryContents.set(5, 3, ClickableItem.of(ItemBuilder.builder()
                        .material(Material.ARROW)
                        .displayName(before).build().toItemStack(),
                e -> getInventory(title, before, next, close, closeLore, design).open(player, pagination.previous().getPage())));
        inventoryContents.set(5, 5, ClickableItem.of(ItemBuilder.builder()
                        .material(Material.ARROW)
                        .displayName(next).build().toItemStack(),
                e -> getInventory(title, before, next, close, closeLore, design).open(player, pagination.next().getPage())));
        inventoryContents.set(5, 4, ClickableItem.of(ItemBuilder.builder()
                        .material(Material.PAPER)
                        .displayName(close)
                        .lores(ListHandler.getList("§7", closeLore.replaceAll("%page%", String.valueOf((pagination.getPage() + 1))))).build().toItemStack(),
                e -> e.getWhoClicked().closeInventory()));
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }
}