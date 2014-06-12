package com.undergroundminer.remdul.underworld;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class GlowstonePopulator extends BlockPopulator
{

	@Override
	public void populate(World arg0, Random rand, Chunk arg2)
	{
		for (int var7 = 0; var7 < 8; ++var7)
		{
			int var8 = rand.nextInt(15);
			int var9 = rand.nextInt(250) + 4;
			int var10 = rand.nextInt(15);
			generate(arg2, rand, var8, var9, var10);
		}
	}

	@SuppressWarnings("deprecation")
	public boolean generate(Chunk par1World, Random par2Random, int par3,
			int par4, int par5)
	{
		if (!(par1World.getBlock(par3, par4, par5).getType() == Material.AIR))
		{
			return false;
		}
		else if (par1World.getBlock(par3, par4 + 1, par5).getType() != Material.STONE)
		{
			return false;
		}
		else
		{
			par1World.getBlock(par3, par4, par5).setType(Material.GLOWSTONE);

			for (int var6 = 0; var6 < 1500; ++var6)
			{
				int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
				int var8 = par4 - par2Random.nextInt(12);
				int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

				if (par1World.getBlock(var7, var8, var9).getTypeId() == 0)
				{
					int var10 = 0;

					for (int var11 = 0; var11 < 6; ++var11)
					{
						int var12 = 0;

						if (var11 == 0)
						{
							var12 = par1World.getBlock(var7 - 1, var8, var9)
									.getTypeId();
						}

						if (var11 == 1)
						{
							var12 = par1World.getBlock(var7 + 1, var8, var9)
									.getTypeId();
						}

						if (var11 == 2)
						{
							var12 = par1World.getBlock(var7, var8 - 1, var9)
									.getTypeId();
						}

						if (var11 == 3)
						{
							var12 = par1World.getBlock(var7, var8 + 1, var9)
									.getTypeId();
						}

						if (var11 == 4)
						{
							var12 = par1World.getBlock(var7, var8, var9 - 1)
									.getTypeId();
						}

						if (var11 == 5)
						{
							var12 = par1World.getBlock(var7, var8, var9 + 1)
									.getTypeId();
						}

						if (var12 == Material.GLOWSTONE.getId())
						{
							++var10;
						}
					}

					if (var10 == 1)
					{
						par1World.getBlock(var7, var8, var9).setType(
								Material.GLOWSTONE);
					}
				}
			}

			return true;
		}
	}
}