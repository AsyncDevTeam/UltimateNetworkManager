package fr.asynchronous.unm.core.game.option.type;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.asynchronous.unm.core.game.option.Options.OptionAction;

public class DoubleAction implements OptionAction {

	@Override
	public ItemStack getNewItem(ItemStack oldItem, ClickType clickType) {
		return null;
	}

	@Override
	public Boolean getValue(ItemStack currentItem) {
		return null;
	}

}
