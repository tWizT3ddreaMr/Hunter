package me.tWizT3d_dreaMr.Hunter.Listeners;

import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockInteractionListener {

    @EventHandler
    public void ClickBlock(PlayerInteractEvent e) {
    	Player clicker=e.getPlayer();
    	//TODO
    	//insert check for if player is trying to add to container list
    	
        BlockState state = e.getClickedBlock().getState();
        
        if (!(state instanceof Container)) { 
        	return;
        }
        //I dont actually think i need this but just incase
        //Container container = (Container) state;
        
        //TODO
        //I gotta go setup the structure for what to do
        
    }
}
