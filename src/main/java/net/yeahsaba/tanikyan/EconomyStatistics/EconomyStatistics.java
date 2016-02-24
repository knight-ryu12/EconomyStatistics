package net.yeahsaba.tanikyan.EconomyStatistics;

import java.io.File;
import java.util.logging.Logger;

import net.yeahsaba.tanikyan.EconomyStatistics.Database.MySQL;
import net.yeahsaba.tanikyan.EconomyStatistics.Listener.ChestShopEventListener;
import net.yeahsaba.tanikyan.EconomyStatistics.Listener.CraftConomyEventListener;
import net.yeahsaba.tanikyan.EconomyStatistics.Listener.EventListener;
import net.yeahsaba.tanikyan.EconomyStatistics.Listener.IconomyEventLisneter;
import net.yeahsaba.tanikyan.EconomyStatistics.Listener.PlayerPointsEventListener;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EconomyStatistics extends JavaPlugin {
	//config
	public static File config = new File("plugins/EconomyStatistics", "config.yml");
	public static FileConfiguration conf = YamlConfiguration.loadConfiguration(config);
	//ステータス関連設定ファイル
	public static File statussettingfile = new File("plugins/EconomyStatistics", "status.yml");
	public static FileConfiguration ssf = YamlConfiguration.loadConfiguration(statussettingfile);
	//ステータス保存用ファイル
	public static File statusfile_iconomy;
	public static FileConfiguration sf_iconomy;
	public static File statusfile_craftconomy;
	public static FileConfiguration sf_craftconomy;
	public static File statusfile_playerpoints;
	public static FileConfiguration sf_playerpoints;
	public static File statusfile;
	public static FileConfiguration sf;

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
		if(manager.isPluginEnabled("Craftconomy3")) enable_craftconomy = true;
		if(manager.isPluginEnabled("PlayerPoints")) enable_playerpoints = true;
		logger.info(logprefix + "Found Vault!");
		logger.info("Version: " + manager.getPlugin("Vault").getDescription().getVersion());
		if(enable_iconomy){
			logger.info(logprefix + "Found iConomy!");
			logger.info(logprefix + "Version: " + manager.getPlugin("iConomy").getDescription().getVersion());
			new IconomyEventLisneter(this);
		}
		if(enable_craftconomy){
			logger.info(logprefix + "Found CraftConomy!");
			logger.info(logprefix + "Version: " + manager.getPlugin("CraftConomy").getDescription().getVersion());
			new CraftConomyEventListener(this);
		}
		if(enable_playerpoints){
			logger.info(logprefix + "Found PlayerPoints!");
			logger.info(logprefix + "Version: " + manager.getPlugin("PlayerPoints").getDescription().getVersion());
			new PlayerPointsEventListener(this);
		}
		//MySQL 接続
		use_mysql = conf.getBoolean("Settings.Database.MySQL.use");
		if(use_mysql){
			mysql_url = conf.getString("Settings.Database.MySQL.Host") + ":" + conf.getInt("Settings.Database.MySQL.Port");
			mysql_database = conf.getString("Settings.Database.MySQL.Database");
			mysql_user = conf.getString("Settings.Database.MySQL.User");
			mysql_pass = conf.getString("Settings.Database.MySQL.Pass");
			if(mysql_url != null && mysql_database != null && mysql_user != null && mysql_pass != null) MySQL.connect(mysql_url, mysql_database, mysql_user, mysql_pass); else logger.warning(logprefix + "Invalid MySQL Settings!");
		}
		new EventListener(this);
		new ChestShopEventListener(this);
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
