package me.tWizT3d_dreaMr.Hunter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.block.data.Directional;
import java.util.ArrayList;

import me.tWizT3d_dreaMr.Hunter.Objects.HuntContainer;
import me.tWizT3d_dreaMr.Hunter.Objects.HunterInventories;
import me.tWizT3d_dreaMr.Hunter.Objects.InventoryItems;
import me.tWizT3d_dreaMr.Hunter.Objects.RandomCollection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class Configurator {
	public static YamlConfiguration MainConfig;
	public static YamlConfiguration ContainerConfig;
	public static YamlConfiguration ItemConfig;
	public static YamlConfiguration InvConfig;
	public static boolean inv;
	public static ArrayList<String> containers=new ArrayList<>();
	public static ArrayList<String> invs=new ArrayList<>();
	public static ArrayList<HuntContainer> hunt=new ArrayList<>();
	public static RandomCollection<HunterInventories> invItems=new RandomCollection<>();
	public static RandomCollection<InventoryItems> items=new RandomCollection<>();
	
	public static void Init(YamlConfiguration m, YamlConfiguration c, YamlConfiguration it, YamlConfiguration iv) {
		MainConfig=m;
		ContainerConfig=c;
		ItemConfig=it;
		InvConfig=iv;
	}
	public static void setUp() {
		containers.clear();
		hunt.clear();
		invs.clear();
		invItems.clear();
		items.clear();
		if(ContainerConfig.contains("Containers")) {
			containers.addAll(ContainerConfig.getConfigurationSection("Containers").getKeys(false));
			for(String s:containers) {
				Location l=ContainerConfig.getLocation("Containers."+s+".Location");
				Material m= Material.valueOf(ContainerConfig.getString("Containers."+s+".Material"));
				BlockFace f= BlockFace.valueOf(ContainerConfig.getString("Containers."+s+".Facing"));
				double d= ContainerConfig.contains("Containers."+s+".Percentage") ? ContainerConfig.getDouble("Containers."+s+".Percentage") : 100.0;
				Material alt=null;
				if(ContainerConfig.contains("Containers."+s+".AltMaterial"))
					alt = Material.valueOf(ContainerConfig.getString("Containers."+s+".AltMaterial"));
				
				HuntContainer h= new HuntContainer(l, s, m, f, d, alt);
				hunt.add(h);
			}
		}
		inv= true;
		
		if(MainConfig.contains("PopulationMethod")) {
			if(MainConfig.getInt("PopulationMethod")==2)
				inv= false;
		} else 
			MainConfig.set("PopulationMethod", 1);
		
		if(inv) {
			if(InvConfig.contains("Inventories")) {
				invs.addAll(InvConfig.getConfigurationSection("Inventories").getKeys(false));
				for(String s:invs) {
					ItemStack items[]=new ItemStack[27];
					String path="Inventories."+s+".Items.";
					double d= InvConfig.getDouble("Inventories."+s+".Percentage");
					for(int in=0; in<27;in++) {
						if(InvConfig.contains(path+in)) {
							ItemStack i= InvConfig.getItemStack(path+in);
							items[in]=i;
						}
					}
					HunterInventories hi=new HunterInventories(d, s, items);
					invItems.add(hi.getPercentage(), hi);
				}
			}
		} else {
			if(ItemConfig.contains("Items")) {
				invs.addAll(ItemConfig.getConfigurationSection("Items").getKeys(false));
				String i="Items.";
				for(String s:invs) {
					double d= ItemConfig.getDouble(i+s+".Percentage");
					ItemStack Reward= ItemConfig.getItemStack(i+s+".Reward");
					InventoryItems in=new InventoryItems(Reward, d, s);
					items.add(d, in);
				}
			}
		}
		
	}
	public static void addContainer(Block b) {
		String UUID= getUUID();
		while(ContainerConfig.contains(UUID)) {
			UUID= getUUID();
		}
		ContainerConfig.set("Containers."+UUID+".Location", b.getLocation());
		ContainerConfig.set("Containers."+UUID+".Material", b.getType().name());
		ContainerConfig.set("Containers."+UUID+".Percentage", 100.0);
		ContainerConfig.set("Containers."+UUID+".Facing", ((Directional) b.getBlockData()).getFacing().name());
		HuntContainer huntC=new HuntContainer(b.getLocation(), UUID, b.getType(), b.getBlockData(), 100.0, null);
		hunt.add(huntC);
		containers.add(UUID);
		main.SAVE(ContainerConfig, 2);
		
		
	}
	public static void addItem(ItemStack i) {
		String UUID= getUUID();
		while(ItemConfig.contains(UUID)) {
			UUID= getUUID();
		}
		String p="Items."+UUID+".";
		ItemConfig.set(p+"Reward", i);
		ItemConfig.set(p+"Percentage", 1.0);
		invs.add(UUID);
		InventoryItems invis=new InventoryItems(i, 1.0, UUID);
		
		items.add(1.0, invis);
		main.SAVE(ItemConfig, 3);
		
		
	}
	public static void addHuntInventory(HunterInventories HI) {
		String UUID= HI.getID();
		//TODO setup
		ItemStack items[]=HI.getItems(); 
		int in=0;
		for(ItemStack i: items) {
			if(i==null)
				InvConfig.set("Inventories."+UUID+".Items."+in , new ItemStack(Material.AIR));
			else
				InvConfig.set("Inventories."+UUID+".Items."+in , i);
			in++;
		}
		InvConfig.set("Inventories."+UUID+".Percentage" , 10.0);
		invItems.add(HI.getPercentage(), HI);
		invs.add(UUID);
		containers.add(UUID);
		main.SAVE(InvConfig, 4);
		
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
			if(b==null) Log.log(Level.INFO, h.getID()+" NotSet");
			else {
				Log.log(Level.INFO, h.getID()+" Set");
				populateChest(b);
			}
		}
	}
	private static void populateChest(Block b) {
		if(!(b.getState() instanceof Container))return;
		Logger Log=Bukkit.getLogger();
		Container c= (Container) b.getState();
		if(inv) {
			HunterInventories hi=invItems.next();
			Log.log(Level.INFO, hi.getID());
			c.getInventory().setContents(hi.getItems());
		} else {
			String build="";
			c.getInventory().clear();
			for(int i=0;i<27;i++) {
				InventoryItems invi=items.next();
				build+= invi.getID()+" ";
				if(invi.getReward()!=null)
					c.getInventory().setItem(i, invi.getReward());
			}
			Log.log(Level.INFO, build);
		}
	}
}
