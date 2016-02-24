package net.yeahsaba.tanikyan.EconomyStatistics.Listener;

import net.yeahsaba.tanikyan.EconomyStatistics.EconomyStatistics;

import org.bukkit.event.Listener;

public class PlayerPointsEventListener implements Listener{

	public PlayerPointsEventListener(EconomyStatistics plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

}
