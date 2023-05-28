package me.tWizT3d_dreaMr.Hunter.Items;

import java.util.stream.IntStream;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.tWizT3d_dreaMr.Hunter.Configurator;
import me.tWizT3d_dreaMr.Hunter.Objects.HunterInventories;

public class CommandWorkings {
public static void addInventory(Block b) {
	if(b.getState() instanceof Container) {
		Container cont= (Container) b.getState();
		addInventory(cont.getInventory());
	}
}
public static void addInventory(Player p) {
	Inventory inv= p.getInventory();
	ItemStack[] items= IntStream.range(9, 35).boxed().map(inv::getItem).toArray(ItemStack[]::new);
	Inventory inventory= Bukkit.createInventory(p, 27, p.getName());
    inventory.setContents(items);
	addInventory(inventory);
}
public static void addInventory(Inventory inv) {
	String name=Configurator.getUUID();
	HunterInventories HI=new HunterInventories(name, inv.getContents());
}
}
