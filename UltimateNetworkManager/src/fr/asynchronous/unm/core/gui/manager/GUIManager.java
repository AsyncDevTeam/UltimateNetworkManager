package fr.asynchronous.unm.core.gui.manager;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import fr.asynchronous.unm.core.UNMPlugin;
import fr.asynchronous.unm.core.gui.base.GUIScreen;
import fr.asynchronous.unm.core.gui.task.GUITask;

public class GUIManager {

	protected static Map<String, Class<?>> openGuis = new HashMap<>();
	
	private GUIManager() {
		throw new IllegalStateException("GuiManager.class hasn't to be instantiated.");
	}
	
	public static GUIScreen openGui(UNMPlugin plugin, Player player, String inventoryName, GUIScreen gui) {
		openPlayer(player, gui.getClass());
		if (gui.isUpdate())
			new GUITask(plugin, gui.getPlayer(), inventoryName, gui).runTaskTimer(plugin, 0, 20);
		else {
			gui.open(plugin, player, inventoryName, true);
		}
		return gui;
	}

	@SuppressWarnings("rawtypes")
	private static void openPlayer(Player p, Class gui) {
		if (openGuis.containsKey(p.getName())) {
			openGuis.remove(p.getName());
			openGuis.put(p.getName(), gui);
		} else {
			openGuis.put(p.getName(), gui);
		}
	}

	public static void closePlayer(Player p) {
		if (openGuis.containsKey(p.getName())) {
			openGuis.remove(p.getName());
		}
	}

	public static boolean isPlayer(Player p) {
		if (openGuis.containsKey(p.getName()))
			return true;
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isOpened(Class clas) {
		for (Class cla : openGuis.values()) {
			if (cla.equals(clas))
				return true;
		}
		return false;
	}
}