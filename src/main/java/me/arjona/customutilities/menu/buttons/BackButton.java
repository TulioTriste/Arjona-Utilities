package me.arjona.customutilities.menu.buttons;

import lombok.AllArgsConstructor;
import me.arjona.customutilities.CC;
import me.arjona.customutilities.menu.Menu;
import me.arjona.customutilities.item.ItemBuilder;
import me.arjona.customutilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class BackButton extends Button {

    private final Menu back;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.REDSTONE)
                .name(CC.RED + CC.BOLD + "Atrás")
                .lore(Arrays.asList(
                        CC.RED + "Click here to return",
                        CC.RED + "to the previous menu.")
                ).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        Button.playNeutral(player);
        this.back.openMenu(player);
    }
}