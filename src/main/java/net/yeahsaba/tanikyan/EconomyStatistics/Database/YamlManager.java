package net.yeahsaba.tanikyan.EconomyStatistics.Database;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class YamlManager extends JavaPlugin {

	public static String getColorMessage(FileConfiguration f, String pass){
		String msg = ChatColor.translateAlternateColorCodes('&', f.getString(pass));
		return msg;
	}
}
