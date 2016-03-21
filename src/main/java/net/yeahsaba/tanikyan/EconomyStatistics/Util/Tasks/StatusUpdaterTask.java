package net.yeahsaba.tanikyan.EconomyStatistics.Util.Tasks;

import net.yeahsaba.tanikyan.EconomyStatistics.EconomyStatistics;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

/**
 * Created by chroma on 16/03/21.
 */
public class StatusUpdaterTask extends BukkitRunnable {
    @Override
    public void run() {
        List<String> list = EconomyStatistics.ssf.getStringList("Settings.Update.TargetList");
        for (String s : list) {
            //更新処理(?)
        }
    }
}
