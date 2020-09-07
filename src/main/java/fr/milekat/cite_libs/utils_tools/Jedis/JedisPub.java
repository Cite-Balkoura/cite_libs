package fr.milekat.cite_libs.utils_tools.Jedis;

import fr.milekat.cite_libs.MainLibs;
import org.bukkit.Bukkit;
import redis.clients.jedis.Jedis;

import java.util.Objects;


public class JedisPub {

    /**
     *      Envoi d'un message sur le Redis
     * @param msg message à envoyer
     */
    public static void sendRedis(String msg){
        Bukkit.getScheduler().runTaskAsynchronously(MainLibs.getInstance(), () -> {
            try {
                Jedis jedis = new Jedis(MainLibs.getInstance().getConfig().getString("redis.host"),
                        Integer.parseInt(Objects.requireNonNull(MainLibs.getInstance().getConfig().getString("redis.port"))),
                        0);
                jedis.auth(MainLibs.getInstance().getConfig().getString("redis.auth"));
                jedis.publish(MainLibs.getInstance().getConfig().getString("redis.thischannel"),msg);
                jedis.quit();
            } catch (Exception ex) {
                Bukkit.getLogger().warning("Exception : " + ex.getMessage());
            }
        });
    }
}
