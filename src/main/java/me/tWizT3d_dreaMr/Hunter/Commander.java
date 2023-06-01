package me.tWizT3d_dreaMr.Hunter;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
	p.sendMessage(ChatColor.GREEN+"Added to list");
	main.PSetCont.add(p);
}
public static void removeFromCList(Player p) {
	p.sendMessage(ChatColor.RED+"Removed from list");
	main.PSetCont.remove(p);
}
public static void toggleIPlayer(Player p) {
	if(main.PSetInv.contains(p)) removeFromIList(p);
	else addToIList(p);
}
public static void addToIList(Player p) {
	p.sendMessage(ChatColor.GREEN+"Added to list");
	main.PSetInv.add(p);
}
public static void removeFromIList(Player p) {
	p.sendMessage(ChatColor.RED+"Removed from list");
	main.PSetInv.remove(p);
}
public static void help(CommandSender sender) {
	sender.sendMessage(ChatColor.RED+"Make a help list");
	
}
public static void reload() {
	main.LOAD();
	
}
}
