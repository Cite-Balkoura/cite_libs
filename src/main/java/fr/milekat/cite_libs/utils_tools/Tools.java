package fr.milekat.cite_libs.utils_tools;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;

public class Tools {
    /**
     *      Vérifie si le joueur peut stocker l'item stack
     * @param baseInv inventaire à check
     * @param nbCases nombre de cases (Coffre = 27, joueur = 36)
     * @param items l'item stack à stocker
     * @param nbAdd le nombre de fois qu'il faut ajouter l'item (1 pour 0 ajouts)
     * @return oui/non
     */
    public static boolean canStore(Inventory baseInv, int nbCases, ItemStack items, int nbAdd) {
        Inventory inv = Bukkit.createInventory(null, nbCases, "canStore");
        inv.setContents(baseInv.getStorageContents());
        for (int i=0;i<nbAdd;i++){
            final Map<Integer, ItemStack> map = inv.addItem(items); // Attempt to add in inventory
            if (!map.isEmpty()) { // If not empty, it means the player's inventory is full.
                return false;
            }
        }
        return true;
    }

    /**
     *      Permet de réduire une Strind de 1 caractère
     *      Source : https://www.xenovation.com/blog/development/java/remove-last-character-from-string-java
     * @param str String à réduire
     * @return String réduite
     */
    public static String remLastChar(String str) {
        return Optional.ofNullable(str)
                .filter(sStr -> sStr.length() != 0)
                .map(sStr -> sStr.substring(0, sStr.length() - 1))
                .orElse(str);
    }
}
