package de.happybavarian07.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
	
	FileConfiguration homes;
	File homesfile;
	
	public SetHomeCommand(FileConfiguration homes, File homesfile) {
		this.homes = homes;
		this.homesfile = homesfile;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sethome")) {
			if(s instanceof Player) {
				Player p = (Player) s;
				if(args.length == 1) {
					if(p.hasPermission("sethome.sethome")) {
						String homename = args[0];
						Location loc = p.getLocation();
						
						//Putting the Location in the Config
						
						if(loc == null) {
							p.sendMessage("§4Error: §fLocation is null please try again!");
							return true;
						}
						
						if(homes == null || homesfile == null) {
							p.sendMessage("§4Error: §fNo Homes Config found: Please go to a Admin!");
							return true;
						}
						
						if(homes.contains(p.getName() + "." + args[0])) {
							p.sendMessage("§4Error: §fHome §b" + args[0] + "§f already exists!");
							return true;
						}
						
						homes.set(p.getName() + "." + homename + ".World", loc.getWorld().getName());
						homes.set(p.getName() + "." + homename + ".X", loc.getX());
						homes.set(p.getName() + "." + homename + ".Y", loc.getY());
						homes.set(p.getName() + "." + homename + ".Z", loc.getZ());
						homes.set(p.getName() + "." + homename + ".Yaw", loc.getYaw());
						homes.set(p.getName() + "." + homename + ".Pitch", loc.getPitch());
						List<String> homelist = homes.getStringList(p.getName() + ".Homelist");
						homelist.add(homename);
						homes.set(p.getName() + ".Homelist", homelist);
						try {
							homes.save(homesfile);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						p.sendMessage("§aSuccessfully created home §6" + homename + "§a in World §6" + homes.getString(p.getName() + "." + homename + ".World") + "§a at X: §6" + homes.getInt(p.getName() + "." + homename + ".X") + "§a, Y: §6" + homes.getInt(p.getName() + "." + homename + ".Y") + "§a, Z: §6" + homes.getInt(p.getName() + "." + homename + ".Z") + "§a!");
						return true;
					} else {
						p.sendMessage("§4You don't have Permissions for that!");
					}
					return true;
				}
				if(args.length == 2) {
					if(p.hasPermission("sethome.sethome.others")) {
						Player target = Bukkit.getPlayerExact(args[1]);
						
						if(target == null) {
							p.sendMessage("§4The Player " + args[1] + " is not online or don't exists!");
							return true;
						}
						if(target.hasPermission("sethome.sethome")) {
							String homename = args[0];
							Location loc = target.getLocation();
							
							//Putting the Location in the Config
							
							if(loc == null) {
								p.sendMessage("§4Error: §fLocation is null please try again!");
								return true;
							}
							
							if(homes == null || homesfile == null) {
								p.sendMessage("§4Error: §fNo Homes Config found: Please go to a Admin!");
								return true;
							}
							
							if(homes.contains(p.getName() + "." + args[0])) {
								p.sendMessage("§4Error: §fHome §b" + args[0] + "§f already exists!");
								return true;
							}
							homes.set(p.getName() + "." + homename + ".Targeted_Player", target.getName());
							homes.set(p.getName() + "." + homename + ".World", loc.getWorld().getName());
							homes.set(p.getName() + "." + homename + ".X", loc.getX());
							homes.set(p.getName() + "." + homename + ".Y", loc.getY());
							homes.set(p.getName() + "." + homename + ".Z", loc.getZ());
							homes.set(p.getName() + "." + homename + ".Yaw", loc.getYaw());
							homes.set(p.getName() + "." + homename + ".Pitch", loc.getPitch());
							List<String> homelist = homes.getStringList(p.getName() + ".Homelist");
							homelist.add(homename);
							homes.set(p.getName() + ".Homelist", homelist);
							try {
								homes.save(homesfile);
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							p.sendMessage("§aSuccessfully created home §6" + homename + "§a in World §6" + homes.getString(p.getName() + "." + homename + ".World") + "§a at X: §6" + homes.getInt(p.getName() + "." + homename + ".X") + "§a, Y: §6" + homes.getInt(p.getName() + "." + homename + ".Y") + "§a, Z: §6" + homes.getInt(p.getName() + "." + homename + ".Z") + "§a!");
							return true;
						} else {
							p.sendMessage("§4Dein Target §6" + target.getName() + " §4hat keine Rechte dazu!");
						}
					} else {
						p.sendMessage("§4You don't have Permissions for that!");
					}
					return true;
				}
				
				p.sendMessage("§4Too many / few Arguments!");
				p.sendMessage("§6Usage: " + cmd.getUsage());
				return true;
			} else {
				s.sendMessage("§4Only Players can executed this Command!");
				return true;
			}
		}
		return true;
	}
}
