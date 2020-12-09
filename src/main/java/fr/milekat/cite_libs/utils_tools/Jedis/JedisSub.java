package fr.milekat.cite_libs.utils_tools.Jedis;

import fr.milekat.cite_libs.MainLibs;
import org.bukkit.Bukkit;
import redis.clients.jedis.JedisPubSub;

public class JedisSub extends JedisPubSub {
    /**
     *      Réception d'un message Redis
     * @param channel channel du message
     * @param message !
     */
    @Override
    public void onMessage(String channel, String message) {
        if (!channel.equalsIgnoreCase(MainLibs.getInstance().getConfig().getString("redis.thischannel"))) {
            if (MainLibs.jedisDebug) {
                Bukkit.getLogger().info("SUB:{" + channel + "},MSG:{" + message + "}");
            }
            Bukkit.getScheduler().runTask(MainLibs.getInstance(), () -> {
                JedisSubEvent jedisSubEvent = new JedisSubEvent(channel, message);
                Bukkit.getPluginManager().callEvent(jedisSubEvent);
            });
        } else {
            if (MainLibs.jedisDebug) {
                Bukkit.getLogger().info("PUB:{" + message + "}");
            }
        }
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        if (MainLibs.jedisDebug) {
            Bukkit.getLogger().info(MainLibs.prefixConsole + "Redis connecté à " + channel);
        }
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        if (MainLibs.jedisDebug) {
            Bukkit.getLogger().warning(MainLibs.prefixConsole + "Redis déconnecté de " + channel);
        }
    }
}