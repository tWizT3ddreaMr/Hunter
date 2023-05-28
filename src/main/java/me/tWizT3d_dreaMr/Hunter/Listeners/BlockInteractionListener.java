package me.tWizT3d_dreaMr.Hunter.Listeners;

import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import me.tWizT3d_dreaMr.Hunter.Configurator;
import me.tWizT3d_dreaMr.Hunter.main;

public class BlockInteractionListener {

    @EventHandler
	public void ClickBlockMain(PlayerInteractEvent e) {
    	ClickBlockContainer(e);
    	ClickBlockInventoruy(e);
	}
    public void ClickBlockContainer(PlayerInteractEvent e) {
    	Player clicker=e.getPlayer();
    	if(!main.isOnList(clicker,0)) return;
    	
        BlockState state = e.getClickedBlock().getState();
        
        if (!(state instanceof Container)) { 
        	return;
        }
        Configurator.addContainer(e.getClickedBlock());        
    }
    public void ClickBlockInventoruy(PlayerInteractEvent e) {
    	Player clicker=e.getPlayer();
    	if(!main.isOnList(clicker,1)) return;
    	
        BlockState state = e.getClickedBlock().getState();
        
        if (!(state instanceof Container)) { 
        	return;
        }
        //Configurator.addContainer(e.getClickedBlock());        
    }
}
