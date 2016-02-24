package net.yeahsaba.tanikyan.EconomyStatistics.Listener;

import net.yeahsaba.tanikyan.EconomyStatistics.EconomyStatistics;

import org.bukkit.event.Listener;

public class EventListener implements Listener{

	public EventListener(EconomyStatistics plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

}
