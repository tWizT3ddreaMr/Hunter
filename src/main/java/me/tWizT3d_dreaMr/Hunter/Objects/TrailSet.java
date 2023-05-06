package me.tWizT3d_dreaMr.Hunter.Objects;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import dev.esophose.playerparticles.particles.ParticleEffect;
import dev.esophose.playerparticles.particles.ParticlePair;
import dev.esophose.playerparticles.styles.ParticleStyle;
import me.tWizT3d_dreaMr.TrailArmor.main;

public class TrailSet {
	//TODO String triggers to TrailSet
	private boolean valid=true;
	private ParticleStyle Style;
	private ParticleEffect Effect;
	private String Name;
public TrailSet(String styleString, String effectString, String name) {
	Name=name.toLowerCase();
	ParticleStyle style=ParticleStyle.fromName(styleString);
	ParticleEffect effect=ParticleEffect.fromName(effectString);
	if(nullCheck(style, effect)) {
		return;
	}
	setObject(style, effect);
}
private boolean nullCheck(ParticleStyle style, ParticleEffect effect) {
	if(style==null || effect==null) {
		valid=false;
		String out=style==null ? "style==null" : "effect==null";
		Bukkit.getLogger().log(Level.WARNING, Name+" "+out);
		return true;
	}
	return false;
}
private void setObject(ParticleStyle style, ParticleEffect effect) {
	Style= style;
	Effect= effect;
}


public boolean isValid() {
	return valid;
}
public String getName() {
	return Name;
}
public ParticleEffect getEffect() {
	return Effect;
}
public ParticleStyle getStyle() {
	return Style;
}
public boolean contains(String lore) {
	return lore.contains(Name);
}
public void setTrail(Player player) {
	if(!valid) {
		Bukkit.getLogger().log(Level.WARNING,"tried to add non-valid trail "+Name);
		return;
	}
	ParticlePair pp= main.getppAPI().addActivePlayerParticle(player, Effect, Style); 
	if(pp==null) {
		Bukkit.getLogger().log(Level.WARNING,"particle pair null cant add "+ Name);
	}
}
}
