/**
 * Genereted by Happybavarian07 on 17:23:53, 01.06.2021, 2021
 * Filename: HomeCommand.java
 */
package de.happybavarian07.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author quiri
 *
 */
public class HomeCommand implements CommandExecutor {
	
	FileConfiguration homes;
	File homefile;
	
	public HomeCommand(FileConfiguration homes, File homefile) {
		this.homes = homes;
		this.homefile = homefile;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("home")) {
			if(s instanceof Player) {
				Player p = (Player) s;
				if(args.length == 1) {
					if(p.hasPermission("sethome.hometp")) {
						String homename = args[0];
						
						if(homes == null || homefile == null) {
							p.sendMessage("§4Error: §fNo Homes Config found: Please go to a Admin!");
							return true;
						}
						
						if(!homes.contains(p.getName() + "." + homename)) {
							p.sendMessage("§4Error: §fHome §b" + homename + "§f doesn't exists!");
							return true;
						}
						
						
						World world = Bukkit.getWorld(homes.getString(p.getName() + "." + homename + ".World"));
						if(world == null) return true;
						
						double x = homes.getDouble(p.getName() + "." + homename + ".X");
						double y = homes.getDouble(p.getName() + "." + homename + ".Y");
						double z = homes.getDouble(p.getName() + "." + homename + ".Z");
						float yaw = (float) homes.getDouble(p.getName() + "." + homename + ".Yaw");
						float pitch = (float) homes.getDouble(p.getName() + "." + homename + ".Pitch");
						
						Location loc = new Location(world, x, y, z, yaw, pitch);
						p.teleport(loc);
						p.sendMessage("§aTeleported you to §6" + homename + "§a in World §6" + homes.getString(p.getName() + "." + homename + ".World") + "§a at X: §6" + homes.getInt(p.getName() + "." + homename + ".X") + "§a, Y: §6" + homes.getInt(p.getName() + "." + homename + ".Y") + "§a, Z: §6" + homes.getInt(p.getName() + "." + homename + ".Z") + "§a!");
						return true;
					} else {
						p.sendMessage("§4You don't have Permissions for that!");
					}
					return true;
				}
				if(args.length == 2) {
					if(p.hasPermission("sethome.hometp.other")) {
						Player target = Bukkit.getPlayerExact(args[1]);
						
						if(target == null) {
							p.sendMessage("§4The Player " + args[1] + " is not online or don't exists!");
							return true;
						}
						if(target.hasPermission("sethome.hometp")) {
							String homename = args[0];
							if(homes == null || homefile == null) {
								p.sendMessage("§4Error: §fNo Homes Config found: Please go to a Admin!");
								return true;
							}
							
							if(!homes.contains(p.getName() + "." + homename)) {
								p.sendMessage("§4Error: §fHome §b" + homename + "§f doesn't exists!");
								return true;
							}
							
							if(target == p) {
								p.sendMessage("§4Um dich selber zu teleportieren nutze: §6/home <Home>");
								return true;
							}
							
							World world = Bukkit.getWorld(homes.getString(p.getName() + "." + homename + ".World"));
							if(world == null) return true;
							
							double x = homes.getDouble(p.getName() + "." + homename + ".X");
							double y = homes.getDouble(p.getName() + "." + homename + ".Y");
							double z = homes.getDouble(p.getName() + "." + homename + ".Z");
							float yaw = (float) homes.getDouble(p.getName() + "." + homename + ".Yaw");
							float pitch = (float) homes.getDouble(p.getName() + "." + homename + ".Pitch");
							
							Location loc = new Location(world, x, y, z, yaw, pitch);
							target.teleport(loc);
							p.sendMessage("§aTeleported you to §6" + homename + "§a in World §6" + homes.getString(p.getName() + "." + homename + ".World") + "§a at X: §6" + homes.getInt(p.getName() + "." + homename + ".X") + "§a, Y: §6" + homes.getInt(p.getName() + "." + homename + ".Y") + "§a, Z: §6" + homes.getInt(p.getName() + "." + homename + ".Z") + "§a!");
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
