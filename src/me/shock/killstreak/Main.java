package me.shock.killstreak;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

	public Main plugin;
	
	public void onEnable()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new DeathListener(this), this);
		getCommand("killstreak").setExecutor(new Commands());
		getCommand("ks").setExecutor(new Commands());
		
		loadStreaks();
	}
	
	public void onDisable()
	{
		
	}
	
	public void loadStreaks()
	{
		Logger log = getServer().getLogger();
		/**
		 * Check to see if there's a streaks file.
		 * If not then create a new one.
		 */
		File config = new File(getDataFolder() + "/streaks.yml");
		if(!config.exists())
		{
			try{
				getDataFolder().mkdir();
				config.createNewFile();
			} catch (IOException e) {
				log.log(Level.SEVERE, "[KillStreaks] Couldn't create streaks file.");
			}
			/**
			 * Write the config file here.
			 * New, genius way to write it :)
			 */
			try {
				FileOutputStream fos = new FileOutputStream(new File(getDataFolder() + File.separator + "streaks.yml"));
				InputStream is = getResource("streaks.yml");
				byte[] linebuffer = new byte[4096];
				int lineLength = 0;
				while((lineLength = is.read(linebuffer)) > 0)
				{
					fos.write(linebuffer, 0, lineLength);
				}
				fos.close();
				
				log.log(Level.INFO, "[KillStreak] Wrote new streaks file.");
				
			} catch (IOException e) {
				log.log(Level.SEVERE, "[KillStreak] Couldn't write streaks file: " + e);
			}	
		}
		else
		{
			log.log(Level.INFO, "[KillStreak] streaks file found.");
		}
	}
}
