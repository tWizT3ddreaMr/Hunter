package me.tWizT3d_dreaMr.Hunter;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import me.tWizT3d_dreaMr.Hunter.Objects.ConfigHandlerObject;
import net.md_5.bungee.api.ChatColor;

public class main extends JavaPlugin{
public static ArrayList<Player> PSet=new ArrayList<>();
private static Plugin plugin;
private static ConfigHandlerObject con;
@Override
public void onEnable() {
    LOAD();
	plugin=this;
}
public static boolean isOnList(Player p) {
	if(PSet.isEmpty()) return false;
	if(PSet.contains(p)) return true;
	return false;
}
public static void SAVE(YamlConfiguration config) {
	con.SaveConfig(config);
}
public static void LOAD() {
	con=new ConfigHandlerObject("config");
	Configurator.Init(con.getConfig());
	Configurator.setUp();
}
public boolean onCommand(CommandSender sender, Command command, String[] args) {
if (!command.getName().equalsIgnoreCase("Hunter"))
	return false;
	//if (sender instanceof Player) {
	//}, 
String label="help";
if(args.length!=0)
	label=args[0];
	if(!sender.hasPermission("Hunter."+label)) {
		sender.sendMessage(ChatColor.RED+"No permissions for command /hunter "+label);
		return true;
	}
	switch (label) {
		case "help":  
			Commander.help(sender);
			break;
		case "container":  
			if (sender instanceof Player) {
				Commander.togglePlayer((Player)sender);
				}else
					sender.sendMessage(ChatColor.RED+"You cant do that");
			break;
		case "coff":  
			if (sender instanceof Player) {
				Commander.removeFromList((Player)sender); //   
				}else
					sender.sendMessage(ChatColor.RED+"You cant do that");
			break;
		case "con":  
			if (sender instanceof Player) {
				Commander.addToList((Player)sender); //    
				}else
					sender.sendMessage(ChatColor.RED+"You cant do that");
			break;
		case "reward":
			//TODO
			break;
		case "reload":
			Commander.reload();
			break;
		case "setup":
			Commander.CommandSetUp();
			break;
		case "set":
			//TODO
			break;
		default:
			Commander.help(sender);
			break;
			
        }
return true;
}
public static Plugin getPlugin() {
	return plugin;
}
}
