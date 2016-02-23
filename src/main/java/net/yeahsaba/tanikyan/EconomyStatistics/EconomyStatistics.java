package net.yeahsaba.tanikyan.EconomyStatistics;

import java.io.File;
import java.util.logging.Logger;

import net.yeahsaba.tanikyan.EconomyStatistics.Database.MySQL;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EconomyStatistics extends JavaPlugin {
	public static File config = new File("plugins/EconomyStatistics", "config.yml");
	public static FileConfiguration conf = YamlConfiguration.loadConfiguration(config);

	public static EconomyStatistics EconomyStatistics;
	public static Plugin plugin;

	public static PluginManager manager = Bukkit.getServer().getPluginManager();
	public static Logger logger = Bukkit.getServer().getLogger();

	public static String mysql_url, mysql_database, mysql_user, mysql_pass;
	public static boolean use_mysql = false;

	public static boolean enable_vault = false;
	public static boolean enable_iconomy = false;
	public static boolean enable_craftconomy = false;
	public static boolean enable_playerpoints = false;

	public static String logprefix = "[ES] ";

	public void onEnable(){
		//ファイル関連の処理
		saveDefaultConfig();
		//saveStatusFile
		plugin = this;
		boolean enable_cc = false;
		logger.info(logprefix + "Enabling Plugin...");
		logger.info(logprefix + "EconomyStatistics Version: " + this.getDescription().getVersion());
		if(manager.isPluginEnabled("ChestShop")) enable_cc = true; else logger.warning(logprefix + "ChestShop not Found! Disabling Plugin...");
		if(!enable_cc) manager.disablePlugin(plugin);
		logger.info(logprefix + "Found ChestShop!");
		logger.info("Version: " + manager.getPlugin("ChestShop").getDescription().getVersion());
		if(manager.isPluginEnabled("Vault")) enable_vault = true; else logger.warning(logprefix + "Vault not Found! Disabling Plugin...");
		if(!enable_vault) manager.disablePlugin(plugin);
		if(manager.isPluginEnabled("iConomy")) enable_iconomy = true;
		if(manager.isPluginEnabled("CraftConomy")) enable_craftconomy = true;
		if(manager.isPluginEnabled("PlayerPoints")) enable_playerpoints = true;
		logger.info(logprefix + "Found Vault!");
		logger.info("Version: " + manager.getPlugin("Vault").getDescription().getVersion());
		if(enable_iconomy){
			logger.info(logprefix + "Found iConomy!");
			logger.info(logprefix + "Version: " + manager.getPlugin("iConomy").getDescription().getVersion());
		}
		if(enable_craftconomy){
			logger.info(logprefix + "Found CraftConomy!");
			logger.info(logprefix + "Version: " + manager.getPlugin("CraftConomy").getDescription().getVersion());
		}
		if(enable_playerpoints){
			logger.info(logprefix + "Found PlayerPoints!");
			logger.info(logprefix + "Version: " + manager.getPlugin("PlayerPoints").getDescription().getVersion());
		}
		//MySQL 接続
		use_mysql = conf.getBoolean("Settings.Database.MySQL.use");
		if(use_mysql) MySQL.connect(mysql_url, mysql_database, mysql_user, mysql_pass);
		logger.info(logprefix + "Plugin Enabled!");
	}

	public void onDisable(){
		//ファイル関連の処理
		//SaveStatusFile
		//MySQL クローズ
		if(use_mysql) MySQL.close();
		logger.info(logprefix + "Disabling Plugin...");
	}
}
