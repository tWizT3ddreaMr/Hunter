package me.tWizT3d_dreaMr.Hunter.Objects;

import org.bukkit.inventory.ItemStack;

public class InventoryItems {
private final double Percentage;
private final ItemStack Reward;
private final String ID;

public InventoryItems(ItemStack Reward, double percentage, String ID){
	this.Percentage=percentage;
	this.Reward=Reward;
	this.ID=ID;
}


public ItemStack getReward() {
	return Reward;
}
public String getID() {
	return ID;
}
public double Percentage() {
	return Percentage;
}
}
