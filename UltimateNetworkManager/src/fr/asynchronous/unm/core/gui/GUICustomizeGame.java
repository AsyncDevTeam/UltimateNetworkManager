package fr.asynchronous.unm.core.gui;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.asynchronous.unm.core.game.Games;
import fr.asynchronous.unm.core.game.option.Options;
import fr.asynchronous.unm.core.gui.base.GUIScreen;

public class GUICustomizeGame extends GUIScreen {

	private final Games game;
	private Map<Integer, Options> map;
	
	public GUICustomizeGame(Games game) {
		super(6, false);
		this.game = game;
		this.map = new HashMap<>();
	}

	@Override
	public void drawScreen() {
		int slot = 0;
		for (Options option : this.game.getOptions()) {
			this.map.put(slot, option);
			setItem(option.getIcon(), slot);
			slot++;
		}
	}

	@Override
	public void onOpen() {
	}

	@Override
	public void onClose() {
	}

	@Override
	public void onClick(ItemStack item, InventoryClickEvent event) {
		if (!item.hasItemMeta())
			return;
		final int slot = event.getSlot();
		final Options option = this.map.get(slot);
		
	}

}
