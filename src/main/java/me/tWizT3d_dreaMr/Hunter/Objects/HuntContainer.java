package me.tWizT3d_dreaMr.Hunter.Objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;

public class HuntContainer {
	
private final Location location;
private final String ID;
private final Material material;
private final Material alt;
private final BlockFace facing;
private final double Percentage;



public HuntContainer(Location location, String ID, Material material, BlockData state, double Percentage, Material alt) {
	Directional dir= (Directional) state;
	this.alt = alt;
	facing= dir.getFacing();
	this.ID=ID;
	this.material= material;
	this.location=location;
	this.Percentage=Percentage;

}
public HuntContainer(Location location, String ID, Material material, BlockFace facing, double Percentage, Material alt) {
	this.alt = alt;
	this.facing=facing;
	this.ID=ID;
	this.material= material;
	this.location=location;
	this.Percentage=Percentage;

}
public double getPercentage() {
	return Percentage;
}
public Block set() {
	double d=Math.random()*100;
	if(Percentage!=100&&d>Percentage) {
		if(alt != null)
			location.getBlock().setType(alt);
		return null;
	}
	
	location.getBlock().setType(material);
	BlockData data=location.getBlock().getBlockData();
	Directional dir=(Directional) data;
	dir.setFacing(facing);
	location.getBlock().setBlockData(dir);
	
	return location.getBlock();
}
public Location getLocation() {
	return location;
}
public String getID() {
	return ID;
}
}
