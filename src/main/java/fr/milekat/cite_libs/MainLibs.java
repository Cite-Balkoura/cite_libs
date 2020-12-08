package fr.milekat.cite_libs;

import fr.milekat.cite_libs.core.commands.JedisDebug;
import fr.milekat.cite_libs.utils_tools.Jedis.JedisSub;
import fr.milekat.cite_libs.utils_tools.MariaManage;
import fr.mrmicky.fastinv.FastInvManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import redis.clients.jedis.Jedis;

import java.util.Objects;

public class MainLibs extends JavaPlugin {
    private static MainLibs mainLibs;
    public static String prefixConsole = "[Balkoura] ";
    public static boolean jedisDebug;
    public static Jedis jedis;
    public static JedisSub jedisSub;
    public static MariaManage sql;
    private BukkitRunnable jedisTask;

    @Override
    public void onEnable() {
        mainLibs = this;
        FastInvManager.register(this);
        jedisDebug = this.getConfig().getBoolean("redis.debug");
        this.saveDefaultConfig();
        this.getConfig();
        // SQL
        sql = new MariaManage("jdbc:mysql://",
                this.getConfig().getString("SQL.host"),
                this.getConfig().getString("SQL.db-user"),
                this.getConfig().getString("SQL.user"),
                this.getConfig().getString("SQL.log"));
        sql.connection();
        // Jedis
        jedis = new Jedis(this.getConfig().getString("redis.host"),
                Integer.parseInt(Objects.requireNonNull(this.getConfig().getString("redis.port"))),
                0);
        jedis.auth(this.getConfig().getString("redis.auth"));
        jedisSub = new JedisSub();
        jedisTask = new BukkitRunnable(){
            @Override
            public void run(){
                try {
                    jedis.subscribe(jedisSub, "discord", "cite", "event", "survie", "bungee");
                } catch (Exception e) {
                    Bukkit.getLogger().warning("Subscribing failed : " + e);
                }
            }
        };
        jedisTask.runTaskAsynchronously(this);
        getCommand("jedis").setExecutor(new JedisDebug());
    }

    @Override
    public void onDisable() {
        jedisTask.cancel();
        jedisSub.unsubscribe();
        sql.disconnect();
        mainLibs.saveConfig();
    }

    public static MainLibs getInstance(){
        return mainLibs;
    }
}
