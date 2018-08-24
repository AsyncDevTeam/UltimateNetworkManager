package fr.asynchronous.unm.core.event;

import org.bukkit.event.Listener;

import fr.asynchronous.unm.core.UNMPlugin;

public class UNMListener implements Listener {
	
	// Variables plugin
	protected UNMPlugin plugin;
	
	/**
	 * Constructor extends
	 * @param plugin
	 */
	protected UNMListener(UNMPlugin plugin) {
		this.plugin = plugin;
	}

}
