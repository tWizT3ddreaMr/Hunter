package me.tWizT3d_dreaMr.Hunter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.block.data.Directional;
import java.util.ArrayList;

import me.tWizT3d_dreaMr.Hunter.Objects.HuntContainer;
import me.tWizT3d_dreaMr.Hunter.Objects.HunterInventories;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class Configurator {
	public static YamlConfiguration config;
	public static ArrayList<String> containers=new ArrayList<>();
	public static ArrayList<String> invs=new ArrayList<>();
	public static ArrayList<HuntContainer> hunt=new ArrayList<>();
	public static ArrayList<HunterInventories> invItems=new ArrayList<>();
	
	public static void Init(YamlConfiguration conf) {
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
		if(config.contains("Inventories")) {
			invs.addAll(config.getConfigurationSection("Inventories").getKeys(false));
			for(String s:invs) {
				ItemStack items[]=new ItemStack[27];
				String path="Inventories."+s+".Items.";
				double d= config.getDouble("Inventories."+s+".Percentage");
				for(int in=0; in<27;in++) {
					if(config.contains(path+in)) {
						ItemStack i= config.getItemStack(path+in);
						items[in]=i;
					}
				}
				HunterInventories hi=new HunterInventories(d, s, items);
				invItems.add(hi);
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
		config.set("Containers."+UUID+".Percentage", 100.0);
		config.set("Containers."+UUID+".Facing", ((Directional) b.getBlockData()).getFacing().name());
		HuntContainer huntC=new HuntContainer(b.getLocation(), UUID, b.getType(), b.getBlockData(), 100.0);
		hunt.add(huntC);
		containers.add(UUID);
		main.SAVE(config);
		
		
	}
	public static void addHuntInventory(HunterInventories HI) {
		String UUID= HI.getID();
		//TODO setup
		ItemStack items[]=HI.getItems(); 
		int in=0;
		for(ItemStack i: items) {
			config.set("Inventories."+UUID+".Items."+in , i);
			in++;
		}
		config.set("Inventories."+UUID+".Percentage." , 100.0);
		invItems.add(HI);
		invs.add(UUID);
		containers.add(UUID);
		main.SAVE(config);
		
	}
	public static String getUUID() {
	String characters ="abcdefghijklmnopqrstuvwxyz1234567890";
	int count=0;
	StringBuilder UUID= new StringBuilder();
	while(count!=10){
		int r=random(characters.length());
			if(r!=characters.length())
				UUID.append(characters.charAt(r));
			else
				UUID.append(characters.substring(r));
	count++;	
	}
	
	return UUID.toString();
		
		
	}
	public static int random(int x){
		Random randomGenerator = new Random();
	    return randomGenerator.nextInt((x+1));
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
