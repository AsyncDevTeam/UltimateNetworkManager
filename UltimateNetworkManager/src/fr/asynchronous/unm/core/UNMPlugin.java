package fr.asynchronous.unm.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.asynchronous.unm.core.command.ConfigCommand;
import fr.asynchronous.unm.core.event.UNMListener;
import fr.asynchronous.unm.core.event.block.BlockPlace;
import fr.asynchronous.unm.core.event.player.PlayerInteract;
import fr.asynchronous.unm.core.event.player.PlayerJoin;
import fr.asynchronous.unm.core.event.player.PlayerSwap;
import fr.asynchronous.unm.core.util.ExceptionUtils;

public class UNMPlugin extends JavaPlugin {
	
	// Primary variables
	public static UNMPlugin instance;
	public static String ITEM_NAME = ChatColor.GOLD + "Configure ton serveur !";
	
	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		// Instance plugin
		instance = this;	
		// Listeners
		this.register(PlayerJoin.class, BlockPlace.class, PlayerInteract.class, PlayerSwap.class);
		// Commands
		this.getCommand("config").setExecutor(new ConfigCommand(this));
		// Config
		loadConfiguration();
	}
	
	/**
	 * Register all listeners with extends UNMListener
	 * @param classes
	 */
	private void register(@SuppressWarnings("unchecked") final Class<? extends UNMListener>... classes) {
		try {
			for (final Class<? extends UNMListener> clazz : classes) {
				final Constructor<? extends UNMListener> constructor = clazz.getConstructor(UNMPlugin.class);
				Bukkit.getPluginManager().registerEvents((Listener) constructor.newInstance(this), (Plugin) this);
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException ex) {
			ExceptionUtils.register(ex, true);
			try {
				throw ex;
			} catch (java.lang.Exception exx) {
				ExceptionUtils.register(exx, true);
			}
		}
	}
	
	/**
	 * Load the configuration path config.yml
	 */
	private void loadConfiguration() {
		this.getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
	}
	
	/**
	 * Debug or log
	 * @param level
	 * @param message
	 */
	public static void log(Level level, String message) {
		getInstance().getLogger().log(level, message);
	}

	public static UNMPlugin getInstance() {
		return instance;
	}
	
}
