package com.sadmean.mc.RuinsOfIce.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.ItemStack;

import com.sadmean.mc.RuinsOfIce.RuinsOfIce;

public class TreasurePopulator extends BlockPopulator {
	
	public static int chestChance = RuinsOfIce.populator_treasure_chestChance;
	//item chances
	public static int commonChance = RuinsOfIce.populator_treasure_commonChance;
	public static int uncommonChance = RuinsOfIce.populator_treasure_uncommonChance;
	public static int rareChance = RuinsOfIce.populator_treasure_rareChance;
	public static int epicChance = RuinsOfIce.populator_treasure_epicChance;
	//item arrays
	ItemStack[] commonRewards;
	ItemStack[] uncommonRewards;
	ItemStack[] rareRewards;
	ItemStack[] epicRewards;
	//
	ItemStack reward = null;
	
	
	@Override
	public void populate(World world, Random rand, Chunk source) {
		if(rand.nextInt(100) > chestChance ) {
			return;
		}
		ChunkSnapshot snapshot = source.getChunkSnapshot();
		int x = rand.nextInt(15);
		int z = rand.nextInt(15);
		int y = snapshot.getHighestBlockYAt(x, z);

		//build our reward tables
		commonRewards = new ItemStack[10];
		commonRewards[1] = new ItemStack(Material.STONE_AXE, 1);
		commonRewards[2] = new ItemStack(Material.STONE_HOE, 1);
		commonRewards[3] = new ItemStack(Material.STONE_SWORD, 1);
		commonRewards[4] = new ItemStack(Material.STONE_SPADE, 1);
		commonRewards[5] = new ItemStack(Material.IRON_INGOT, 2);
		commonRewards[6] = new ItemStack(Material.STONE_PICKAXE, 1);
		commonRewards[7] = new ItemStack(Material.STONE_AXE, 1);
		commonRewards[8] = new ItemStack(Material.BUCKET, 1);
		commonRewards[9] = new ItemStack(Material.COAL, rand.nextInt(3) + 1);
		commonRewards[0] = new ItemStack(Material.COOKED_BEEF, rand.nextInt(1) + 1);
		
		uncommonRewards = new ItemStack[10];
		uncommonRewards[1] = new ItemStack(Material.IRON_AXE, 1);
		uncommonRewards[2] = new ItemStack(Material.IRON_HOE, 1);
		uncommonRewards[3] = new ItemStack(Material.IRON_SWORD, 1);
		uncommonRewards[4] = new ItemStack(Material.IRON_SPADE, 1);
		uncommonRewards[5] = new ItemStack(Material.IRON_INGOT, rand.nextInt(4) + 1);
		uncommonRewards[6] = new ItemStack(Material.IRON_PICKAXE, 1);
		uncommonRewards[7] = new ItemStack(Material.IRON_AXE, 1);
		uncommonRewards[8] = new ItemStack(Material.BOOK, rand.nextInt(2) + 1);
		uncommonRewards[9] = new ItemStack(Material.COAL, rand.nextInt(7) + 1);
		uncommonRewards[0] = new ItemStack(Material.ARROW, rand.nextInt(4) + 1);
		
		rareRewards = new ItemStack[10];
		rareRewards[0] = new ItemStack(Material.BOW, 1);
		rareRewards[1] = new ItemStack(Material.DIAMOND_AXE, 1);
		rareRewards[2] = new ItemStack(Material.DIAMOND_HOE, 1);
		rareRewards[3] = new ItemStack(Material.DIAMOND_SWORD, 1);
		rareRewards[4] = new ItemStack(Material.DIAMOND_SPADE, 1);
		rareRewards[5] = new ItemStack(Material.IRON_BLOCK, rand.nextInt(1) + 1);
		rareRewards[6] = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		rareRewards[7] = new ItemStack(Material.DIAMOND_AXE, 1);
		rareRewards[8] = new ItemStack(Material.BOOKSHELF, rand.nextInt(2) + 1);
		rareRewards[9] = new ItemStack(Material.EYE_OF_ENDER, 1);;
		
		epicRewards = new ItemStack[11];
		epicRewards[1] = new ItemStack(Material.SPONGE, 1);
		epicRewards[2] = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
		epicRewards[3] = new ItemStack(Material.CHAINMAIL_HELMET, 1);
		epicRewards[4] = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
		epicRewards[5] = new ItemStack(Material.IRON_BLOCK, rand.nextInt(8) + 3);
		epicRewards[6] = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
		epicRewards[7] = new ItemStack(Material.GLOWSTONE, rand.nextInt(3) + 1);
		epicRewards[8] = new ItemStack(Material.FIRE, rand.nextInt(2) + 1);
		epicRewards[9] = new ItemStack(Material.EYE_OF_ENDER, 1);
		epicRewards[0] = new ItemStack(Material.BEDROCK, 1);
		
		epicRewards[10] = new ItemStack(Material.DIAMOND_SWORD, 1);
		epicRewards[10].addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
		
		//determine level of reward
		int chance = rand.nextInt(100);
		if(chance <= commonChance) {
			reward = commonRewards[rand.nextInt(9)];
			
			if(chance <= uncommonChance) {
				reward = uncommonRewards[rand.nextInt(9)];
				
				if(chance <= rareChance) {
					reward = rareRewards[rand.nextInt(9)];
					
					if(chance <= epicChance) {
						//epic reward
						reward = epicRewards[rand.nextInt(10)];
					}
					
				}
				
			}
			
		}
		
		
		Block block = source.getBlock(x, y, z);
		block.setType(Material.CHEST);
		Chest chest = (Chest) block.getState();
		chest.getInventory().addItem(reward);
	}

	
}
