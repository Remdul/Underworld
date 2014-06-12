package com.undergroundminer.remdul.underworld;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class util
{
	public static Block findOpenBlock(int x, int z, World world)
	{
		Block current = world.getBlockAt(x, 0, z);
		Block last = current;

		for (int y = 1; y < world.getMaxHeight(); y++)
		{
			current = world.getBlockAt(x, y, z);
			if (current.getType() == Material.AIR
					&& last.getType() == Material.STONE)
				return last;
			last = current;
		}
		return null;
	}
	public static Block findDungeonBlock(int x, int z, World world)
	{
		Block current = world.getBlockAt(x, 0, z);
		Block last = current;

		for (int y = 1; y < world.getMaxHeight(); y++)
		{
			current = world.getBlockAt(x, y, z);
			if (current.getType() == Material.STONE
					&& last.getType() == Material.STONE
					&& current.getRelative(BlockFace.UP, 1).getType() == Material.STONE
					&& current.getRelative(BlockFace.UP, 2).getType() == Material.STONE
					&& current.getRelative(BlockFace.UP, 3).getType() == Material.STONE
					&& current.getRelative(BlockFace.UP, 4).getType() == Material.STONE
					&& current.getRelative(BlockFace.UP, 5).getType() == Material.STONE
					&& current.getRelative(BlockFace.EAST, 1).getType() == Material.STONE
					&& current.getRelative(BlockFace.EAST, 2).getType() == Material.STONE
					&& current.getRelative(BlockFace.EAST, 3).getType() == Material.STONE
					&& current.getRelative(BlockFace.EAST, 4).getType() == Material.STONE
					&& current.getRelative(BlockFace.EAST, 5).getType() == Material.STONE
					&& current.getRelative(BlockFace.WEST, 1).getType() == Material.STONE
					&& current.getRelative(BlockFace.WEST, 2).getType() == Material.STONE
					&& current.getRelative(BlockFace.WEST, 3).getType() == Material.STONE
					&& current.getRelative(BlockFace.WEST, 4).getType() == Material.STONE
					&& current.getRelative(BlockFace.WEST, 5).getType() == Material.STONE
					&& current.getRelative(BlockFace.SOUTH, 1).getType() == Material.STONE
					&& current.getRelative(BlockFace.SOUTH, 2).getType() == Material.STONE
					&& current.getRelative(BlockFace.SOUTH, 3).getType() == Material.STONE
					&& current.getRelative(BlockFace.SOUTH, 4).getType() == Material.STONE
					&& current.getRelative(BlockFace.SOUTH, 5).getType() == Material.STONE
					&& current.getRelative(BlockFace.NORTH, 1).getType() == Material.STONE
					&& current.getRelative(BlockFace.NORTH, 2).getType() == Material.STONE
					&& current.getRelative(BlockFace.NORTH, 3).getType() == Material.STONE
					&& current.getRelative(BlockFace.NORTH, 4).getType() == Material.STONE
					&& current.getRelative(BlockFace.NORTH, 5).getType() == Material.STONE)


				
				return last;
			last = current;
		}
		return null;
	}

}
