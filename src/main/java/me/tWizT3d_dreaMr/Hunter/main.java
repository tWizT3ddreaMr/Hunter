package me.tWizT3d_dreaMr.Hunter;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.util.ArrayList;

import me.tWizT3d_dreaMr.Hunter.Listeners.BlockInteractionListener;
import me.tWizT3d_dreaMr.Hunter.Objects.ConfigHandlerObject;
import net.md_5.bungee.api.ChatColor;

public class main extends JavaPlugin{
	public static ArrayList<Player> PSetCont=new ArrayList<>();
	public static ArrayList<Player> PSetInv=new ArrayList<>();
private static Plugin plugin;
private static ConfigHandlerObject con;
@Override
public void onEnable() {
	plugin=this;
	Log.startup(false);
    LOAD(); 
	Bukkit.getPluginManager().registerEvents(new BlockInteractionListener(), (Plugin)this);
}
public static boolean isOnList(Player p, int i) {
	/*
	 * 0 is for HuntContainer
	 * 1 is for HunterInventories
	 * coulda used boolean, but I wanted to future-proof this work
	 * in-case I wanted more states
	 */
	if(i==0) {
		if(PSetCont.isEmpty()) return false;
		return PSetCont.contains(p);
	}
	if(i==1) {
		if(PSetInv.isEmpty()) return false;
		return PSetInv.contains(p);
	}
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
if(args.length==0) {
	Commander.help(sender);
	return true;
}
label=args[0];
if(!sender.hasPermission("Hunter."+label)) {
	sender.sendMessage(ChatColor.RED+"No permissions for command /hunter "+label);
	return true;
}
switch (label) {
	case "help":
		Commander.help(sender);
		break;
	case "ctoggle":
		if (sender instanceof Player) {
			Commander.toggleCPlayer((Player)sender);
			}else
				sender.sendMessage(ChatColor.RED+"You cant do that");
		break;
	case "coff":
		if (sender instanceof Player) {
			Commander.removeFromCList((Player)sender); //
			}else
				sender.sendMessage(ChatColor.RED+"You cant do that");
		break;
	case "con":
		if (sender instanceof Player) {
			Commander.addToCList((Player)sender); //
			}else
				sender.sendMessage(ChatColor.RED+"You cant do that");
		break;
	case "itoggle":
		if (sender instanceof Player) {
			Commander.toggleIPlayer((Player)sender);
			}else
				sender.sendMessage(ChatColor.RED+"You cant do that");
		break;
	case "icoff":
		if (sender instanceof Player) {
			Commander.removeFromIList((Player)sender); //
			}else
				sender.sendMessage(ChatColor.RED+"You cant do that");
		break;
	case "ion":
		if (sender instanceof Player) {
			Commander.addToIList((Player)sender); //
			}else
				sender.sendMessage(ChatColor.RED+"You cant do that");
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
