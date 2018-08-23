package fr.asynchronous.unm.core.gui.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.asynchronous.unm.core.UNMPlugin;
import fr.asynchronous.unm.core.gui.base.GUIScreen;
import fr.asynchronous.unm.core.gui.event.GUIUpdateEvent;

public class GUITask extends BukkitRunnable {

	private final UNMPlugin plugin;
	private final Player player;
	private final GUIScreen gui;

	public GUITask(UNMPlugin plugin, Player player, String inventoryName, GUIScreen gui) {
		this.plugin = plugin;
		this.player = player;
		this.gui = gui;
		gui.open(plugin, player, inventoryName, true);
	}

	@Override
	public void run() {

		if (!gui.getInventory().getViewers().contains(this.player)) {
			this.cancel();
			return;
		}

		this.plugin.getServer().getPluginManager().callEvent(new GUIUpdateEvent(this.player, this.gui, false));
		this.gui.drawScreen();
	}
}