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
