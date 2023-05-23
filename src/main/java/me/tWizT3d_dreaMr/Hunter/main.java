package me.tWizT3d_dreaMr.Hunter;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class main extends JavaPlugin{
public static ArrayList<Player> PSet=new ArrayList<>();
@Override
public void onEnable() {
	Configurator.Init(getConfig());
}
public static boolean isOnList(Player p) {
	if(PSet.isEmpty()) return false;
	if(PSet.contains(p)) return true;
	return false;
}
public static void SAVE() {
	
}
public static void LOAD() {

	//Configurator.Init(getConfig());
	//Configurator.setUp();
}
}
