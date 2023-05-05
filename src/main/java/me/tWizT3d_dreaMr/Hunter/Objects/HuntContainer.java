package me.tWizT3d_dreaMr.Hunter.Objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Chest;

public class HuntContainer {
	
private Location location;
private String ID;
private Material material;
private BlockFace facing;



public HuntContainer() {
	Block block = null;
	ShulkerBox box = (ShulkerBox)block.getState();
	box.
	box.getData().setData((byte)3);
	box.update(true);
}
}
