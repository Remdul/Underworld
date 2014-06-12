package com.undergroundminer.remdul.underworld;
//import java.util.Arrays;
//import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
//import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.PerlinOctaveGenerator;
import org.bukkit.generator.BlockPopulator;
public class ChunkGen extends ChunkGenerator
{

	double scale = 35.0;
World w;
Random rand = new Random();
PerlinOctaveGenerator gen;
  
	public ChunkGen(World w)
	{
		this.w = w;
	}
	public List<BlockPopulator> getDefaultPopulators(World w)
	{
		List<BlockPopulator> pops = new ArrayList<BlockPopulator>();
		pops.add(new DungeonPopulator());
		pops.add(new OrePopulator(w));
		pops.add(new LavaPopulator());
		pops.add(new GlowstonePopulator());
		pops.add(new TreePopulator());
		return pops;
	}
        
	@SuppressWarnings("deprecation")
	void setBlock(int x, int y, int z, byte[][] chunk, Material material)
	{
		if (chunk[y >> 4] == null)
		{
			chunk[y >> 4] = new byte[16 * 16 * 16];
		}
		if (!(y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0))
		{
			return;
		}
		try
		{
			chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (byte) material
					.getId();
		}
		catch (Exception e)
		{

		}
	}
	@Override
	public byte[][] generateBlockSections(World world, Random random,
			int ChunkX, int ChunkZ, BiomeGrid biomes)
	{
		rand.setSeed(world.getSeed());
		gen = new PerlinOctaveGenerator(
				(long) (rand.nextLong() * rand.nextGaussian()), 8);
		byte[][] chunk = new byte[(world.getMaxHeight()) / 16][];
		gen.setScale(1 / scale);
		@SuppressWarnings("unused")
		double threshold = 0.0;
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				int real_x = x + ChunkX * 16;
				int real_z = z + ChunkZ * 16;
				for (int y = 1; y < 256; y++)
				{
					if (y == 250 || y == 251)
					{
						setBlock(x, y, z, chunk, Material.STONE);
					}
					if (y == 2 || y == 3 || y == 4 || y == 5)
					{
						setBlock(x, y, z, chunk, Material.WATER);
					}
					if (gen.noise(real_x, y, real_z, 0.5, 0.5) > UnderworldGen.p.config
							.getFile().getDouble("threshold"))
					{
						setBlock(x, y, z, chunk, Material.STONE);
					}
					if (y == 252 || y == 253 || y == 254 || y == 255)
					{
						setBlock(x, y, z, chunk, Material.BEDROCK);
					}
					if (y == 0 || y == 1)
					{
						setBlock(x, y, z, chunk, Material.BEDROCK);
					}
					if (y == 2 )
					{
						setBlock(x, y, z, chunk, Material.SAND);
					}
				}
			}
		}

		world.setBiome(ChunkX, ChunkZ, Biome.FOREST);

		return chunk;
	}
}