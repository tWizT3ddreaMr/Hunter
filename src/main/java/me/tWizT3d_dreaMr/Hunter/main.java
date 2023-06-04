package me.tWizT3d_dreaMr.Hunter;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

import me.tWizT3d_dreaMr.Hunter.Listeners.BlockInteractionListener;
import me.tWizT3d_dreaMr.Hunter.Objects.ConfigHandlerObject;

public class main extends JavaPlugin{
	public static ArrayList<Player> PSetCont=new ArrayList<>();
	public static ArrayList<Player> PSetInv=new ArrayList<>();
private static Plugin plugin;
private static ConfigHandlerObject MainCon;
private static ConfigHandlerObject LangCon;
private static ConfigHandlerObject ContCon;
private static ConfigHandlerObject ItemCon;
private static ConfigHandlerObject InvCon;
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
public static void SAVE(YamlConfiguration config, int cv) {
	if(cv==1)
		MainCon.SaveConfig(config);
	if(cv==2)
		ContCon.SaveConfig(config);
	if(cv==3)
		ItemCon.SaveConfig(config);
	if(cv==4)
		InvCon.SaveConfig(config);
}
public List<String> onTabComplete(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
    if (!cmd.getName().equalsIgnoreCase("hunter")) return null;
    ArrayList<String> ret = new ArrayList<String>();
    boolean z=false;
    String s="empty";
    if(args.length==0) 
    	z=true;
    else
    s=args[0];
    if(z || "con".startsWith(s))
    	ret.add("con");
    if(z || "coff".startsWith(s))
    	ret.add("coff");
    if(z || "ion".startsWith(s))
    	ret.add("ion");
    if(z || "ioff".startsWith(s))
    	ret.add("ioff");
    if(z || "additem".startsWith(s))
    	ret.add("additem");
    if(z || "reload".startsWith(s))
    	ret.add("reload");
    if(z || "setup".startsWith(s))
    	ret.add("setup");
    if(z || "help".startsWith(s))
    	ret.add("help");
    	
    return ret;
    }

public static void LOAD() {
	MainCon=new ConfigHandlerObject("config");
	LangCon=new ConfigHandlerObject("Lang");
	ContCon=new ConfigHandlerObject("Container");
	ItemCon=new ConfigHandlerObject("Items");
	InvCon=new ConfigHandlerObject("Inventories");
	
	Configurator.Init(MainCon.getConfig(), ContCon.getConfig(), ItemCon.getConfig(), InvCon.getConfig());
	LangHandler.Init(LangCon.getConfig());
	Configurator.setUp();
}

public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
if (!command.getName().equalsIgnoreCase("Hunter"))
	return false;
label="help";
if(args.length==0) {
	Commander.help(sender);
	return true;
}
label=args[0].toLowerCase();
if(!sender.hasPermission("Hunter."+label)) {
	sender.sendMessage(LangHandler.getMessage("Main.NoPermission").replace("%args%", label));
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
				sender.sendMessage(LangHandler.getMessage("Main.NotPlayer"));
		break;
	case "coff":
		if (sender instanceof Player) {
			Commander.removeFromCList((Player)sender); //
			}else
				sender.sendMessage(LangHandler.getMessage("Main.NotPlayer"));
		break;
	case "con":
		if (sender instanceof Player) {
			Commander.addToCList((Player)sender); //
			}else
				sender.sendMessage(LangHandler.getMessage("Main.NotPlayer"));
		break;
	case "itoggle":
		if (sender instanceof Player) {
			Commander.toggleIPlayer((Player)sender);
			}else
				sender.sendMessage(LangHandler.getMessage("Main.NotPlayer"));
		break;
	case "ioff":
		if (sender instanceof Player) {
			Commander.removeFromIList((Player)sender); //
			}else
				sender.sendMessage(LangHandler.getMessage("Main.NotPlayer"));
		break;
	case "ion":
		if (sender instanceof Player) {
			Commander.addToIList((Player)sender); //
			}else
				sender.sendMessage(LangHandler.getMessage("Main.NotPlayer"));
		break;
	case "additem":
		if (sender instanceof Player) {
			Commander.setItem((Player)sender);
			}else
				sender.sendMessage(LangHandler.getMessage("Main.NotPlayer"));
		break;
	case "reload":
		Commander.reload();
		sender.sendMessage(LangHandler.getMessage("Main.Reload"));
		break;
	case "setup":
		sender.sendMessage(LangHandler.getMessage("Main.SetUp.Start"));
		Commander.CommandSetUp();
		sender.sendMessage(LangHandler.getMessage("Main.SetUp.End"));
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
