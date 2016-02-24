package net.yeahsaba.tanikyan.EconomyStatistics.Util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import net.yeahsaba.tanikyan.EconomyStatistics.EconomyStatistics;
import net.yeahsaba.tanikyan.EconomyStatistics.Event.UpdateEconomyStatusEvent;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class StatusUpdater extends JavaPlugin {

	public static int getTotalAmount(String plugin, String material){
		int ta = 0;

		return ta;
	}

	public static int getTotalMoneyAmount(String plugin, String material){
		int tma = 0;

		return tma;
	}

	public static double getAverage(int amount, int money){
		double averaged = money / amount;
		BigDecimal bi = new BigDecimal(String.valueOf(averaged));
		double average = bi.setScale(1,BigDecimal.ROUND_DOWN).doubleValue();
		return average;
	}

	public static void UpdateEconomyStatus(Plugin plugin, String targetplugin){
		new BukkitRunnable(){
			@Override
			public void run() {
				List<String> targetlist = EconomyStatistics.ssf.getStringList("Settings.Update.TargetList");
				if(targetplugin.contains("iConomy")){
					for(String material : targetlist){
						int amount = EconomyStatistics.sf_iconomy.getInt("Status." + material + ".amount");
						int money = EconomyStatistics.sf_iconomy.getInt("Status." + material + ".money");
						EconomyStatistics.sf_iconomy.set("Status." + material + ".average", getAverage(amount, money));
					}
					try {
						EconomyStatistics.sf_iconomy.save(EconomyStatistics.statusfile_iconomy);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else if(targetplugin.contains("CraftConomy")){
					for(String material : targetlist){
						int amount = EconomyStatistics.sf_craftconomy.getInt("Status." + material + ".amount");
						int money = EconomyStatistics.sf_craftconomy.getInt("Status." + material + ".money");
						EconomyStatistics.sf_craftconomy.set("Status." + material + ".average", getAverage(amount, money));
					}
					try {
						EconomyStatistics.sf_craftconomy.save(EconomyStatistics.statusfile_craftconomy);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else if(targetplugin.contains("PlayerPoints")){
					for(String material : targetlist){
						int amount = EconomyStatistics.sf_playerpoints.getInt("Status." + material + ".amount");
						int money = EconomyStatistics.sf_playerpoints.getInt("Status." + material + ".money");
						EconomyStatistics.sf_playerpoints.set("Status." + material + ".average", getAverage(amount, money));
					}
					try {
						EconomyStatistics.sf_playerpoints.save(EconomyStatistics.statusfile_playerpoints);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					EconomyStatistics.logger.warning("[ES-File] 連携プラグイン名が不正です！");
					EconomyStatistics.logger.warning("[ES-File] データ更新に失敗しました (" + targetplugin + ")");
					return;
				}
				UpdateEconomyStatusEvent event = new UpdateEconomyStatusEvent();
				Bukkit.getServer().getPluginManager().callEvent(event);
			}
		}.runTaskAsynchronously(plugin);
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

	public static void setDefaultStatus(Plugin plugin, File file, FileConfiguration filec){
		new BukkitRunnable(){
			@Override
			public void run() {
				List<String> materiallist = EconomyStatistics.ssf.getStringList("Settings.Update.TargetList");
				for(String material:materiallist){
					filec.set("Status." + material + ".amount", 0);
					filec.set("Status." + material + ".money", 0);
					filec.set("Status." + material + ".average", 0.0);
				}
			}
		}.runTaskAsynchronously(plugin);
	}

	public static boolean CreateNewStatusFile(Plugin plugin, String PluginName){
		Calendar cal = Calendar.getInstance();
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH) + 1;
	    int day = cal.get(Calendar.DATE);
		File sfile = new File("plugins/EconomyStatistics/Statistics/" + PluginName, "Status-" + year + "-" + month + "-" + day + ".yml");
		if(sfile.exists()){
			EconomyStatistics.logger.warning("[ES-File] 既にファイルが存在しているため生成できません！");
			return false;
		}else {
			FileConfiguration sfl = YamlConfiguration.loadConfiguration(sfile);
			setDefaultStatus(plugin, sfile, sfl);
			try {
				if(PluginName.contains("iConomy")){
					EconomyStatistics.statusfile_iconomy = sfile;
					EconomyStatistics.sf_iconomy = sfl;
				}else if(PluginName.contains("CraftConomy")){
					EconomyStatistics.statusfile_craftconomy = sfile;
					EconomyStatistics.sf_craftconomy = sfl;
				}else if(PluginName.contains("PlayerPoints")){
					EconomyStatistics.statusfile_playerpoints = sfile;
					EconomyStatistics.sf_playerpoints = sfl;
				}else {
					EconomyStatistics.logger.warning("[ES-File] 連携プラグイン名が不正です！");
					EconomyStatistics.logger.warning("[ES-File] 記録ファイルの生成に失敗しました (" + PluginName + ")");
					return false;
				}
				sfl.save(sfile);
				EconomyStatistics.logger.info("[ES-File] 記録ファイルの生成に成功しました (" + PluginName + ")");
				return true;
			} catch (IOException e) {
				EconomyStatistics.logger.warning("[ES-File] 記録ファイルの生成に失敗しました (" + PluginName + ")");
				e.printStackTrace();
				return false;
			}
		}
	}
}
