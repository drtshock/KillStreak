package me.shock.killstreak;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor
{

	public Main plugin;
	
	DeathListener deathL = new DeathListener(plugin);
	HashMap<String, Long> killStreak = deathL.killStreak;


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			if(cmd.getName().equalsIgnoreCase("killstreak") || cmd.getName().equalsIgnoreCase("ks"))
			{
				String name = sender.getName();
				Bukkit.getServer().broadcastMessage(name + "");
				if(killStreak.containsKey(name))
				{
					long streak = killStreak.get(name);
					sender.sendMessage(ChatColor.GRAY + "You are on a " + ChatColor.RED + streak + " kill streak.");
					return true;
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "You are not on a kill streak.");
				}
			}
		}
		else
		{
			sender.sendMessage("Consoles can't have kill streaks!");
			return true;
		}
		
		
		return true;
	}
	
}
