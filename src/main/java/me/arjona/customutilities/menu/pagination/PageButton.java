package me.arjona.customutilities.menu.pagination;

import lombok.AllArgsConstructor;
import me.arjona.customutilities.CC;
import me.arjona.customutilities.item.ItemBuilder;
import me.arjona.customutilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class PageButton extends Button {

    private final int mod;
    private final PaginatedMenu menu;

    @Override
    public ItemStack getButtonItem(Player player) {
        if (this.hasNext(player)) {
            return this.mod > 0 ?
                    new ItemBuilder(Material.REDSTONE_TORCH_ON)
                    .name(CC.GREEN + "Next Page")
                    .lore(Arrays.asList(
                            CC.YELLOW + "Click here to go",
                            CC.YELLOW + "to the next page."
                    )).build() :
                    new ItemBuilder(Material.REDSTONE_TORCH_ON)
                    .name(CC.GREEN + "Previous Page")
                    .lore(Arrays.asList(
                            CC.YELLOW + "Click here to go",
                            CC.YELLOW + "to the previous page."
                    )).build();
        } else {
            return this.mod > 0 ?
                    new ItemBuilder(Material.LEVER)
                    .name(CC.GRAY + "Next Page")
                    .lore(Arrays.asList(
                            CC.YELLOW + "no more pages",
                            CC.YELLOW + "available."
                    )).build() :
                    new ItemBuilder(Material.LEVER)
                    .name(CC.GRAY + "Previous Page")
                    .lore(Arrays.asList(
                            CC.YELLOW + "no more previous",
                            CC.YELLOW + "pages found."
                    )).build();
        }
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (hasNext(player)) {
            this.menu.modPage(player, this.mod);
            Button.playNeutral(player);
        } else {
            Button.playFail(player);
        }
    }

    private boolean hasNext(Player player) {
        int pg = this.menu.getPage() + this.mod;
        return pg > 0 && this.menu.getPages(player) >= pg;
    }
}