package fr.milekat.cite_libs;

import fr.milekat.cite_libs.core.commands.JedisDebug;
import fr.milekat.cite_libs.utils_tools.Jedis.JedisLoadServers;
import fr.milekat.cite_libs.utils_tools.Jedis.JedisPub;
import fr.milekat.cite_libs.utils_tools.Jedis.JedisServer;
import fr.milekat.cite_libs.utils_tools.Jedis.JedisSub;
import fr.milekat.cite_libs.utils_tools.MariaManage;
import fr.mrmicky.fastinv.FastInvManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.util.LinkedHashMap;

public class MainLibs extends JavaPlugin {
    /* Main */
    private static MainLibs mainLibs;
    public static String prefixConsole = "[Balkoura] ";
    /* SQL */
    private static MariaManage sql;
    /* Jedis */
    public static boolean jedisDebug;
    public static LinkedHashMap<String, JedisServer> jedisServers;
    public static Jedis jedis;
    public static JedisSub jedisSub;
    public static BukkitRunnable jedisTask;

    @Override
    public void onEnable() {
        mainLibs = this;
        FastInvManager.register(this);
        //  Debug
        jedisDebug = getConfig().getBoolean("redis.debug");
        getCommand("jedis").setExecutor(new JedisDebug());
        // Load SQL
        sql = new MariaManage("jdbc:mysql://",
                getConfig().getString("SQL.host"),
                getConfig().getString("SQL.db-user"),
                getConfig().getString("SQL.user"),
                getConfig().getString("SQL.log"));
        sql.connection();
        // Load Jedis
        new JedisLoadServers().loadServers();
        MainLibs.jedisTask.runTaskAsynchronously(MainLibs.getInstance());
        JedisPub.sendRedis("connect");
    }

    @Override
    public void onDisable() {
        //  Unload Jedis
        jedisTask.cancel();
        jedisSub.unsubscribe();
        //  Unload SQL
        sql.disconnect();
        mainLibs.saveConfig();
    }

    public static MainLibs getInstance(){
        return mainLibs;
    }

    public static Connection getSql() { return sql.getConnection(); }
}
