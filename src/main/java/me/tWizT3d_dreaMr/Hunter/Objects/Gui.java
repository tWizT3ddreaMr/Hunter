package me.tWizT3d_dreaMr.Hunter.Objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import me.tWizT3d_dreaMr.Hunter.Configurator;
import net.md_5.bungee.api.ChatColor;

public class Gui {
	private Player p;
	private String name;
	private Inventory inv;
	public Gui(Player player) {
		this.p= player;
		this.inv=openGui();
		p.openInventory(inv);
	}
	private Inventory openGui() {
		this.name= Configurator.getUUID();
		Inventory inventory= Bukkit.createInventory(p, 54, name);
		
		for(int i=0; i<45; i++) {
			if(Configurator.invItems.size()<i)
				break;
			InventoryItems invi=Configurator.invItems.get(i);
			//TODO create an item builder for the gui
			ItemStack item=invi.getReward().clone();
			Double Percentage=invi.Percentage();

			inventory.setItem(i,item/*TODO set the built item*/);
			
		}
		ItemStack previous;
		ItemStack next;
		
		inventory.setItem(46, previous);
		inventory.setItem(54, next);
		
		
		return inventory;
	}
	private ItemStack guiItem(InventoryItems item) {
		ItemStack fin=item.getReward().clone();
		ItemMeta meta= fin.getItemMeta();

		List<String> lore= new ArrayList<String>();
		lore.add("Percentage "+item.Percentage());
		//TODO add other important information or instructions
		//TODO i could actually forgo the entire GUI and swap it to import chest layouts, 
		//to have it propperly store the locations
		// or could just copy the inventory
				
		meta.setDisplayName(item.getID());
		meta.setLore(lore);
		fin.setItemMeta(meta);
		return fin;
	}
	public boolean is(InventoryView  test) {
		return test.getTopInventory().getHolder() == null && test.getTitle().equals(name);
	}
	public void update() {
		inv= set13(inv);
	}
	private Inventory set13(Inventory inventory) {
		int amount= shop.getStock();
		ItemStack i;
		if(amount>64)
			i= new ItemStack(main.TransAmount, 64);
		else if(amount==0)
			i= new ItemStack(main.TransOut, 1);
		else
			i= new ItemStack(main.TransAmount, amount);
		ItemMeta meta=i.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA+"This shop has "+ChatColor.WHITE+amount+ChatColor.AQUA+" transactions left.");
		i.setItemMeta(meta);
		inventory.setItem(13, i);
		return inventory;
	}
	private ItemStack getItems(int i) {
		ItemStack item=new ItemStack(main.NotStock);
		
		if(shop.getAmount() < 64) {
			if(i==10||i==11)
				return item;
			return shop.getItemStack();
		}
		if(shop.getAmount() < 129){
			if(i==10)
				return item;
			if(i==11) {
				item=shop.getItemStack();
				item.setAmount(64);
				return item;
			}
			if(i==12) {
				item=shop.getItemStack();
				item.setAmount(shop.getAmount()-64);
				return item;
			}
		}
		if(shop.getAmount() >= 129){
			if(i==10||i==11) {
				item=shop.getItemStack();
				item.setAmount(64);
				return item;
			}
			if(i==12) {
				item=shop.getItemStack();
				int left=shop.getAmount()-128;
				if(left>64)
					left=64;
				item.setAmount(left);
				return item;
			}
		}
		return item;
	}
	public AbstractShop getShop() {
		return shop;
	}
}