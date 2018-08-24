package fr.asynchronous.unm.core.event.player;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.asynchronous.unm.core.UNMPlugin;
import fr.asynchronous.unm.core.event.UNMListener;
import fr.asynchronous.unm.core.util.ItemBuilder;

public class PlayerJoin extends UNMListener {

	public PlayerJoin(final UNMPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void on(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		// Join message
		event.setJoinMessage(ChatColor.GOLD + "Coucou pour le moment il n'y a pas de grade du coup.");
		// Item
		ItemStack item = new ItemBuilder(Material.NETHER_STAR).setName(UNMPlugin.ITEM_NAME).setIllegallyGlowing(true).toItemStack();
		// Select item
		player.getInventory().setItem(4, item);
		// Task
		// ByPass
		if (player.isOp()) return;
		// NoBypass
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (!player.isOnline()) return;
				player.setItemOnCursor(item);
			}
		}.runTaskTimer(plugin, 0L, 5L);
	}
}
