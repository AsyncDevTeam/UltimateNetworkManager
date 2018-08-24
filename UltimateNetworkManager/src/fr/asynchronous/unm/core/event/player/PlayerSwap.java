package fr.asynchronous.unm.core.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import fr.asynchronous.unm.core.UNMPlugin;
import fr.asynchronous.unm.core.event.UNMListener;

public class PlayerSwap extends UNMListener {

	public PlayerSwap(UNMPlugin plugin) {
		super(plugin);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void on(PlayerSwapHandItemsEvent event) {
		Player player = event.getPlayer();
		if (player.isOp())
			return;
		event.setCancelled(true);
	}

}
