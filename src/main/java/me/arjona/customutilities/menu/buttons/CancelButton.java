package me.arjona.customutilities.menu.buttons;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

/*
 * Project: KLJurassic
 * Created at: 29/10/22 17:39
 * Created by: Dani-error
 */
public class CancelButton extends AirButton {

    @Override
    public boolean shouldCancel(Player player, int slot, ClickType clickType) {
        return true;
    }

}
