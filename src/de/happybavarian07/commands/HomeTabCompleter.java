package de.happybavarian07.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HomeTabCompleter implements TabCompleter {
	
	List<String> homeslist = new ArrayList<String>();
	
	FileConfiguration homes;
	File homefile;
	
	public HomeTabCompleter(FileConfiguration homes, File homefile) {
		this.homes = homes;
		this.homefile = homefile;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender s, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("home")) {
			for(int i = 0; i < homes.getStringList(s.getName() + ".Homelist").size(); i++) {
				homeslist.add(i, homes.getStringList(s.getName() + ".Homelist").get(i));
			}
			
			List<String> result = new ArrayList<String>();
			if (args.length == 1) {
				for (String a : homeslist) {
					if (a.toLowerCase().startsWith(args[0].toLowerCase()))
						result.add(a);
				}
				return result;
			}
			if(args.length > 1) {
				return null;
			}
			return homeslist;
		}
		if(cmd.getName().equalsIgnoreCase("removehome")) {
			if(args.length == 1) {
				for(int i = 0; i < homes.getStringList(s.getName() + ".Homelist").size(); i++) {
					homeslist.add(i, homes.getStringList(s.getName() + ".Homelist").get(i));
				}
				
				List<String> result = new ArrayList<String>();
				if(args.length == 2) {
					for (String a : homeslist) {
						if (a.toLowerCase().startsWith(args[0].toLowerCase()))
							result.add(a);
					}
					return result;
				}
				if(args.length > 1) {
					return null;
				}
				return homeslist;
			}
			
			return null;
		}
		return null;
	}
	
	public void testmethod() {
		
	}
}
