package me.tWizT3d_dreaMr.Hunter;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Commander {
public static void CommandSetUp(){
	Configurator.SetEm();
}
public static void toggleCPlayer(Player p) {
	if(main.PSetCont.contains(p)) removeFromCList(p);
	else addToCList(p);
}
public static void addToCList(Player p) {
	LangHandler.sendMessage(p, "Lists.ContainerOn");
	main.PSetCont.add(p);
}
public static void removeFromCList(Player p) {
	LangHandler.sendMessage(p, "Lists.ContainerOff");
	main.PSetCont.remove(p);
}
public static void toggleIPlayer(Player p) {
	if(main.PSetInv.contains(p)) removeFromIList(p);
	else addToIList(p);
}
public static void addToIList(Player p) {
	LangHandler.sendMessage(p, "Lists.InventoryOn");
	main.PSetInv.add(p);
}
public static void removeFromIList(Player p) {
	LangHandler.sendMessage(p, "Lists.InventoryOff");
	main.PSetInv.remove(p);
}
public static void toggleAddAll(Player p) {
	if(main.PSetInv.contains(p)) removeFromAddAll(p);
	else addToAddAll(p);
}
public static void addToAddAll(Player p) {
	LangHandler.sendMessage(p, "Lists.AddAllOn");
	main.PSetInv.add(p);
}
public static void removeFromAddAll(Player p) {
	LangHandler.sendMessage(p, "Lists.AddAllOff");
	main.PSetInv.remove(p);
}
public static void setItem(Player p) {
	ItemStack i=p.getInventory().getItemInMainHand();
	if(i == null||i.getType()==Material.AIR) return;
	LangHandler.sendMessage(p, "Confirmations.Items");
	Configurator.addItem(i);
	
}
public static void help(CommandSender sender) {
	LangHandler.HelpMessage(sender);
}
public static void reload() {
	main.LOAD();
	
}
}
