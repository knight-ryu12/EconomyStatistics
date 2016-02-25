package net.yeahsaba.tanikyan.EconomyStatistics.Listener;

import net.yeahsaba.tanikyan.EconomyStatistics.EconomyStatistics;
import net.yeahsaba.tanikyan.EconomyStatistics.Database.YamlManager;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventListener implements Listener{

	public static FileConfiguration conf = EconomyStatistics.conf;

	public EventListener(EconomyStatistics plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if(b instanceof Sign){
			Sign s = (Sign) b;
			String l1 = s.getLine(0);
			String l2 = s.getLine(1);
			if(p.hasPermission("EconomyStatistics.Sign.place") && l1.contains("-[ES]-") && l2 != null){
				Material m = Material.getMaterial(l2);
				if(m != null){
					s.setLine(0, "§1[EconomyStatistics]");
					s.setLine(1, l2);
					s.setLine(2, YamlManager.getColorMessage(conf, "Settings.Signs.Amount") + ": --");
					s.update(true);
					p.sendMessage(EconomyStatistics.mprefix + YamlManager.getColorMessage(conf, "Settings.Messages.SignPlace"));
				}
			}else {
				p.sendMessage(EconomyStatistics.mprefix + YamlManager.getColorMessage(conf, "Settings.Messages.Error.NoPermission"));
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if(b instanceof Sign){
			Sign s = (Sign) b;
			String l1 = s.getLine(0);
		}
	}
}
