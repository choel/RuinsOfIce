package com.sadmean.mc.RuinsOfIce.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.ItemStack;

//THIS CLASS BORROWED FROM BANANAMAZE. THANKS BANANA!! (I didn't ask, does that make me a bad person. YES YES IT DOES)

/**
 * This class is no longer used. It has been replaced with TreasurePopulator. We will save it, as it might be useful someday.
 * @deprecated
 */
public class ChestPopulator extends BlockPopulator {
	public static final int CHANCE_OF_CHEST = 10;
	public static final Material CHEST_MATERIAL = Material.CHEST;
	public static final Material FLOOR_MATERIAL = Material.ICE;
	public static final Material FLOOR_MATERIAL2 = Material.SNOW;
	public static final Material FLOOR_MATERIAL3 = Material.SNOW_BLOCK;
	public static final int[] CHANCES = new int[] { 5, 40, 70, 99, 100 };
	public static final Material[] REWARD_MATERIALS = new Material[] {
			Material.IRON_PICKAXE, Material.IRON_SWORD, Material.IRON_INGOT,
			Material.STONE_SWORD, Material.GOLD_PICKAXE, Material.GOLD_SWORD,
			Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE,
			Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS,
			Material.IRON_BOOTS, Material.IRON_CHESTPLATE,
			Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.ARROW,
			Material.STRING, Material.BONE, Material.APPLE, Material.COOKIE,
			Material.RAW_FISH, Material.COOKED_FISH, Material.PORK,
			Material.GRILLED_PORK };
	public static final Material[] RARE_REWARDS = new Material[] {
			Material.SPONGE, Material.CHAINMAIL_BOOTS,
			Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET,
			Material.BEDROCK, Material.CHAINMAIL_LEGGINGS,
			Material.HUGE_MUSHROOM_2, Material.FIRE,
			Material.RECORD_5, Material.RECORD_6,
			Material.GOLDEN_APPLE, Material.RED_MUSHROOM, Material.IRON_BLOCK };
	public static final int RARE_REWARD_CHANCE = 20;


	@Override
	public void populate(World world, Random random, Chunk source) {
		if (random.nextInt(100) < CHANCE_OF_CHEST) {
			ChunkSnapshot snapshot = source.getChunkSnapshot();
			int x = 1 + random.nextInt(14);
			int z = 1 + random.nextInt(14);
			//int y = MazeImproved.floorHeight;
			int y = snapshot.getHighestBlockYAt(x, z);
			
			Block block = source.getBlock(x, y, z);
			if (block.getRelative(BlockFace.DOWN).getType() == FLOOR_MATERIAL || block.getRelative(BlockFace.DOWN).getType() == FLOOR_MATERIAL2 || block.getRelative(BlockFace.DOWN).getType() == FLOOR_MATERIAL3) {
				block.setType(CHEST_MATERIAL);
				Chest chest = (Chest) block.getState();

				int count = 0;
				for (int chance : CHANCES) {
					if (random.nextInt(100) < chance) {
						break;
					}
					count++;
				}

				for (; count > 0; count--) {
					Material[] list = random.nextInt(100) < RARE_REWARD_CHANCE ? RARE_REWARDS
							: REWARD_MATERIALS;
					ItemStack item = new ItemStack(list[random.nextInt(list.length)], 1);
					//setup reward amount
					if(item.getType() == Material.BEDROCK) {
						int bedchance = random.nextInt(100);
						if (bedchance < 80) {
							item.setType(Material.RECORD_9);
						}
					}
					if(item.getType() == Material.IRON_BLOCK) {
						item.setAmount(3);
					}
					if(item.getType() == Material.IRON_INGOT) {
						item.setAmount(12);
					}
					chest.getInventory().setItem(
							random.nextInt(chest.getInventory().getSize()),
							item);
				}

				chest.update();
			}
		}
	}
}

