package fr.milekat.cite_libs.core.events_register;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Arrays;

public class RedisMessageReceive extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final String redismsg;

    @Override
    public HandlerList getHandlers() {

        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public RedisMessageReceive(String redismsg){
        this.redismsg = redismsg;
    }

    public String getLabel(){
        String[] label = this.redismsg.split("#:#");
        return label[0];
    }

    public String[] getArgs(){
        String[] split = this.redismsg.split("#:#");
        String[] args = Arrays.copyOfRange(split,1,split.length);
        return args;
    }

    public String[] getFullArgs(){
        String[] args = this.redismsg.split("#:#");
        return args;
    }

    public String getFullMessage(){
        return this.redismsg;
    }
}
