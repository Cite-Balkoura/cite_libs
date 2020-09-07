package fr.milekat.cite_libs.utils_tools;

import com.comphenix.protocol.utility.StreamSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class ItemSerial {

    /**
     *      Converti un ItemStack en String base64
     * @param itemStack ItemStack à convertir
     * @return String base64
     */
    public static String itemToBase64(ItemStack itemStack) {
        StringBuilder stringBuilder = new StringBuilder();
        if ((itemStack != null) && (itemStack.getType() != Material.AIR)) {
            try
            {
                stringBuilder.append(StreamSerializer.getDefault().serializeItemStack(itemStack));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /**
     *      Restore un String base64 en ItemStack
     * @param data String base64
     * @return ItemStack restoré !
     */
    public static ItemStack itemFromBase64(String data) {
        ItemStack itemStack = new ItemStack(Material.AIR);
        if (!data.equals("")) {
            try
            {
                itemStack = StreamSerializer.getDefault().deserializeItemStack(data);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return itemStack;
    }

    /**
     *      Converti un inventaire complet en String base64
     * @param itemStacks inventaire
     * @return String base64
     */
    public static String invToBase64(ItemStack[] itemStacks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < itemStacks.length; i++)
        {
            if (i > 0) {
                stringBuilder.append(";");
            }
            if ((itemStacks[i] != null) && (itemStacks[i].getType() != Material.AIR)) {
                try
                {
                    stringBuilder.append(StreamSerializer.getDefault().serializeItemStack(itemStacks[i]));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     *      Restore un String base64 en inventaire
     * @param data String base64
     * @return inventaire restoré
     */
    public static ItemStack[] invFromBase64(String data) {
        String[] strings = data.split(";");
        ItemStack[] itemStacks = new ItemStack[strings.length];
        for (int i = 0; i < strings.length; i++) {
            if (!strings[i].equals("")) {
                try
                {
                    itemStacks[i] = StreamSerializer.getDefault().deserializeItemStack(strings[i]);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return itemStacks;
    }
}
