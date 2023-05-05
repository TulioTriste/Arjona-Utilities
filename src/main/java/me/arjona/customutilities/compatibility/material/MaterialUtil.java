package me.arjona.customutilities.compatibility.material;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;

@UtilityClass
public class MaterialUtil {

    public Material getMaterial(String material) {
        if(material.contains(":"))
            material = material.split(":")[0];

        try {
            return Material.getMaterial(Integer.parseInt(material));
        }
        catch (IllegalArgumentException exception) {
            return Material.getMaterial(material);
        }
    }


}
