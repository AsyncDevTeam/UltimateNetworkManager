package fr.asynchronous.unm.core.command;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.asynchronous.unm.core.UNMPlugin;
import fr.asynchronous.unm.core.util.Utils;

/**
 * Command configuration
 * @author Asynchronous
 */
public class ConfigCommand implements CommandExecutor {
	
	// Variables plugin
	private UNMPlugin plugin;
	
	public ConfigCommand(UNMPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Sender log
		if (!(sender instanceof Player)) {
			UNMPlugin.log(Level.INFO, "Commande uniquement en jeux !");
			return true;
		}
		
		Player player = (Player) sender;
		
		// /config alone
		if (args.length < 2) {
			displayHelp(player);
			return true;
		}
		
		// Command create a line in config.yml
		if (args.length >= 2) {
			try {
				String configPath = args[0].trim();
				String value = args[1].trim();
				
				Utils.configPath = configPath;
				
				if (value.equalsIgnoreCase("%location%")) {
					this.plugin.getConfig().set(configPath, Utils.toString(player.getLocation()));
					this.plugin.saveConfig();
					player.sendMessage(ChatColor.GREEN + "Location '" + configPath + "' set !");
					return true;
				}
				
				if (Utils.isInteger(value)) {
					this.plugin.getConfig().set(configPath, Integer.parseInt(value));
					this.plugin.saveConfig();
					player.sendMessage(ChatColor.GREEN + "Integer '" + configPath + "' set !");
					return true;
				}
				
				if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
					value = value.toLowerCase();
					this.plugin.getConfig().set(configPath, Boolean.parseBoolean(value));
					this.plugin.saveConfig();
					player.sendMessage(ChatColor.GREEN + "Boolean '" + configPath + "' set !");
					return true;
				}
				
				if (Utils.isDouble(value)) {
					this.plugin.getConfig().set(configPath, Double.parseDouble(value));
					this.plugin.saveConfig();
					player.sendMessage(ChatColor.GREEN + "Double '" + configPath + "' set !");
				}
				
				this.plugin.getConfig().set(configPath, value);
				this.plugin.saveConfig();
				player.sendMessage(ChatColor.GREEN + "String '" + configPath + "' set !");
			} catch (ArrayIndexOutOfBoundsException e) {
				player.sendMessage(ChatColor.RED + "/config <ConfigName> <Value>");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Placeholders :
	 * - %location% : current location of the player
	 * 
	 * Display help to player
	 * @param player
	 */
	private void displayHelp(Player player) {
		player.sendMessage(ChatColor.YELLOW + "/config <ConfigName> <Value>");
	}

}
