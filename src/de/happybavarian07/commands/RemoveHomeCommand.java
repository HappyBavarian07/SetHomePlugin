package de.happybavarian07.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class RemoveHomeCommand implements CommandExecutor {
	
	FileConfiguration homes;
	File homefile;
	
	public RemoveHomeCommand(FileConfiguration homes, File homefile) {
		this.homes = homes;
		this.homefile = homefile;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player) {
			Player p = (Player) s;
			if(cmd.getName().equalsIgnoreCase("removehome")) {
				if(args.length == 1) {
					if(p.hasPermission("sethome.removehome")) {
						String homename = args[0];
						if(!homes.contains(p.getName() + "." + homename)) {
							p.sendMessage("§4Error: §fHome §b" + homename + "§f doesn't exists!");
							return true;
						}
						
						homes.set(p.getName() + "." + homename, null);
						List<String> homelist = homes.getStringList(p.getName() + ".Homelist");
						homelist.remove(homename);
						homes.set(p.getName() + ".Homelist", homelist);
						try {
							homes.save(homefile);
						} catch (IOException e) {
							e.printStackTrace();
						}
						p.sendMessage("§aSuccessfully removed Home: §6" + homename);
						return true;
					} else {
						p.sendMessage("§4You don't have Permissions for that!");
					}
					return true;
				}
				
				p.sendMessage("§4Too many / few Arguments!");
				p.sendMessage("§6Usage: " + cmd.getUsage());
				return true;
			}
		}
		return true;
	}
}
