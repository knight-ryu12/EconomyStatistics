package net.yeahsaba.tanikyan.EconomyStatistics.Listener;

import net.yeahsaba.tanikyan.EconomyStatistics.EconomyStatistics;

import org.bukkit.event.Listener;

public class IconomyEventLisneter implements Listener {

	public IconomyEventLisneter(EconomyStatistics plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
}
