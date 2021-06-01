package de.happybavarian07.commands;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HomesListCommand implements CommandExecutor {
	
	FileConfiguration homes;
	File homefile;
	
	public HomesListCommand(FileConfiguration homes, File homefile) {
		this.homes = homes;
		this.homefile = homefile;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("homes")) {
				Player p = (Player) s;
				if(args.length == 0) {
					if(p.hasPermission("sethome.homes")) {
						List<String> homelist = homes.getStringList(p.getName() + ".Homelist");
						
						if(homes == null || homefile == null) {
							p.sendMessage("§4Error: §fNo Homes Config found: Please go to a Admin!");
							return true;
						}
						
						if(homelist.isEmpty()) {
							p.sendMessage("§4You have no homes!");
							return true;
						}
						
						p.sendMessage("§a+---------------Your-Homes---------------+");
						for(int i = 0; i < homelist.size(); i++) {
							p.sendMessage("   §4" + (i+1) + "§4. " + homelist.get(i) + "§a:\n" + 
									"     §aWorld: §6" + homes.getString(p.getName() + "." + homelist.get(i) + ".World") + "§a,\n" + 
									"     §aX: §6" + homes.getString(p.getName() + "." + homelist.get(i) + ".X") + "§a,\n" + 
									"     §aY: §6" + homes.getString(p.getName() + "." + homelist.get(i) + ".Y") + "§a,\n" + 
									"     §aZ: §6" + homes.getString(p.getName() + "." + homelist.get(i) + ".Z") + "§a,\n" + 
									"     §aYaw: §6" + homes.getString(p.getName() + "." + homelist.get(i) + ".Yaw") + "§a,\n" + 
									"     §aPitch: §6" + homes.getString(p.getName() + "." + homelist.get(i) + ".Pitch") + "\n");
						}
						p.sendMessage("§a+----------------------------------------+");
						return true;
					} else {
						p.sendMessage("§4You don't have Permissions for that!");
					}
					return true;
				}
				if(args.length == 1) {
					if(p.hasPermission("sethome.homes.other")) {
						Player target = Bukkit.getPlayerExact(args[0]);
						
						if(target == null) {
							p.sendMessage("§4The Player " + args[0] + " is not online or don't exists!");
							return true;
						}
						List<String> homelist = homes.getStringList(target.getName() + ".Homelist");
						
						if(homes == null || homefile == null) {
							p.sendMessage("§4Error: §fNo Homes Config found: Please go to a Admin!");
							return true;
						}
						
						if(homelist.isEmpty()) {
							p.sendMessage("§4The Player §6" + target.getName() + "§4 has no homes!");
							return true;
						}
						
						p.sendMessage("§a+---------------Your-Homes---------------+");
						for(int i = 0; i < homelist.size(); i++) {
							p.sendMessage("   §4" + (i+1) + "§4. " + homelist.get(i) + "§a:\n" + 
									"     §aWorld: §6" + homes.getString(target.getName() + "." + homelist.get(i) + ".World") + "§a,\n" + 
									"     §aX: §6" + homes.getString(target.getName() + "." + homelist.get(i) + ".X") + "§a,\n" + 
									"     §aY: §6" + homes.getString(target.getName() + "." + homelist.get(i) + ".Y") + "§a,\n" + 
									"     §aZ: §6" + homes.getString(target.getName() + "." + homelist.get(i) + ".Z") + "§a,\n" + 
									"     §aYaw: §6" + homes.getString(target.getName() + "." + homelist.get(i) + ".Yaw") + "§a,\n" + 
									"     §aPitch: §6" + homes.getString(target.getName() + "." + homelist.get(i) + ".Pitch") + "\n");
						}
						p.sendMessage("§a+----------------------------------------+");
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
		} else {
			s.sendMessage("§4Only Players can executed this Command!");
			return true;
		}
		return true;
	}
}
