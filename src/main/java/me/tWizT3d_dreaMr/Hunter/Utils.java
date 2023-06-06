package me.tWizT3d_dreaMr.Hunter;

import java.util.Random;
import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
import me.tWizT3d_dreaMr.Hunter.Objects.InventoryItems;

public class Utils {
	private static ArrayList<InventoryItems> invItems= new ArrayList<>();
	private static ArrayList<ItemStack> items= new ArrayList<>();
	public static String getUUID() {
	String characters ="abcdefghijklmnopqrstuvwxyz1234567890";
	int count=0;
	StringBuilder UUID= new StringBuilder();
	while(count!=10){
		int r=random(characters.length());
			if(r!=characters.length())
				UUID.append(characters.charAt(r));
			else
				UUID.append(characters.substring(r));
	count++;	
	}
	return UUID.toString();
		
	}
	public static int random(int x){
		Random randomGenerator = new Random();
	    return randomGenerator.nextInt((x+1));
	}
	public static ItemStack[] getAllItems() {		
		return (ItemStack[]) items.toArray();
	}
	public static ArrayList<InventoryItems> getAllInvItems() {		
		return invItems;
	}
	public static void addItem(InventoryItems add) {
		items.add(add.getReward());
		invItems.add(add);
	}
}
