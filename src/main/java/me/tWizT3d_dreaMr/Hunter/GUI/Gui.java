package me.tWizT3d_dreaMr.Hunter.GUI;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.tWizT3d_dreaMr.Hunter.Utils;
import net.md_5.bungee.api.ChatColor;

public class Gui {
	private Player p;
	private String name;
	private int page;
	
	private Inventory inv;
	public Gui(Player player) {
		this.p= player;
		this.page= 1;
		this.inv=openGui();
		p.openInventory(inv);
	}
	private Inventory openGui() {
		this.name= Utils.getUUID();
		Inventory inventory=Bukkit.createInventory(p, 54, name);
		
		for(int i=0; i<45; i++) {
			if(Utils.getAllInvItems().size()<i) break;
			inventory.setItem(i, Utils.getAllInvItems().get(i).getReward());
		}
		
		
		inventory.setItem(53, ItemBuilder(Material.RED_STAINED_GLASS_PANE, ChatColor.RED+"Previous Page", null));
		inventory.setItem(54, ItemBuilder(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN+"Next Page", null));
		inventory.setItem(52, ItemBuilder(Material.PAPER, ChatColor.GRAY+"Page", null));
		
		return inventory;
	}
	public ItemStack ItemBuilder(Material mat, String name, ArrayList<String> lore) {
		ItemStack build=new ItemStack(mat);
		ItemMeta meta= build.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		build.setItemMeta(meta);
		
		return build;
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
	private ItemStack getPrice(int i) {
		ItemStack defItem=new ItemStack(main.NotMoney);
		ItemStack item= defItem;
		CurrencyType type=Guis.getCurrencyType();
		if(type==CurrencyType.ITEM) {
			item=new ItemStack(Shop.getPlugin().getItemCurrency().getType());
			if(shop.getPrice() < 65) {
				if(i==15||i==16)
					return defItem;
				item.setAmount((int) shop.getPrice());
				return item;
			}
			if(shop.getPrice() < 129){
				if(i==16)
					return defItem;
				if(i==14) {
					item.setAmount(64);
					ItemMeta meta= item.getItemMeta();
					meta.setDisplayName(ChatColor.GREEN+shop.getPriceString());
					item.setItemMeta(meta);
					return item;
				}
				if(i==15) {
					item.setAmount((int)shop.getPrice()-64);
					ItemMeta meta= item.getItemMeta();
					meta.setDisplayName(ChatColor.GREEN+shop.getPriceString());
					item.setItemMeta(meta);
					return item;
				}
			}
			if(shop.getPrice() >=129){
				if(i==14||i==15) {
					item.setAmount(64);
					ItemMeta meta= item.getItemMeta();
					meta.setDisplayName(ChatColor.GREEN+shop.getPriceString());
					item.setItemMeta(meta);
					return item;
				}
				if(i==16) {
					int left=(int)shop.getPrice()-128;
					if(left>64)
						left=64;
					item.setAmount(left);
					ItemMeta meta= item.getItemMeta();
					meta.setDisplayName(ChatColor.GREEN+shop.getPriceString());
					item.setItemMeta(meta);
					return item;
				}
			}
		}
		if(type==CurrencyType.EXPERIENCE) {
			if(i==15||i==16)
				return defItem;
			item= new ItemStack(Material.EXPERIENCE_BOTTLE);
			ItemMeta meta= item.getItemMeta();
			meta.setDisplayName(ChatColor.AQUA+shop.getPriceString());
			item.setItemMeta(meta);
			return item;
		}
		if(type==CurrencyType.VAULT) {
			if(i==15||i==16)
				return defItem;
			item= new ItemStack(Material.GOLD_INGOT);
			ItemMeta meta= item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD+shop.getPriceString());
			item.setItemMeta(meta);
			return item;
			
		}
		return item;
	}
	public AbstractShop getShop() {
		return shop;
	}
}
