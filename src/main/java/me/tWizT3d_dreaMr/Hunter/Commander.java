package me.tWizT3d_dreaMr.Hunter;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class Commander {
public static void CommandSetUp(){
	Configurator.SetEm();
}
public static void toggleCPlayer(Player p) {
	if(main.PSetCont.contains(p)) removeFromCList(p);
	else addToCList(p);
}
public static void addToCList(Player p) {
	p.sendMessage(ChatColor.GREEN+"Added to Container set list");
	main.PSetCont.add(p);
}
public static void removeFromCList(Player p) {
	p.sendMessage(ChatColor.RED+"Removed from Container set list");
	main.PSetCont.remove(p);
}
public static void toggleIPlayer(Player p) {
	if(main.PSetInv.contains(p)) removeFromIList(p);
	else addToIList(p);
}
public static void addToIList(Player p) {
	p.sendMessage(ChatColor.GREEN+"Added to Inventory set list");
	main.PSetInv.add(p);
}
public static void removeFromIList(Player p) {
	p.sendMessage(ChatColor.RED+"Removed from Inventory set list");
	main.PSetInv.remove(p);
}
public static void setItem(Player p) {
	ItemStack i=p.getInventory().getItemInMainHand();
	if(i == null||i.getType()==Material.AIR) return;
	p.sendMessage(ChatColor.GREEN+"Added to item list");
	Configurator.addItem(i);
	
}
public static void help(CommandSender sender) {
	sender.sendMessage(ChatColor.GRAY+"~~~~Help Menu~~~~");
	sender.sendMessage(ChatColor.of("#ff4500")+"Containers");
	sender.sendMessage(ChatColor.of("#FFD68A")+"/Hunter ctoggle");
	sender.sendMessage(ChatColor.of("#FFA500")+"Toggles container set mode");
	sender.sendMessage(ChatColor.of("#FFD68A")+"/Hunter con");
	sender.sendMessage(ChatColor.of("#FFA500")+"Turns on container set mode");
	sender.sendMessage(ChatColor.of("#FFD68A")+"/Hunter coff");
	sender.sendMessage(ChatColor.of("#FFA500")+"Turns off container set mode");
	sender.sendMessage(ChatColor.of("#ff4500")+"");
	sender.sendMessage(ChatColor.of("#ff4500")+"Inventories");
	sender.sendMessage(ChatColor.of("#FFD68A")+"/Hunter itoggle");
	sender.sendMessage(ChatColor.of("#FFA500")+"Toggles inventory set mode");
	sender.sendMessage(ChatColor.of("#FFD68A")+"/Hunter ion");
	sender.sendMessage(ChatColor.of("#FFA500")+"Turns on inventory set mode");
	sender.sendMessage(ChatColor.of("#FFD68A")+"/Hunter ioff");
	sender.sendMessage(ChatColor.of("#FFA500")+"Turns off inventory set mode");	
	sender.sendMessage(ChatColor.of("#FFD68A")+"/Hunter addItem");
	sender.sendMessage(ChatColor.of("#FFA500")+"Adds item to itemlist(Population method 2)");	
	sender.sendMessage(ChatColor.of("#ff4500")+"");
	sender.sendMessage(ChatColor.of("#ff4500")+"Utilities");
	sender.sendMessage(ChatColor.of("#FFD68A")+"/Hunter reload");
	sender.sendMessage(ChatColor.of("#FFA500")+"Reloads hunter's config");
	sender.sendMessage(ChatColor.of("#FFD68A")+"/Hunter setup");
	sender.sendMessage(ChatColor.of("#FFA500")+"Sets chest and populates the chests");
	sender.sendMessage(ChatColor.of("#FFD68A")+"/Hunter help");
	sender.sendMessage(ChatColor.of("#FFA500")+"Opens this menu");
}
public static void reload() {
	main.LOAD();
	
}
}
