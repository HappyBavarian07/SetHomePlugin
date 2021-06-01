package de.happybavarian07.sethomeplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import de.happybavarian07.apis.FileManager;
import de.happybavarian07.apis.StartUpLogger;
import de.happybavarian07.commands.HomeCommand;
import de.happybavarian07.commands.HomeTabCompleter;
import de.happybavarian07.commands.HomesListCommand;
import de.happybavarian07.commands.RemoveHomeCommand;
import de.happybavarian07.commands.SetHomeCommand;

public class SetHomePlugin extends JavaPlugin {
	
	FileManager fm;
	StartUpLogger logger;
	
	@Override
	public void onEnable() {
		logger = new StartUpLogger();
		fm = new FileManager(this);
		logger.coloredSpacer(ChatColor.AQUA).message("§aCreating File Homes.yml if not exists!§r").spacer();
		fm.createFile("", "Homes", "yml");
		logger.message("§4Register Commands:§r").spacer();
		HomeTabCompleter htc = new HomeTabCompleter(fm.getConfig("", "Homes", "yml"), fm.getFile("", "Homes", "yml"));
		getCommand("sethome").setExecutor(new SetHomeCommand(fm.getConfig("", "Homes", "yml"), fm.getFile("", "Homes", "yml")));
		logger.message("§6Command: §c/sethome§r");
		getCommand("home").setTabCompleter(htc);
		logger.message("§6TabCompleter: §c/home§r");
		getCommand("home").setExecutor(new HomeCommand(fm.getConfig("", "Homes", "yml"), fm.getFile("", "Homes", "yml")));
		logger.message("§6Command: §c/home§r");
		getCommand("homes").setExecutor(new HomesListCommand(fm.getConfig("", "Homes", "yml"), fm.getFile("", "Homes", "yml")));
		logger.message("§6Command: §c/homes§r");
		getCommand("removehome").setExecutor(new RemoveHomeCommand(fm.getConfig("", "Homes", "yml"), fm.getFile("", "Homes", "yml")));
		logger.message("§6TabCompleter: §c/removehome§r");
		getCommand("removehome").setTabCompleter(htc);
		logger.message("§6TabCompleter: §c/removehome§r");
		logger.emptySpacer().message("§aStarting completely done!§r");
		
		logger.emptySpacer().emptySpacer().emptySpacer().coloredSpacer(ChatColor.GREEN).message("§r[§4Set§6Home§5Plugin§r] successfully enabled!").coloredSpacer(ChatColor.GREEN).emptySpacer().emptySpacer().emptySpacer();
	}
	
	@Override
	public void onDisable() {
		logger.emptySpacer().emptySpacer().emptySpacer().coloredSpacer(ChatColor.RED).message("§r[§4Set§6Home§5Plugin§r] successfully disabled!").coloredSpacer(ChatColor.RED).emptySpacer().emptySpacer().emptySpacer();
	}
}
