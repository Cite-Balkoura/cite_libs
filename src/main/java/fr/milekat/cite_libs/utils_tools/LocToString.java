package fr.milekat.cite_libs.utils_tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocToString {
    /**
     * Converts a location to a simple string representation
     * If location is null, returns empty string
     * @param l
     * @return
     */
    public static String getStringLocation(final Location l) {
        if (l == null) {
            return "";
        }
        return l.getWorld().getName() + ":" + l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ();
    }

    /**
     * Converts a serialized location to a Location. Returns null if string is empty
     * @param s - serialized location in format "world:x:y:z"
     * @return Location
     */
    public static Location getLocationString(final String s) {
        if (s == null || s.trim() == "") {
            return null;
        }
        final String[] parts = s.split(":");
        if (parts.length == 4) {
            final World w = Bukkit.getServer().getWorld(parts[0]);
            final int x = Integer.parseInt(parts[1]);
            final int y = Integer.parseInt(parts[2]);
            final int z = Integer.parseInt(parts[3]);
            return new Location(w, x, y, z);
        }
        return null;
    }
}
