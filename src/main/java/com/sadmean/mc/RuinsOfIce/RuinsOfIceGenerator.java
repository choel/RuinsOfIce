package com.sadmean.mc.RuinsOfIce;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import com.sadmean.mc.RuinsOfIce.populators.ChestPopulator;
import com.sadmean.mc.RuinsOfIce.populators.IceFortressRuinsPopulator;
import com.sadmean.mc.RuinsOfIce.populators.OrePopulator;
import com.sadmean.mc.RuinsOfIce.populators.RuinsPopulator;
import com.sadmean.mc.RuinsOfIce.populators.SnowmanPopulator;
import com.sadmean.mc.RuinsOfIce.populators.TreasurePopulator;
import com.sadmean.mc.RuinsOfIce.populators.TreePopulator;
import com.sadmean.mc.RuinsOfIce.populators.SnowPopulator;

public class RuinsOfIceGenerator extends ChunkGenerator {

	 @Override
	 public List<BlockPopulator> getDefaultPopulators(World world) {
		 return Arrays.asList(new TreePopulator(), new SnowmanPopulator(),
				 new OrePopulator(), new RuinsPopulator(), 
				 new IceFortressRuinsPopulator(), new TreasurePopulator(), new SnowPopulator());
	 }
	
	
	@Override
	public byte[] generate(World world, Random rand, int Source_x, int Source_z) {
		byte[] result = new byte[32768];

		//set 1 to 25 to stone
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 1; y <= 25; y++) {
					result[xyzToByte(x, y, z)] = (byte) Material.STONE.getId();
				}
			}
		}
		
		//set 26 to 38 to (dirt or stone random)
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int ran = rand.nextInt(3);
				if(ran != 3) {
					for (int y = 26; y <= 38; y++) {
						result[xyzToByte(x, y, z)] = (byte) Material.DIRT.getId();
					}
				} else {
					for (int y = 26; y <= 38; y++) {
						result[xyzToByte(x, y, z)] = (byte) Material.STONE.getId();
					}
				}
			}
		}
		
		//set 39 to 45 to (dirt or gravel random)
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int ran = rand.nextInt(3);
				if(ran != 3) {
					for (int y = 39; y <= 45; y++) {
						result[xyzToByte(x, y, z)] = (byte) Material.DIRT.getId();
					}
				} else {
					for (int y = 39; y <= 45; y++) {
						result[xyzToByte(x, y, z)] = (byte) Material.GRAVEL.getId();
					}
				}
			}
		}
		
		//set 46 to 47 to dirt
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 46; y <= 47; y++) {
					result[xyzToByte(x, y, z)] = (byte) Material.DIRT.getId();
				}
			}
		}
				
		//set 48 to 64 to snow
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 48; y <= 64; y++) {
					result[xyzToByte(x, y, z)] = (byte) Material.SNOW_BLOCK.getId();
				}
			}
		}
		
		//set layer 65 to snow (not block)
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				result[xyzToByte(x, 65, z)] = (byte) Material.SNOW.getId();
			}
		}
		
		// Set the lowest layer to bedrock
		if(RuinsOfIce.BedrockFloor) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					result[xyzToByte(x, 0, z)] = (byte) Material.BEDROCK.getId();
				}
			}
		}
		return result;
	}

	
	
	 /**
	 * This needs to be set to return true to override minecraft's default
	 * behaviour.
	 */
	 @Override
	 public boolean canSpawn(World world, int x, int z) {
		 return true;
	 }
	 
	 @Override
	 public Location getFixedSpawnLocation(World world, Random random) {
		 return new Location(world, 8, 20, 8);
	 }

	 /**
	 * This converts relative chunk locations to bytes that can be written to
	 * the chunk
	 */
	 public int xyzToByte(int x, int y, int z) {
		 return (x * 16 + z) * 128 + y;
	 }
}
