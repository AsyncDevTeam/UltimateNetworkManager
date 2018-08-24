package fr.asynchronous.unm.core.event.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.asynchronous.unm.core.UNMPlugin;
import fr.asynchronous.unm.core.event.UNMListener;
import fr.asynchronous.unm.core.gui.GUIMenu;
import fr.asynchronous.unm.core.gui.manager.GUIManager;

public class PlayerInteract extends UNMListener {

	public PlayerInteract(UNMPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void on(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		// Click droit ?
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			// Si oui on verifie l'item
			ItemStack stack = event.getItem();
			if (stack == null) return;
			if (!stack.hasItemMeta()) return;
			if (stack.getItemMeta().getDisplayName().contains(UNMPlugin.ITEM_NAME)) {
				GUIManager.openGui(plugin, player, ChatColor.GRAY + "Ondemand server", new GUIMenu());
			}
		}
	}

}
