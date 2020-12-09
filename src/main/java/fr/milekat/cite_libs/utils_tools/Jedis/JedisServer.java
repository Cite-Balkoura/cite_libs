package fr.milekat.cite_libs.utils_tools.Jedis;

import org.bukkit.Material;

public class JedisServer {
    private final String channel;
    private final String name;
    private final Material material;

    JedisServer(String channel, String name, Material material) {
        this.channel = channel;
        this.name = name;
        this.material = material;
    }

    public String getChannel() {
        return channel;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }
}
