package fr.milekat.cite_libs.core.commands;

import fr.milekat.cite_libs.MainLibs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class JedisDebug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("jedis")){
            MainLibs.jedisDebug = !MainLibs.jedisDebug;
            MainLibs.getInstance().getConfig().set("redis.debug", MainLibs.jedisDebug);
            sender.sendMessage("Jedis debug: " + MainLibs.jedisDebug);
        }
        return true;
    }
}