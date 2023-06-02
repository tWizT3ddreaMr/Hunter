package me.tWizT3d_dreaMr.Hunter.Listeners;

import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.tWizT3d_dreaMr.Hunter.Configurator;
import me.tWizT3d_dreaMr.Hunter.LangHandler;
import me.tWizT3d_dreaMr.Hunter.main;
import me.tWizT3d_dreaMr.Hunter.Items.CommandWorkings;

public class BlockInteractionListener implements Listener {

    @EventHandler
	public void ClickBlockMain(PlayerInteractEvent e) {
        if(e.getClickedBlock()==null) return;
    	ClickBlockContainer(e);
    	ClickBlockInventory(e);
	}
    public void ClickBlockContainer(PlayerInteractEvent e) {
    	Player clicker=e.getPlayer();
    	if(!main.isOnList(clicker,0)) return;


        BlockState state = e.getClickedBlock().getState();
        
        if (!(state instanceof Container)) { 
        	return;
        }
        LangHandler.sendMessage(e.getPlayer(), "Confirmations.Container");
        Configurator.addContainer(e.getClickedBlock());        
    }
    public void ClickBlockInventory(PlayerInteractEvent e) {
    	Player clicker=e.getPlayer();
    	if(!main.isOnList(clicker,1)) return;
    	
        BlockState state = e.getClickedBlock().getState();
        
        if (!(state instanceof Container)) { 
        	return;
        }
        LangHandler.sendMessage(e.getPlayer(), "Confirmations.Inventory");
        CommandWorkings.addInventory(e.getClickedBlock());   
    }
}
