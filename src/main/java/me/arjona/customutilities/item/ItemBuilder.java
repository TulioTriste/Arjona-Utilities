package me.arjona.customutilities.item;

import me.arjona.customutilities.BukkitUtil;
import me.arjona.customutilities.CC;
import me.arjona.customutilities.compatibility.material.CompatibleMaterial;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material, 1);
    }

    public ItemBuilder(String material) {
        this.itemStack = new ItemStack(Material.matchMaterial(material), 1);
    }

    public ItemBuilder(Material material, int data) {
        this.itemStack = new ItemStack(material, 1, (short) data);
    }

    public ItemBuilder(Material material, int amount, int data) {
        this.itemStack = new ItemStack(material, amount, (short) data);
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack.clone();
    }

    public ItemBuilder name(String name) {
        if (name != null) {
            ItemMeta meta = itemStack.getItemMeta();

            if (meta != null) {
                meta.setDisplayName(CC.translate(name));
                itemStack.setItemMeta(meta);
            }
        }
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        if (lore != null) {
            ItemMeta meta = itemStack.getItemMeta();

            if (meta != null) {
                meta.setLore(CC.translate(lore));
                itemStack.setItemMeta(meta);
            }
        }
        return this;
    }

    public ItemBuilder lore(String... lore) {
        if (lore != null) {
            ItemMeta meta = itemStack.getItemMeta();

            if (meta != null) {
                meta.setLore(CC.translate(Arrays.asList(lore)));
                itemStack.setItemMeta(meta);
            }
        }
        return this;
    }

    public ItemBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder glow(boolean glow) {
        if (glow) {
            ItemMeta meta = itemStack.getItemMeta();

            if (meta != null) {
                if (BukkitUtil.SERVER_VERSION_INT >= 8) {
                    meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
                else {
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }

                itemStack.setItemMeta(meta);
            }
        }

        return this;
    }

    public ItemBuilder enchant(boolean enchanted) {
        if (enchanted) {
            ItemMeta meta = itemStack.getItemMeta();

            if (meta != null) {
                meta.addEnchant(Enchantment.DURABILITY, 1, true);
                itemStack.setItemMeta(meta);
            }
        }
        return this;
    }

    public ItemBuilder enchant(boolean enchanted, int level) {
        if (enchanted) {
            ItemMeta meta = itemStack.getItemMeta();

            if (meta != null) {
                meta.addEnchant(Enchantment.DURABILITY, level, true);
                itemStack.setItemMeta(meta);
            }
        }
        return this;
    }

    public ItemBuilder enchant(boolean enchanted, Enchantment enchant, int level) {
        if (enchanted) {
            ItemMeta meta = itemStack.getItemMeta();

            if (meta != null) {
                meta.addEnchant(enchant, level, true);
                itemStack.setItemMeta(meta);
            }
        }
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        if (unbreakable) {
            ItemMeta meta = itemStack.getItemMeta();

            if (meta != null) {
                try {
                    meta.spigot().setUnbreakable(true);
                    itemStack.setItemMeta(meta);
                }
                catch (NoSuchMethodError ex) {
                    enchant(true, Enchantment.DURABILITY, 100);
                }
            }
        }
        return this;
    }

    public ItemBuilder data(int dur) {
        this.itemStack.setDurability((short) dur);
        return this;
    }

    public ItemBuilder owner(String owner) {
        if (itemStack.getType() == CompatibleMaterial.HUMAN_SKULL.getMaterial()) {
            SkullMeta meta = (SkullMeta) itemStack.getItemMeta();

            if (meta != null) {
                meta.setOwner(owner);
                itemStack.setItemMeta(meta);
            }

            return this;
        }

        throw new IllegalArgumentException("setOwner() only applicable for Skull Item");
    }

    public ItemBuilder owner(boolean enabled, String owner) {
        if (enabled) {
            return owner(owner);
        }

        return this;
    }

    public ItemBuilder armorColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();

        if (leatherArmorMeta != null) {
            leatherArmorMeta.setColor(color);
            itemStack.setItemMeta(leatherArmorMeta);
            return this;
        }

        throw new IllegalArgumentException("setColor() only applicable for LeatherArmor");
    }

    public static ItemStack createSkull(String owner, String displayName, List<String> lore) {
        return new ItemBuilder(CompatibleMaterial.HUMAN_SKULL.getMaterial())
                .data(3)
                .owner(owner)
                .name(displayName)
                .lore(lore)
                .build();
    }

    public static String getDisplayName(ItemStack itemStack) {
        return itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : CC.toReadable(itemStack.getType());
    }

    public static String getOwner(ItemStack itemStack) {
        if (itemStack.getType() == Material.SKULL_ITEM && itemStack.getDurability() == 3 && itemStack.hasItemMeta()) {
            try {
                return ((SkullMeta) itemStack.getItemMeta()).getOwner();
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    public ItemStack build() {
        return itemStack;
    }
}