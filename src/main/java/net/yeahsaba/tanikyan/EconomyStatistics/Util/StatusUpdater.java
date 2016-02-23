package net.yeahsaba.tanikyan.EconomyStatistics.Util;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class StatusUpdater extends JavaPlugin {

	public static void UpdateEconomyStatus(String plugin){

	}

	public static void AutomaticUpdate(Plugin plugin, int i){
		new BukkitRunnable(){
			@Override
			public void run() {
				long start = 0,end = 0;
				start = System.currentTimeMillis();

				end = System.currentTimeMillis();
			}
		}.runTaskTimer(plugin,  i * 20, i * 20);
	}
}
