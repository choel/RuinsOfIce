package com.sadmean.mc.RuinsOfIce.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import com.sadmean.mc.RuinsOfIce.RuinsOfIce;

public class OrePopulator extends BlockPopulator {

	@Override
	public void populate(World world, Random rand, Chunk source) {
		// diamonds
		// only on map layers 1 to 18. 
		// Random number of veins, between 0 and 2 (inclusive)
		// random vein size, between 1 and 6 (inclusive)
		
		//first, how many veins?
		int DiamondVeins = rand.nextInt(2);
		//first, decide what layer we start on
		while (DiamondVeins > 0) {
			int layer = rand.nextInt(17) + 1;
			Location target = source.getBlock(rand.nextInt(16), layer, rand.nextInt(16)).getLocation();
			makeAVein(Material.DIAMOND_ORE, rand.nextInt(5) + 1, target, rand);
			DiamondVeins--;
		}
				
		// gold
		// on map between 1 to 32
		// Random number if veins, between 0 and 3 (inclusive)
		// random vein size, between 2 and 9 (inclusive)
		
		//first, how many veins?
		int GoldVeins = rand.nextInt(3);
		//first, decide what layer we start on
		while (GoldVeins > 0) {
			int layer = rand.nextInt(31) + 1;
			Location target = source.getBlock(rand.nextInt(16), layer, rand.nextInt(16)).getLocation();
			makeAVein(Material.GOLD_ORE, rand.nextInt(7) + 2, target, rand);
			GoldVeins--;
		}
				
		// TODO redstone
		// may not bother with redstone
		
		// Iron
		// done twice
		// pass 1:
		// on map between 45 and 61
		// Random number of veins between 1 and 4 (inclusive)
		// random vein size of 2 to 7 (inclusive)
		// pass 2:
		// on map between 2 and 45
		// random number of veins between 1 and 5 (inclusive)
		// random vein size of 2 to 7 (inclusive)
		
		//first, how many veins?
		int Iron_1_Veins = rand.nextInt(3) + 1;
		//first, decide what layer we start on
		while (Iron_1_Veins > 0) {
			int layer = rand.nextInt(16) + 45;
			Location target = source.getBlock(rand.nextInt(16), layer, rand.nextInt(16)).getLocation();
			makeAVein(Material.IRON_ORE, rand.nextInt(5) + 2, target, rand);
			Iron_1_Veins--;
		}
		int Iron_2_Veins = rand.nextInt(5) + 1;
		//first, decide what layer we start on
		while (Iron_2_Veins > 0) {
			int layer = rand.nextInt(43) + 2;
			Location target = source.getBlock(rand.nextInt(16), layer, rand.nextInt(16)).getLocation();
			makeAVein(Material.IRON_ORE, rand.nextInt(6) + 2, target, rand);
			Iron_2_Veins--;
		}
		
		// Coal
		// on map between 2 and 64
		// Random number of veins between 2 and 15 (inclusive)
		// random vein size of 3 to 12 (inclusive)
		
		//first, how many veins?
		int CoalVeins = rand.nextInt(13) + 2;
		//first, decide what layer we start on
		while (CoalVeins > 0) {
			int layer = rand.nextInt(62) + 2;
			Location target = source.getBlock(rand.nextInt(16), layer, rand.nextInt(16)).getLocation();
			makeAVein(Material.COAL_ORE, rand.nextInt(9) + 3, target, rand);
			CoalVeins--;
		}
	}
	
	private void makeAVein(Material type, int size, Location target, Random rand) {
		if(RuinsOfIce.simplemode) {
			return;
		}
		if(target.getBlock().getType() == Material.BEDROCK) {
			target.setY(target.getY() + 1);
			size--;
			makeAVein(type, size, target, rand);
			return;
		} else {
			int randomnum;
			while(size > 0) {
				if(target.getBlock().getType() == Material.BEDROCK) return;
				target.getBlock().setType(type);
				size--;
				randomnum = rand.nextInt(5);
				switch (randomnum) {
				case 0:
					target.setY(target.getY() + 1);
					//makeAVein(type, size, target, rand);
					break;
				case 1:
					target.setX(target.getX() + 1);
					//makeAVein(type, size, target, rand);
					break;
				case 2:
					target.setZ(target.getZ() + 1);
					//makeAVein(type, size, target, rand);
					break;
				case 3:
					target.setZ(target.getZ() - 1);
					//makeAVein(type, size, target, rand);
					break;
				case 4:
					target.setX(target.getX() - 1);
					//makeAVein(type, size, target, rand);
					break;
				case 5:
					target.setY(target.getY() - 1);
					//makeAVein(type, size, target, rand);
					break;
				default:
					break;
				}
			}
			return;
		}
		
		/**Old method. causes serious slowdown. TRY AGAIN
		int randomnum;
		//Make sure target isn't bedrock
		if(target.getBlock().getType() == Material.BEDROCK) {
			target.setY(target.getY() + 1);
			size--;
			makeAVein(type, size, target, rand);
			return;
		} 
		target.getBlock().setType(type);
		size--;
		randomnum = rand.nextInt(5);
		switch (randomnum) {
			case 0:
				target.setY(target.getY() + 1);
				makeAVein(type, size, target, rand);
				break;
			case 1:
				target.setX(target.getX() + 1);
				makeAVein(type, size, target, rand);
				break;
			case 2:
				target.setZ(target.getZ() + 1);
				makeAVein(type, size, target, rand);
				break;
			case 3:
				target.setZ(target.getZ() - 1);
				makeAVein(type, size, target, rand);
				break;
			case 4:
				target.setX(target.getX() - 1);
				makeAVein(type, size, target, rand);
				break;
			case 5:
				target.setY(target.getY() - 1);
				makeAVein(type, size, target, rand);
				break;
			default:
				break;
		}
		return;
		**/
	}

}
