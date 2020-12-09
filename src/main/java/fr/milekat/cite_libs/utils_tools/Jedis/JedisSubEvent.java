package fr.milekat.cite_libs.utils_tools.Jedis;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Arrays;

public class JedisSubEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final String channel;
    private final String redismsg;

    @Override
    public HandlerList getHandlers() {

        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public JedisSubEvent(String channel, String redismsg){
        this.channel = channel;
        this.redismsg = redismsg;
    }

    public String getChannel(){
        return this.channel;
    }

    public String getLabel(){
        String[] label = this.redismsg.split("#:#");
        return label[0];
    }

    public String[] getArgs(){
        String[] split = this.redismsg.split("#:#");
        return Arrays.copyOfRange(split,1,split.length);
    }

    public String[] getFullArgs(){
        return this.redismsg.split("#:#");
    }

    public String getFullMessage(){
        return this.redismsg;
    }
}
