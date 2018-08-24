package fr.asynchronous.unm.core.game;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.asynchronous.unm.core.game.option.Options;
import fr.asynchronous.unm.core.util.ItemBuilder;

public enum Games {

	ULTIMATE_SHEEP_WARS(0, "UltimateSheepWars", new ItemBuilder(Material.WOOL).setColor(DyeColor.WHITE).setName(ChatColor.GOLD + "" + ChatColor.BOLD + "ULTIMATE SHEEP WARS")), 
	ARROW(1, "Arrow", new ItemBuilder(Material.ARROW).setName(ChatColor.GOLD + "" + ChatColor.BOLD + "ULTIMATE ARROW"));

	private int id;
	private String name;
	private ItemBuilder icon;
	private List<Options> options;

	private Games(final Integer id, final String name, final ItemBuilder icon) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.options = Options.getGameOptions(this);
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}
	
	public ItemStack getIcon() {
		return icon.toItemStack();
	}
	
	public List<Options> getOptions() {
		return options;
	}
}
