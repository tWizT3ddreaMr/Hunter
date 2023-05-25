package me.tWizT3d_dreaMr.Hunter.Listeners;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import me.tWizT3d_dreaMr.Hunter.Objects.Gui;


public class GuiListener implements Listener {
    
    @EventHandler (ignoreCancelled = true)
    public void InventoryClick(InventoryClickEvent e) {
    	Gui gui= Guis.getGui(e.getView());
    	if(gui == null)
    		return;
    		
    	int i=e.getRawSlot();
    	if(e.getAction()== InventoryAction.MOVE_TO_OTHER_INVENTORY)
	    	if(i==26||i==0||i==18||i==9||i==0||i==17||i==8) {
		    	AbstractShop shop=gui.getShop();
		    	PlayerInteractEvent event= new PlayerInteractEvent((Player)e.getWhoClicked(), Action.LEFT_CLICK_BLOCK,
		    			e.getWhoClicked().getInventory().getItemInMainHand(), shop.getSignLocation().getBlock(),
		    			BlockFace.EAST, EquipmentSlot.HAND);
		    	event.setCancelled(true);
		    	Shop.getPlugin().getTransactionHelper().executeTransactionFromEvent(event, shop, true);
		    	gui.update();
	    	}
    	if(e.getAction()== InventoryAction.PICKUP_ALL)
	    	if(i==26||i==0||i==18||i==9||i==0||i==17||i==8) {
		    	AbstractShop shop=gui.getShop();
		    	PlayerInteractEvent event= new PlayerInteractEvent((Player)e.getWhoClicked(), Action.LEFT_CLICK_BLOCK,
		    			e.getWhoClicked().getInventory().getItemInMainHand(), shop.getSignLocation().getBlock(),
		    			BlockFace.EAST, EquipmentSlot.HAND);
		    	event.setCancelled(true);
		    	Shop.getPlugin().getTransactionHelper().executeTransactionFromEvent(event, shop, false);
		    	gui.update();
	    	}
    	e.setCancelled(true);
    }    @EventHandler (ignoreCancelled = true)
    public void inventoryClose(InventoryCloseEvent e) {
    	Gui gui= Guis.getGui(e.getView());
    	if(gui == null)
    		return;
    	Guis.guilist.remove(gui);
    	
    }
}