package com.undergroundminer.remdul.underworld;
import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

public class TreePopulator extends BlockPopulator
{

	@Override
	public void populate(World w, Random rand, Chunk src)
	{
/*		int numtrees = rand.nextInt(UnderworldGen.p.config.getFile().getInt(
				"max-trees-per-chunk")
				- UnderworldGen.p.config.getFile().getInt("min-trees-per-chunk")
				+ 1)
				+ UnderworldGen.p.config.getFile().getInt("min-trees-per-chunk");*/
		int maxtreesperchunk = 10;
		int mintreesperchunk = 0;
		
		int numtrees = rand.nextInt(maxtreesperchunk - mintreesperchunk);
		for (int i = 0; i <= numtrees; i++)
		{
			int xpos = rand.nextInt(16);
			int zpos = rand.nextInt(16);
			int real_x = xpos + src.getX() * 16;
			int real_z = zpos + src.getZ() * 16;
			Block placetree = util.findOpenBlock(real_x, real_z, w);	
			if (placetree == null)
			{
				break;
			}
			Location treepos = placetree.getRelative(BlockFace.UP, 1).getLocation();
			int type = rand.nextInt(10);
			if (type > 2)
			{
				placetree.setType(Material.DIRT);
				w.generateTree(treepos, TreeType.BIG_TREE);
			}
			else
			{
				placetree.setType(Material.DIRT);
				w.generateTree(treepos, TreeType.DARK_OAK);

			}
		}
	}
}