package com.sadmean.mc.RuinsOfIce;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class RuinsOfIce extends JavaPlugin {

	//internal
	private static RuinsOfIce thisPlugin = null;
	public static Logger log = Logger.getLogger("Minecraft");
	private static boolean debugLogs = false;
	static String mainDirectory = "plugins/RuinsOfIce"; //plugin directory
	static public File configFile = new File(mainDirectory + File.separator + "config.yml");
	private static final RuinsOfIceGenerator gen = new RuinsOfIceGenerator();
	public static boolean simplemode = false;
	public static List<World> IceWorlds;
	int taskID = -2;
	//settings - to be set by Config.load();
	public static boolean alwaysSnowing = true;
	public static boolean BedrockFloor = true;
	//populator settings
	//treasure settings
	public static boolean populator_treasure_allowUnobtainableItems;
	public static int populator_treasure_chestChance;
	public static int populator_treasure_commonChance;
	public static int populator_treasure_uncommonChance;
	public static int populator_treasure_rareChance;
	public static int populator_treasure_epicChance;
	//ruins settings
	public static int populator_ruins_maxRuins;
	public static int populator_ruins_ruinsChance;
	//plants settings
	public static int populator_tree_bushChance;
	
	public void onDisable() {
		log_It("info", "Disable completed");
		
	}

	public void onEnable() {
		Config.load();
		getServer().getPluginManager().registerEvents(new WorldHandler(), this);
		
		taskID = getThisPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(getThisPlugin(), new Runnable() {
			
			public void run() {
				if(IceWorlds.isEmpty()) {
					return;
				}
				int i = 0;
				while(i < IceWorlds.size()) {
					WorldHandler.UpdateWorld(IceWorlds.get(i));
				}
			}
			
		}, 60L, 300L);
		
		log_It("info", "Enabled finished");
	}
	
	public void onLoad() {
		setThisPlugin(this);
	}
	
	public static RuinsOfIce getThisPlugin() {
		return thisPlugin;
	}
	
	private static void setThisPlugin(final RuinsOfIce plugin) {
		RuinsOfIce.thisPlugin = plugin;
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return gen;
	}
	
	//don't care
	public static void log_It(String message) {
		//converts 1 string log_it to a 2 string log it. Fixes leftovers.
		String level = "undefined";
		log_It("warning", "this message's priority was not properly set");
		log_It(level, message);
	}
	
	/**
	 * @param level
	 * @param message
	 */
	public static void log_It(String level, String message) {
		PluginDescriptionFile thisYAML = getThisPlugin().getDescription();
		String pluginName = thisYAML.getName();
		String pluginVersion = thisYAML.getVersion();
		String fullName = "[" + pluginName + "][" + pluginVersion + "] ";
		//convert our level into an int for logging
		int level_int = 6;
		
		if(level == "finest") level_int = 0;
		if(level == "finer") level_int = 1;
		if(level == "fine") level_int = 2;
		if(level == "info") level_int = 3;
		if(level == "warning") level_int = 4;
		if(level == "severe") level_int = 5;
		if(level == "undefined") level_int = 6;
		
	
		switch (level_int) {
		case 0: if(debugLogs) log.finest(fullName + message); break; //for people who like logs in the hexabytes
		case 1: if(debugLogs) log.finer(fullName + message); break; //for people who like log file sizes in the petabytes
		case 2: if(debugLogs) log.fine(fullName + message); break; //for people who like log file sizes in the terabytes
		case 3: log.info(fullName + message); break; //for people who like log file sizes in the gigabytes
		case 4: log.warning(fullName + message); break; //for people who like log file sizes in the megabytes
		case 5: log.severe(fullName + message); break; //for people who like log file sizes in the kilobytes
		case 6: log.warning(fullName + message); break; //for me, 'cause I forgot to specify what level of logging
		default: log.warning(fullName + "warning defaulted, maybe a typo: " + message); //also for me, because I spelled the logging level wrong
			break;
		}
	}

	
	//Iceworlds list getter/setter
	public static void add(World world) {
		if(!IceWorlds.contains(world)) {
			IceWorlds.add(world);
		}
		
	}

	public static boolean contains(World world) {
		if(IceWorlds.contains(world)) {
			return true;
		} else{
			return false;
		}
	}

	public static void remove(World world) {
		if(IceWorlds.contains(world)) {
			IceWorlds.remove(world);
		}
		
	}


}
