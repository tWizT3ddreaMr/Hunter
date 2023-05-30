package me.tWizT3d_dreaMr.Hunter.Objects;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HunterInventories {
private final ItemStack[] items;
private final double Percentage;
private final String ID;
public HunterInventories(String ID,ItemStack ... items) {
	this.ID=ID;
	Percentage=1;
	this.items= items;	
}
public HunterInventories(Double Percentage, String ID, ItemStack ... items) {
	this.ID=ID;
	this.Percentage=Percentage;
	this.items= items;	
}
public String getID() {
	return ID;
}
public double getPercentage() {
	return Percentage;
}
public ItemStack[] getItems() {
	return items;
}
public Inventory setContents(Inventory inv) {
	inv.clear();
	
	inv.setContents(items);
	return inv;
}
}
