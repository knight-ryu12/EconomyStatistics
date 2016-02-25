package net.yeahsaba.tanikyan.EconomyStatistics.Listener;

import net.yeahsaba.tanikyan.EconomyStatistics.EconomyStatistics;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.event.PlayerPointsChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class PlayerPointsEventListener implements Listener{

	private static PlayerPoints playerPoints;

	public static boolean hookPlayerPoints() {
	    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints");
	    playerPoints = PlayerPoints.class.cast(plugin);
	    return playerPoints != null;
	}

	//これより下イベント処理
	public PlayerPointsEventListener(EconomyStatistics plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerPointsChange(PlayerPointsChangeEvent e){

	}

}
