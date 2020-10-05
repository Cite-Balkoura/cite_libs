package fr.milekat.cite_libs.utils_tools;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Easily create itemstacks, without messing your hands. <i>Note that if you do
 * use this in one of your projects, leave this notice.</i> <i>Please do credit
 * me if you do use this in one of your projects.</i>
 * @author NonameSL
 */
public class ItemParser {
    /**
     *      Mini-lib pour définir les items plus simplement !
     */
    public static ItemStack setItem(String material, String name,String enchant, String lore, int amount){
        ItemStack item = new ItemStack(Material.valueOf(material),amount);
        ItemMeta data = item.getItemMeta();
        if (data==null) return item;
        if (name!=null){
            data.setDisplayName(name);
        }
        if (enchant!=null){
            String[] enchants = enchant.split(",");
            for (int i=1;i<=enchants.length;i++){
                String[] ench = enchants[i-1].split(":");
                data.addEnchant(Objects.requireNonNull(Enchantment.getByKey(NamespacedKey.minecraft(ench[0].toLowerCase()))),
                        Integer.parseInt(ench[1]),true);
            }
        }
        if (lore!=null){
            List<String> lores = Arrays.asList(lore.split("%nl%"));
            data.setLore(lores);
        }
        if (name!=null && name.contains("Hammer")) data.setCustomModelData(1);
        item.setItemMeta(data);
        return item;
    }
}
