package fr.asynchronous.unm.core.event.block;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.asynchronous.unm.core.UNMPlugin;
import fr.asynchronous.unm.core.event.UNMListener;

public class BlockPlace extends UNMListener {
	
	public BlockPlace(UNMPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void on(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (player.isOp()) return;
		event.setCancelled(true);
	}
	
	@EventHandler
	public void on(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (player.isOp()) return;
		event.setCancelled(true);
	}

}
