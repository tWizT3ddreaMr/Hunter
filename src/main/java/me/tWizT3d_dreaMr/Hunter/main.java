package me.tWizT3d_dreaMr.Hunter;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import me.tWizT3d_dreaMr.Hunter.Objects.ConfigHandlerObject;

public class main extends JavaPlugin{
public static ArrayList<Player> PSet=new ArrayList<>();
private static Plugin plugin;
private static ConfigHandlerObject con;
@Override
public void onEnable() {
	con=new ConfigHandlerObject("config");
	Configurator.Init(con.getConfig());
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
public static Plugin getPlugin() {
	return plugin;
}
}
