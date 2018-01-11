package topkick;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NetherTopKick extends JavaPlugin{
	
	public void onEnable(){
		if (!new File(getDataFolder(), "config.yml").exists())
	    {
	      saveDefaultConfig();
	      reloadConfig();
	    }
		
		World w = Bukkit.getServer().getWorld(getConfig().getString("Spawn.World"));
		Double x = getConfig().getDouble("Spawn.x");
		Double y = getConfig().getDouble("Spawn.y");
		Double z = getConfig().getDouble("Spawn.z");
				
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(p.getWorld().getEnvironment() == World.Environment.NETHER){
						if(p.hasPermission("nethertopkick.bypass")) return;
						if(p.getLocation().getY() >= 127){
							p.sendMessage(ChatColor.RED + "禁止在地獄基岩上方活動！");
							try{
								p.teleport(new Location(w, x, y, z));	
							}catch (Exception e) {
								getLogger().info("請確認座標及世界是否設置正確");
							}
						}
					}else return;
				}
				
			}
		}, 0L, 5*20L);
	}


}
