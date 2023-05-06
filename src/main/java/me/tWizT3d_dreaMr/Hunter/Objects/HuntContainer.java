package me.tWizT3d_dreaMr.Hunter.Objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;

public class HuntContainer {
	
private Location location;
private String ID;
private Material material;
private BlockFace facing;



public HuntContainer(Location location, String ID, Material material, BlockData state) {
	Directional dir= (Directional) state;
	facing= dir.getFacing();
	this.ID=ID;
	this.material= material;
	this.location=location;

}

public Block set() {
	location.getBlock().setType(material);
	BlockData data=location.getBlock().getBlockData();
	Directional dir=(Directional) data;
	dir.setFacing(facing);
	data=(BlockData) dir;
	location.getBlock().setBlockData(data);
	
	return location.getBlock();
}
public Location getLocation() {
	return location;
}
public String ID() {
	return ID;
}
}
