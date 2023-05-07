package me.tWizT3d_dreaMr.Hunter;
import java.util.Random;
import java.util.ArrayList;

import me.tWizT3d_dreaMr.Hunter.Objects.HuntContainer;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;

public class Configurator {
	public static FileConfiguration config;
	public static ArrayList<String> containers=new ArrayList<>();
	public static ArrayList<HuntContainer> hunt=new ArrayList<>();
	
	public static void Init(FileConfiguration conf) {
		config=conf;
	}
	public static void setUp(String name) {
		containers.addAll(config.getConfigurationSection("Containers."+name).getKeys(false));
	}
	public static void addContainer(Block b, String group) {
		
	}
	private static String getUUID() {
	String characters ="abcdefghijklmnopqrstuvwxyz1234567890";
	int count=0;
	String UUID="";
	while(count!=10){
		int r=random(characters.length());
			if(r!=characters.length())
				UUID=UUID+characters.substring(r,r+1);
			else
				UUID=UUID+characters.substring(r);
	count++;	
	}
	
	return UUID;
		
		
	}
	public static int random(int x){
		Random randomGenerator = new Random();
		int	rand = randomGenerator.nextInt((x+1));
	    return rand;
	}
}
