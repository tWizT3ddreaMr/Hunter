package me.tWizT3d_dreaMr.Hunter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.block.data.Directional;
import java.util.ArrayList;

import me.tWizT3d_dreaMr.Hunter.Objects.HuntContainer;
import me.tWizT3d_dreaMr.Hunter.Objects.InventoryItems;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class Configurator {
	public static FileConfiguration config;
	public static ArrayList<String> containers=new ArrayList<>();
	public static ArrayList<String> items=new ArrayList<>();
	public static ArrayList<HuntContainer> hunt=new ArrayList<>();
	public static ArrayList<InventoryItems> invItems=new ArrayList<>();
	
	public static void Init(FileConfiguration conf) {
		config=conf;
	}
	public static void setUp() {
		containers.clear();
		if(config.contains("Containers")) {
			containers.addAll(config.getConfigurationSection("Containers").getKeys(false));
			for(String s:containers) {
				Location l=config.getLocation("Containers."+s+".Location");
				Material m= Material.valueOf(config.getString("Containers."+s+".Material"));
				BlockFace f= BlockFace.valueOf(config.getString("Containers."+s+".Facing"));
				double d= config.contains("Containers."+s+".Percentage") ? config.getDouble("Containers."+s+".Percentage") : 100.0;
				HuntContainer h= new HuntContainer(l, s, m, f, d);
				hunt.add(h);
			}
		}
		if(config.contains("Items")) {
			items.addAll(config.getConfigurationSection("Items").getKeys(false));
			for(String s:items) {
				ItemStack i= config.getItemStack("Items."+s+".Item");
				Double d= config.getDouble("Items."+s+".Percentage");
				InventoryItems in=new InventoryItems(i, d, s);
				invItems.add(in);
			}
		}
	}
	public static void addContainer(Block b) {
		String UUID= getUUID();
		while(config.contains(UUID)) {
			UUID= getUUID();
		}
		config.set("Containers."+UUID+".Location", b.getLocation());
		config.set("Containers."+UUID+".Material", b.getType().name());
		config.set("Containers."+UUID+".Facing", ((Directional) b.getBlockData()).getFacing().name());
		HuntContainer huntC=new HuntContainer(b.getLocation(), UUID, b.getType(), b.getBlockData(), 100.0);
		hunt.add(huntC);
		containers.add(UUID);
		
		
	}
	public static String getUUID() {
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
	public static void SetEm() {
		Logger Log=Bukkit.getLogger();
		for(HuntContainer h:hunt) {
			Block b=h.set();
			if(b==null) Log.log(Level.INFO, getUUID()+" NotSet");
			else {
				Log.log(Level.INFO, getUUID()+" Set");
				populateChest(b);
			}
		}
	}
	private static void populateChest(Block b) {
		// TODO 
		// Fill the chest, somehow with the stuff I have done already
		
	}
}
