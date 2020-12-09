package fr.milekat.cite_libs.utils_tools.Jedis;

import fr.milekat.cite_libs.MainLibs;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JedisLoadServers {
    public void loadServers() {
        MainLibs.jedisServers = loadChannels();
        ArrayList<String> servers = new ArrayList<>();
        for (JedisServer server : MainLibs.jedisServers.values()) servers.add(server.getChannel());
        MainLibs.jedis = new Jedis(MainLibs.getInstance().getConfig().getString("redis.host"),
                MainLibs.getInstance().getConfig().getInt("redis.port"), 0);
        MainLibs.jedis.auth(MainLibs.getInstance().getConfig().getString("redis.auth"));
        MainLibs.jedisSub = new JedisSub();
        MainLibs.jedisTask = new BukkitRunnable(){
            @Override
            public void run(){
                try {
                    if (MainLibs.jedisDebug) Bukkit.getLogger().info("Load Jedis channels");
                    MainLibs.jedis.subscribe(MainLibs.jedisSub, servers.toArray(new String[0]));
                } catch (Exception exception) {
                    Bukkit.getLogger().warning("Subscribing failed: " + exception);
                }
            }
        };
    }

    public LinkedHashMap<String, JedisServer> loadChannels() {
        try {
            Connection connection = MainLibs.getSql();
            PreparedStatement q = connection.prepareStatement("SELECT * FROM `balkoura_redis_channels`");
            q.execute();
            LinkedHashMap<String, JedisServer> jedisServers = new LinkedHashMap<>();
            while (q.getResultSet().next()) {
                jedisServers.put(q.getResultSet().getString("channel"),
                        new JedisServer(q.getResultSet().getString("channel"),
                        q.getResultSet().getString("name"),
                        Material.getMaterial(q.getResultSet().getString("display_material"))));
            }
            return jedisServers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
