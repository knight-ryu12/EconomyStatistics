package net.yeahsaba.tanikyan.EconomyStatistics.Event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateEconomyStatusEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public UpdateEconomyStatusEvent() {

    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
