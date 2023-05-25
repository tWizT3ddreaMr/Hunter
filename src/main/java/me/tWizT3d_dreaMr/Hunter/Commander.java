package me.tWizT3d_dreaMr.Hunter;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Commander {
public static void CommandSetUp(){
	Configurator.setUp();
}
public static void togglePlayer(Player p) {
	if(main.PSet.contains(p)) removeFromList(p);
	else addToList(p);
}
public static void addToList(Player p) {
	p.sendMessage(ChatColor.GREEN+"Added to list");
	main.PSet.add(p);
}
public static void removeFromList(Player p) {
	p.sendMessage(ChatColor.RED+"Removed from list");
	main.PSet.remove(p);
}
public static void help(CommandSender sender) {
	sender.sendMessage(ChatColor.RED+"Make a help list");
	
}
public static void reload() {
	main.LOAD();
	
}
}
