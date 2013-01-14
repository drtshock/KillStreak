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
	}
	
	public void onDisable()
	{
		
	}
}
