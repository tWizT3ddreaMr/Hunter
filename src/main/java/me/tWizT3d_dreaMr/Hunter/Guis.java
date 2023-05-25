package me.tWizT3d_dreaMr.Hunter;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

import me.tWizT3d_dreaMr.Hunter.Objects.Gui;

public class Guis {
public static ArrayList<Gui> guilist;
public static Gui getGui(InventoryView IV) {
	if(guilist==null) {
		guilist= new ArrayList<Gui>();
	}
	if(guilist.isEmpty()) {
		return null;
	}
	for(Gui gui:guilist) {
		if(gui.is(IV))
			return gui;
	}
	return null;
}
public static void add(Gui gui) {
	if(guilist==null) {
		guilist= new ArrayList<Gui>();
	}
	guilist.add(gui);
}
public static void makeGui(Player p) {
	add(new Gui(p));
}
}
