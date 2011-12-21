package com.sadmean.mc.RuinsOfIce.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

//THIS CLASS BORROWED FROM BANANAMAZE. THANKS BANANA!! (I didn't ask, does that make me a bad person. YES YES IT DOES)
public class RuinsPopulator extends BlockPopulator {
	public static final int MAX_RUINS = 3;
	public static final int RUINS_CHANCE = 60;
	public static final Material RUINS_MATERIAL = Material.ICE;
	public static final BlockFace[] directions = new BlockFace[] {
			BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST };

	@Override
	public void populate(World world, Random random, Chunk source) {
		ChunkSnapshot snapshot = source.getChunkSnapshot();
		int ruins = 0;
		while (random.nextInt(100) < RUINS_CHANCE && ruins < MAX_RUINS) {
			int startX = random.nextInt(14) + 1;
			int startZ = random.nextInt(14) + 1;
			int startY = snapshot.getHighestBlockYAt(startX, startZ);
			//int startY = MazeImproved.floorHeight;
			int startHeight = random.nextInt(5) + 1;

			BlockFace direction1 = directions[random.nextInt(directions.length)];
			BlockFace direction2 = directions[random.nextInt(directions.length)];

			int height = startHeight;
			int x = startX;
			int z = startZ;
			while (height > 0 && 0 <= x && x < 16 && 0 <= z && z < 16) {
				for (int y = startY; y < startY + height; y++) {
					source.getBlock(x, y, z).setType(RUINS_MATERIAL);
				}

				height -= random.nextInt(3);

				x += direction1.getModX();
				z += direction1.getModZ();
			}

			if (direction1 != direction2) {
				height = startHeight;
				x = startX;
				z = startZ;
				while (height > 0 && 0 <= x && x < 16 && 0 <= z && z < 16) {
					for (int y = startY; y < startY + height; y++) {
						source.getBlock(x, y, z).setType(RUINS_MATERIAL);
					}

					height -= random.nextInt(3);

					x += direction2.getModX();
					z += direction2.getModZ();
				}
			}

			ruins++;
		}
	}
}
