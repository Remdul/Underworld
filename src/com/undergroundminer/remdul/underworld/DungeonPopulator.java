package com.undergroundminer.remdul.underworld;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class DungeonPopulator extends BlockPopulator
{

	@Override
	public void populate(World world, Random rand, Chunk chunk)
	{
		if (rand.nextInt(50) == 0)
		{

			int sizex = getRandomOddNumber(rand, 5, 7);
			int sizez = getRandomOddNumber(rand, 5, 7);
			int x = (chunk.getX() * 16) + rand.nextInt(16) - sizex;
			int z = (chunk.getZ() * 16) + rand.nextInt(16) - sizez;

			Block ypre = util.findDungeonBlock(x, z, world);
			if (ypre == null)
			{
				return;
			}
			int y = ypre.getY();
			int chestsLeft = rand.nextInt(2) + 1;
			for (int i = 0; i < sizex + 1; i++)
			{
				for (int o = 0; o < sizez + 1; o++)
				{
					for (int p = 0; p < 5; p++)
					{
						boolean isWall = i == 0 || i == sizex || o == 0
								|| o == sizez;
						boolean isNextToWall = i == 1 || i == sizex - 1
								|| o == 1 || o == sizez - 1;
						boolean isCenter = ((i == 3 && sizex == 5) || (i == 4 && sizex == 7))
								&& ((o == 3 && sizez == 5) || (o == 4 && sizez == 7));
						if (p == 0)
						{
							chunk.getBlock(x + i, y + p, z + o).setType(
									rand.nextInt(3) == 0
											? Material.COBBLESTONE
											: Material.MOSSY_COBBLESTONE);
						}
						else if (p == 1)
						{
							if (isWall && rand.nextInt(3) > 0)
							{
								chunk.getBlock(x + i, y + p, z + o).setType(
										Material.COBBLESTONE);
							}
							else if (isNextToWall && chestsLeft > 0
									&& rand.nextInt(4) == 0)
							{
								chunk.getBlock(x + i, y + p, z + o).setType(
										Material.CHEST);
								popChest(chunk, rand, x + i, y + p, z + o);
							}
							else if (isCenter)
							{
								chunk.getBlock(x + i, y + p, z + o).setType(
										Material.MOB_SPAWNER);
								CreatureSpawner spawner = (CreatureSpawner) chunk
										.getBlock(x + i, y + p, z + o)
										.getState();
								spawner.setSpawnedType(rand.nextBoolean()
										? EntityType.ZOMBIE
										: (rand.nextBoolean()
												? EntityType.SKELETON
												: EntityType.SPIDER));

							}
							else
							{
								chunk.getBlock(x + i, y + p, z + o).setType(
										Material.AIR);
							}
						}
						else
						{
							if (isWall && rand.nextInt(3) > 0)
							{
								chunk.getBlock(x + i, y + p, z + o).setType(
										Material.COBBLESTONE);
							}
							else if (!isWall)
							{
								chunk.getBlock(x + i, y + p, z + o).setType(
										Material.AIR);
							}
						}
					}
				}
			}
		}
	}
	private int getRandomOddNumber(Random rand, int min, int max)
	{
		int randOdd = rand.nextInt((max - min) + 1) + min;
		while (randOdd % 2 == 0)
		{
			randOdd++;
		}
		return randOdd;
	}
	@SuppressWarnings("deprecation")
	private void popChest(Chunk chunk, Random rand, int x, int y, int z)
	{
		Chest c = (Chest) chunk.getBlock(x, y, z).getState();
		int stacks = 8;
		for (int i = 0; i < stacks - 1; i++)
		{
			if (rand.nextInt(11) == 0)
			{
				c.getInventory().setItem(
						rand.nextInt(c.getInventory().getSize()),
						new ItemStack(Material.SADDLE));
			}
			else if (rand.nextInt(11) == 0)
			{
				c.getInventory()
						.setItem(
								rand.nextInt(c.getInventory().getSize()),
								new ItemStack(Material.IRON_INGOT, rand
										.nextInt(4) + 1));
			}
			else if (rand.nextInt(11) == 0)
			{
				c.getInventory().setItem(
						rand.nextInt(c.getInventory().getSize()),
						new ItemStack(Material.BREAD, rand.nextInt(2) + 1));
			}
			else if (rand.nextInt(11) == 0)
			{
				c.getInventory().setItem(
						rand.nextInt(c.getInventory().getSize()),
						new ItemStack(Material.WHEAT, rand.nextInt(4) + 1));
			}
			else if (rand.nextInt(11) == 0)
			{
				c.getInventory().setItem(
						rand.nextInt(c.getInventory().getSize()),
						new ItemStack(Material.SULPHUR, rand.nextInt(4) + 1));
			}
			else if (rand.nextInt(11) == 0)
			{
				c.getInventory().setItem(
						rand.nextInt(c.getInventory().getSize()),
						new ItemStack(Material.STRING, rand.nextInt(4) + 1));
			}
			else if (rand.nextInt(11) == 0)
			{
				c.getInventory().setItem(
						rand.nextInt(c.getInventory().getSize()),
						new ItemStack(Material.BUCKET));
			}
			else if (rand.nextInt(11) == 0)
			{
				c.getInventory().setItem(
						rand.nextInt(c.getInventory().getSize()),
						new ItemStack(Material.INK_SACK, rand.nextInt(3) + 1,
								(short) 3));
			}
			else if (rand.nextInt(22) == 0)
			{
				c.getInventory().setItem(
						rand.nextInt(c.getInventory().getSize()),
						new ItemStack(Material.REDSTONE, rand.nextInt(4) + 1));
			}
			else if (rand.nextInt(110) == 0)
			{
				c.getInventory().setItem(
						rand.nextInt(c.getInventory().getSize()),
						new ItemStack(2256 + rand.nextInt(2)));
			}

			else if (rand.nextInt(11) == 0)
			{
				c.getInventory()
						.setItem(
								rand.nextInt(c.getInventory().getSize()),
								new ItemStack(Material.MELON_SEEDS, rand
										.nextInt(3) + 1));
			}
			else if (rand.nextInt(11) == 0)
			{
				c.getInventory().setItem(
						rand.nextInt(c.getInventory().getSize()),
						new ItemStack(Material.PUMPKIN_SEEDS,
								rand.nextInt(3) + 1));
			}
		}
	}
}