package com.sadmean.mc.RuinsOfIce.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
//import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import com.sadmean.mc.RuinsOfIce.RuinsOfIce;

public class TreePopulator extends BlockPopulator {
	
	int bushChance = RuinsOfIce.populator_tree_bushChance;
	
	//no longer used, gonna go with world wide ruins instead
	@Override
	public void populate(World world, Random rand, Chunk source) {
	
		ChunkSnapshot Snapshot = source.getChunkSnapshot();
		
		if(bushChance > rand.nextInt(100)) {
			int x = rand.nextInt(15) + 1;
			int z = rand.nextInt(15) + 1;
			int y = Snapshot.getHighestBlockYAt(x, z) + 1;
			Block block = source.getBlock(x, y, z);
			block.setType(Material.LEAVES);
		}

	}

}
