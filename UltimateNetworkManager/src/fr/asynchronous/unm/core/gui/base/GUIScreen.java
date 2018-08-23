package fr.asynchronous.unm.core.gui.base;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.asynchronous.unm.core.UNMPlugin;
import fr.asynchronous.unm.core.gui.manager.GUIManager;
import fr.asynchronous.unm.core.util.ItemBuilder;

public abstract class GUIScreen implements Listener {
	
	/*
	 * JE TE LAISSE UNE PETIT NOTE D'AMOUR
	 * BISOUS :)
	 * JE T'AIME
	 * Ugo.Gl 
	 */
	
	public int size;
	public boolean update;
	
	public Inventory inventory;
	public Player player;
	public UNMPlugin plugin;

	public GUIScreen(int size, boolean update) {
		this.size = size;
		if (size > 6)
			throw new IllegalArgumentException("Size of the inventory can't exceed 6 because minecraft accepts only 6 lines max.");
		this.update = update;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isUpdate() {
		return this.update;
	}

	public void open(UNMPlugin plugin, Player player, String inventoryName, boolean registerEvents) {
		this.inventory = Bukkit.createInventory(null, size * 9, inventoryName);
		this.player = player;
		this.plugin = plugin;
		player.openInventory(this.inventory);
		drawScreen();
		player.updateInventory();
		if (registerEvents)
			Bukkit.getPluginManager().registerEvents(this, plugin);
		onOpen();
	}

	public void close() {
		this.player.closeInventory();
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void setItem(ItemStack item, int slot) {
		this.inventory.setItem(slot, item);
	}

	public void addItem(ItemStack item) {
		this.inventory.addItem(item);
	}

	public void setItem(ItemStack item, int line, int colomn) {
		setItem(item, (line * 9 - 9) + colomn - 1);
	}

	public void setItemLine(ItemStack item, int line) {
		for (int i = (line * 9 - 9); i < (line * 9); i++)
			setItem(item, i);
	}
	
	public void clearInventory() {
		this.inventory.clear();
	}

	public void setFont(ItemStack item) {
		for (int i = 0; i < inventory.getSize(); i++)
			setItem(item, i);
	}

	@EventHandler
	public void onPlayerInventory(InventoryClickEvent event) {
		if (event.getClickedInventory() == null)
			return;
		if (event.getClickedInventory().equals(this.inventory))
			onClick(event.getCurrentItem(), event);
	}
	
	public abstract void drawScreen();

	public abstract void onOpen();
	
	public abstract void onClose();

	public abstract void onClick(ItemStack item, InventoryClickEvent event);

	@EventHandler
	public void onPlayerInventory(InventoryCloseEvent e) {
		if (!GUIManager.isOpened(this.getClass())) {
			HandlerList.unregisterAll(this);
			onClose();
		}
	}
	
	public static enum SlotSequence {
		
		BORDER(0, 9, 18, 27, 36, 45, 46, 47, 48, 49, 50, 51, 52, 53, 44, 35, 26, 17, 8, 7, 6, 5, 4, 3, 2, 1),
		SUCCEED_TOP(0, 1, 2, 3, 4, 5, 6, 7, 8), 
		SUCCEED_BOTTOM(53, 52, 51, 50, 49, 48, 47, 46, 45);

		private List<Integer> slots;
		
		private SlotSequence(Integer... slots) {
			this.slots = Arrays.asList(slots);
		}
		
		public List<Integer> getSlots() {
			return slots;
		}
		
		public void fillWith(ItemBuilder item, Inventory inv) {
			final Iterator<Integer> iter = slots.iterator();
			while (iter.hasNext()) {
				final int i = iter.next();
				inv.setItem(i, item.toItemStack());
			}
		}
	}
}