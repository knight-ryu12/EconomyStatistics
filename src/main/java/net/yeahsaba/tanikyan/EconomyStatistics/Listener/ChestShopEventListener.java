package net.yeahsaba.tanikyan.EconomyStatistics.Listener;

import net.yeahsaba.tanikyan.EconomyStatistics.EconomyStatistics;

import org.bukkit.event.Listener;

public class ChestShopEventListener implements Listener{

	public ChestShopEventListener(EconomyStatistics plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

}
