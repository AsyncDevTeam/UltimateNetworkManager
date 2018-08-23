package fr.asynchronous.unm.core.gui.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.asynchronous.unm.core.gui.base.GUIScreen;

public class GUIUpdateEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private GUIScreen gui;

	public GUIUpdateEvent(Player player, GUIScreen gui, boolean who) {
		super(who);
		this.player = player;
		this.gui = gui;
	}

	public GUIScreen getGui() {
		return gui;
	}

	public Player getPlayer() {
		return player;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}