package me.tWizT3d_dreaMr.Hunter;

import org.bukkit.plugin.java.JavaPlugin;


public class main extends JavaPlugin{
@Override
public void onEnable() {
	Configurator.Init(getConfig());
}
}
