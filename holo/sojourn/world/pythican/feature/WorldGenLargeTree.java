package holo.sojourn.world.pythican.feature;

import holo.utils.world.feature.BaseWorldGenerator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLargeTree extends BaseWorldGenerator
{
	public void generateTrunk(World world, Random rand, int x, int y, int z, int radius, int height)
	{
		for(int i = x - radius; i < x + radius; ++i)
		{
			for(int j = y; j <= y + height && y < 256; ++j)
			{
				for(int k = z - radius; k < z + radius; ++k)
				{
					int y1 = height - (j - y) - rand.nextInt(radius / 2);
					if ((i - x) * (i - x) + (k - z) * (k - z) <= (radius * radius + y1))
					{
						this.setBlock(world, i, j, k, Block.wood.blockID);
					}
				}
			}
		}
	}

	public void generateCanopy(World world, Random rand, int x, int y, int z, int radius, int height)
	{
		float uDiv = (radius * radius) / (height / 2);
		float lDiv = (radius * radius) / (height / 2);
		int groundHeight = 35;
		
		for(int i = x - radius; i < x + radius; ++i)
		{
			for(float k = z - radius; k < z + radius; ++k)
			{
				
				for(float j = y + height; j >= 0 && j < 256; --j)
				{
					float bx = (i - x);
					float by = (j - y);
					float bz = (k - z);
					if (inCanopy(bx, by, bz, radius, height, lDiv, uDiv))
					{
						this.setBlockAndMetadata(world, (int)i, (int)j, (int)k, Block.leaves.blockID, 4);
					}
				}
			}
		}
	}

	public boolean inCanopy(float x, float y, float z, float r, float h, float d, float uD)
	{
		if(y <= upperCanopyY(x, y, z, r, h, uD) && y >= lowerCanopyY(x, y, z, r, h, d))
			return true;
		return false;
	}

	public float lowerCanopyY(float x, float y, float z, float r, float h, float div)
	{
		return (x * x + z * z) / div;
	}

	public float upperCanopyY(float x, float y, float z, float r, float h, float div)
	{
		return h - (x * x + z * z) / div;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
		float scale = (rand.nextFloat() * 0.5F + 1.0F);
		int height = (int)((30 + rand.nextInt(10)) * scale);
		this.generateTrunk(world, rand, x, y, z, (int)(3 * scale), height);
		this.generateCanopy(world, rand, x, y + height, z, (int)((30 + rand.nextInt(15)) ), (int)((10 + rand.nextInt(5)) ));
		return true;
	}

	@Override
	public int getYPosition(World world, Random rand, int x, int z) 
	{
		return 30;
	}
}
