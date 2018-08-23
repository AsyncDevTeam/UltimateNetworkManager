package fr.asynchronous.unm.core.gui;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.asynchronous.unm.core.gui.base.GUIScreen;
import fr.asynchronous.unm.core.util.ItemBuilder;

public class GUIMenu extends GUIScreen {

	public GUIMenu() {
		super(6, false);
	}
	
	@Override
	public void drawScreen() {
		// TODO DECORATION
		SlotSequence.BORDER.fillWith(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("").setColor(DyeColor.WHITE), this.inventory);
		SlotSequence.SUCCEED_BOTTOM.fillWith(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("").setColor(DyeColor.WHITE), this.inventory);
		SlotSequence.SUCCEED_TOP.fillWith(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("").setColor(DyeColor.WHITE), this.inventory);
		// TODO CREATE THE ITEM
		// To configurate the mini-game (SheepWars,Arrow..)
	}

	@Override
	public void onOpen() {
		
	}

	@Override
	public void onClose() {
		
	}

	@Override
	public void onClick(ItemStack item, InventoryClickEvent event) {
		
		/*
		 * CLICK
		 */
		
	}

}
