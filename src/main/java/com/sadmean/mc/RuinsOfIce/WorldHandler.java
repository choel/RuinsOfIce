package com.sadmean.mc.RuinsOfIce;

import org.bukkit.World;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

/**
 * 
 * @author Choel
 * Modify worlds with our generator to seem more "authentic"
 */
public class WorldHandler implements Listener {

	@EventHandler
	public void onWorldLoad(WorldLoadEvent event) {
		if(event.getWorld().getGenerator() == new RuinsOfIceGenerator()) {
			RuinsOfIce.add(event.getWorld());
		}
	}
	
	@EventHandler
	public void onWorldInit(WorldInitEvent event) {
		if(event.getWorld().getGenerator() == new RuinsOfIceGenerator()) {
			RuinsOfIce.add(event.getWorld());
		}
	}
	
	@EventHandler
	public void onWorldUnload(WorldUnloadEvent event) {
		if(event.getWorld().getGenerator() == new RuinsOfIceGenerator()) {
			RuinsOfIce.remove(event.getWorld());
		}
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if(RuinsOfIce.contains(event.getLocation().getWorld())) {
			event.getEntity().remove();
			Entity ent = event.getLocation().getWorld().spawnCreature(event.getLocation(), CreatureType.SNOWMAN);
			Snowman snowman = (Snowman) ent;
			snowman.setHealth(1000); //dunno why I'm doing this
		}
	}
	
	public static void UpdateWorld(World world) {
		
	}
}
