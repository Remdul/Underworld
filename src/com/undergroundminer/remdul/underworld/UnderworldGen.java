package com.undergroundminer.remdul.underworld;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class UnderworldGen extends JavaPlugin
{
	static boolean debug = true;
	static UnderworldGen p;
	public FileAccessor config;
	public void onEnable()
	{
		p = this;
		Logger l = Bukkit.getServer().getLogger();
		l.info("Underworld enabled!");
		config = new FileAccessor(this, "config.yml");
	}
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		return new ChunkGen(Bukkit.getWorld(worldName));
	}
	
}

// Big thanks to xAstraah, NodinChan, meem, and turt2live