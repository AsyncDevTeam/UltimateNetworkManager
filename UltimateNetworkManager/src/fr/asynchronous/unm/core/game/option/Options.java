package fr.asynchronous.unm.core.game.option;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.asynchronous.unm.core.game.Games;
import fr.asynchronous.unm.core.game.option.type.BooleanAction;
import fr.asynchronous.unm.core.game.option.type.DoubleAction;
import fr.asynchronous.unm.core.game.option.type.IntegerAction;
import fr.asynchronous.unm.core.game.option.type.StringAction;
import fr.asynchronous.unm.core.util.ItemBuilder;

public class Options {

	/*
	 * TODO Pour boolean Item activé = enchant sinon pas d'effet enchant
	 */
	private String configPath;
	private OptionType type;
	private ItemBuilder icon;
	
	/**
	 * Constructor to make your own options
	 * @param configPath Path access
	 * @param type Option Type
	 * @param icon Item
	 */
	public Options(String configPath, OptionType type, String name, Material mat, Object def) {
		this.configPath = configPath;
		this.type = type;
		this.icon = new ItemBuilder(mat).setName(name);
		switch (type) {
		case BOOLEAN:
			this.icon = icon.setIllegallyGlowing((boolean) def);
			break;
		case DOUBLE:
			this.icon = icon.setLore((double) def + "");
			break;
		case INTEGER:
			this.icon = icon.setAmount((int) def);
			break;
		case STRING:
			this.icon = icon.setLore((String) def);
			break;
		}
	}

	public static enum OptionType {
		BOOLEAN(new BooleanAction()), 
		STRING(new StringAction()), 
		INTEGER(new IntegerAction()), 
		DOUBLE(new DoubleAction());
		
		private OptionAction action;
		
		private OptionType(OptionAction action) {
			this.action = action;
		}
		
		public OptionAction getAction() {
			return action;
		}
	}
	
	public static interface OptionAction {
		
		public abstract ItemStack getNewItem(ItemStack oldItem, ClickType clickType);
		
		public abstract Object getValue(ItemStack currentItem);
	}
	
	/*
	 * GETTER
	 */
	
	public ItemStack getIcon() {
		return icon.toItemStack();
	}
	
	/**
	 * Type 
	 * @param type
	 */
	public void setType(OptionType type) {
		this.type = type;
	}
	
	/**
	 * Récupère les options de jeux
	 * @param game
	 * @return
	 */
	public static List<Options> getGameOptions(Games game) {
		List<Options> options = new ArrayList<>();
		switch (game) {
			case ULTIMATE_SHEEP_WARS:
				options.add(new Options("prefix", OptionType.STRING, "Change the prefix", Material.PAPER, "&8[&9SheepWars&8]"));
				break;
			case ARROW:
				break;
		}
		return options;
	}
	
	/**
	 * Test NullPointer
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getGameOptions(Games.ARROW));
	}

}
