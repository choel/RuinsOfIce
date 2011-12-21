package com.sadmean.mc.RuinsOfIce.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class IceFortressRuinsPopulator extends BlockPopulator {

	public static final int maxPillarHeight = 16;
	public static final int pillarChance = 50;
	public static final int floorChance = 10;
	public static final int minFloorHeight = 10;
	public static final int maxRandFloorHeight = 4;
	
	@Override
	public void populate(World world, Random rand, Chunk source) {
		ChunkSnapshot snapshot = source.getChunkSnapshot();
		int numTimesToRun = rand.nextInt(5) + 2;
		while(numTimesToRun > 0) {
			if(rand.nextInt(100) < pillarChance) {
				boolean canWeHaveAFloor = false;
				int startX = rand.nextInt(14) + 1;
				int startZ = rand.nextInt(14) + 1;
				int startY = snapshot.getHighestBlockYAt(startX, startZ);
				if(startY > 125) {
					//too high
					break;
				}
				//make the pillar
				int height = rand.nextInt(maxPillarHeight);
				
				int floorBonusHeight = rand.nextInt(maxRandFloorHeight);
				if((floorBonusHeight + minFloorHeight) <= height) {
					canWeHaveAFloor = true;
				}
				
				int index = 0;
				while (height > 0) {
					source.getBlock(startX, startY + index, startZ).setType(Material.ICE);
					height--;
					index++;
				}
				
				//floor
				if(canWeHaveAFloor) {
					int floorStartX = rand.nextInt(3);
					int floorStartZ = rand.nextInt(3);
					for (int x = floorStartX; x < 16; x++) {
						for (int z = floorStartZ; z < 16; z++) {
							source.getBlock(x, startY + minFloorHeight + floorBonusHeight, z).setType(Material.ICE);
						}
				
					}
			
				}
		
			}
			numTimesToRun--;
			snapshot = source.getChunkSnapshot();
		}

	}
}
