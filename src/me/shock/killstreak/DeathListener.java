package me.shock.killstreak;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener
{

	public HashMap<String, Long> killStreak = new HashMap<String, Long>();
	public Main plugin;
	
	public DeathListener(Main instance)
	{
		this.plugin = instance;
	}
	
	/**
	 * Get the killer, add +1 to his killstreak.
	 * @param event
	 */
	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		Player dead = event.getEntity();
		LivingEntity killer = dead.getKiller();
		if(killer instanceof Player)
		{
			Player player = (Player) killer;
			String name = player.getName();
			if(killStreak.containsKey(name))
			{
				long currentStreak = killStreak.get(name);
				long newStreak = currentStreak + 1;
				killStreak.put(name, newStreak);
				Bukkit.getServer().broadcastMessage("kill +1");
				return;
			}
			else
			{
				killStreak.put(name, (long) 1);
				Bukkit.getServer().broadcastMessage("killstreak started.");

				return;
			}
		}
	}
	
	public long ks(String name, Long ks)
	{
		 if(killStreak.containsKey(name))
		 {
			 long kills = killStreak.get(name);
			 return kills;
		 }
		return ks;
	}
}
