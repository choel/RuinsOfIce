package com.sadmean.mc.RuinsOfIce;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

	
	public static void load() {
		FileConfiguration config;
		config = RuinsOfIce.getThisPlugin().getConfig();
		try {
			config.load(RuinsOfIce.configFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//system config
		
		//populator config
		//Treasure Populator
		if(config.contains("populators.treasure.allowUnobtainableItems")) {
			RuinsOfIce.populator_treasure_allowUnobtainableItems = config.getBoolean("populators.treasure.allowUnobtainableItems", true);
		} else {
			config.set("populators.treasure.allowUnobtainableItems", RuinsOfIce.populator_treasure_allowUnobtainableItems); 
		}
		
		if(config.contains("populators.treasure.chestChance")) {
			RuinsOfIce.populator_treasure_chestChance = config.getInt("populators.treasure.chestChance", 7);
		} else {
			config.set("populators.treasure.chestChance", RuinsOfIce.populator_treasure_chestChance); 
		}
		
		if(config.contains("populators.treasure.commonChance")) {
			RuinsOfIce.populator_treasure_commonChance = config.getInt("populators.treasure.commonChance", 100);
		} else {
			config.set("populators.treasure.commonChance", RuinsOfIce.populator_treasure_commonChance); 
		}
		
		if(config.contains("populators.treasure.uncommonChance")) {
			RuinsOfIce.populator_treasure_uncommonChance = config.getInt("populators.treasure.uncommonChance", 30);
		} else {
			config.set("populators.treasure.uncommonChance", RuinsOfIce.populator_treasure_uncommonChance); 
		}
		
		if(config.contains("populators.treasure.rareChance")) {
			RuinsOfIce.populator_treasure_rareChance = config.getInt("populators.treasure.rareChance", 12);
		} else {
			config.set("populators.treasure.rareChance", RuinsOfIce.populator_treasure_rareChance); 
		}
		
		if(config.contains("populators.treasure.epicChance")) {
			RuinsOfIce.populator_treasure_epicChance = config.getInt("populators.treasure.epicChance", 1);
		} else {
			config.set("populators.treasure.epicChance", RuinsOfIce.populator_treasure_epicChance); 
		}
		
		//Ruins populator
		
		if(config.contains("populators.ruins.maxRuins")) {
			RuinsOfIce.populator_ruins_maxRuins = config.getInt("populators.ruins.maxRuins", 3);
		} else {
			config.set("populators.ruins.maxRuins", RuinsOfIce.populator_ruins_maxRuins); 
		}
		
		if(config.contains("populators.ruins.ruinsChance")) {
			RuinsOfIce.populator_ruins_ruinsChance = config.getInt("populators.ruins.ruinsChance", 60);
		} else {
			config.set("populators.ruins.ruinsChance", RuinsOfIce.populator_ruins_ruinsChance); 
		}
		
		//IceFortressPopulator
		
		
		//world control
		if(config.contains("post.alwaysSnowing")) {
			RuinsOfIce.alwaysSnowing = config.getBoolean("post.alwaysSnowing", true);
		} else {
			config.set("post.alwaysSnowing", RuinsOfIce.alwaysSnowing); 
		}

		

		//save
		try {
			config.save(RuinsOfIce.configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
