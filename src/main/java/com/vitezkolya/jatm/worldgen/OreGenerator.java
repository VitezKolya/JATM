package com.vitezkolya.jatm.worldgen;

import com.vitezkolya.jatm.JATM;
import com.vitezkolya.jatm.reference.Reference;
import com.vitezkolya.jatm.reference.Settings;
import com.vitezkolya.jatm.utility.Ore;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.ArrayList;
import java.util.Random;

public class OreGenerator implements IWorldGenerator {

	//private String dimReplaceBlock;
	private ArrayList<String> dimList;


	/**
	 * Generate some world
	 *
	 * @param random         the chunk specific {@link java.util.Random}.
	 * @param chunkX         the chunk X coordinate of this chunk.
	 * @param chunkZ         the chunk Z coordinate of this chunk.
	 * @param world          : additionalData[0] The minecraft {@link net.minecraft.world.World} we're generating for.
	 * @param chunkGenerator : additionalData[1] The {@link net.minecraft.world.chunk.IChunkProvider} that is generating.
	 * @param chunkProvider  : additionalData[2] {@link net.minecraft.world.chunk.IChunkProvider} that is requesting the world generation.
	 */
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		ArrayList<Ore> oreList = JATM.OGLinstance.getOreList();

		for(int ores = 0; ores < oreList.size(); ores++) {

			Ore ore = oreList.get(ores);

			for(int dimListIndex = 0; dimListIndex < ore.dimensionList.length; dimListIndex++) {

				String dimension = ore.dimensionList[dimListIndex];
				String dimReplaceBlock = "";

				//     -15:endstone,-1,0,1, 5~25, 26, 32~50:Netherrak

				if(checkDimSpecChars(dimension)) {

					if(dimension.contains(":")) {

						String[] dimSplit;

						dimSplit = splitDimensionData(dimension);

						dimension = dimSplit[0];
						dimReplaceBlock = dimSplit[1];

					}

					if(dimension.contains("~")){

						if(isValidDim(dimension, world)) {

							dimReplaceBlock = defaultMinecraftDims(world);

							if(dimReplaceBlock != "") {

								addOreSpawn(ore.block, GameRegistry.findBlock(Reference.MOD_ID, dimReplaceBlock), world, random, chunkX * 16, chunkZ * 16, ore.veinSize - random.nextInt(6), ore.veinSize, ore.veinChance, ore.minHeight, ore.maxHeight);
							} else {

								addOreSpawn(ore.block, world, random, chunkX * 16, chunkZ * 16, ore.veinSize - random.nextInt(6), ore.veinSize, ore.veinChance, ore.minHeight, ore.maxHeight);
							}
						}
					} else {

						if(isValidDim(dimension, world)) {

							if(dimReplaceBlock != "") {

								addOreSpawn(ore.block, GameRegistry.findBlock(Reference.MOD_ID, dimReplaceBlock), world, random, chunkX * 16, chunkZ * 16, ore.veinSize - random.nextInt(6), ore.veinSize, ore.veinChance, ore.minHeight, ore.maxHeight);
							} else {

								addOreSpawn(ore.block, world, random, chunkX * 16, chunkZ * 16, ore.veinSize - random.nextInt(6), ore.veinSize, ore.veinChance, ore.minHeight, ore.maxHeight);
							}
						}
					}
				} else {

					if(isValidDim(dimension, world)) {

						dimReplaceBlock = defaultMinecraftDims(world);

						// Spawn ore
						addOreSpawn(ore.block, world, random, chunkX * 16, chunkZ * 16, ore.veinSize - random.nextInt(6), ore.veinSize, ore.veinChance, ore.minHeight, ore.maxHeight);
					}
				}
			}
		}
	}

	private boolean isValidDim(String dimension, World world) {

		if(checkDimSpecChars(dimension)) {

			String[] dimSplit = dimension.split("~");
			int smallerDimID = Integer.parseInt(dimSplit[0]);
			int largerDimID = Integer.parseInt(dimSplit[1]);

			for(int dimId = smallerDimID; smallerDimID < largerDimID; smallerDimID++) {

				if(world.provider.dimensionId == smallerDimID) {

					return true;
				}
			}

		} else {

			if(world.provider.dimensionId == Integer.parseInt(dimension)) {

				return true;
			}
		}

		return false;
	}

	private String defaultMinecraftDims(World world) {

		switch (world.provider.dimensionId) {

			case -1:
				return "netherrack";
			case 0:
				return "stone";
			case 1:
				return "end_stone";
			default:
				return "stone";
		}
	}

	private boolean checkDimSpecChars(String data) {

		if(data.contains(":")) {

			return true;
		} else if(data.contains("~")) {

			return true;
		}

		return false;
	}

	/**
	 * Splits dimension data
	 * @param data    dimensionID:replaceBlock
	 * @return    {dimensionID, replaceBlock}
	 */
	private String[] splitDimensionData(String data) {

		return data.split(":");
	}

	/**
	 *
	 * This method adds our block to the world.
	 * It randomizes the coordinates, and does that as many times, as defined in spawnChance.
	 * Then it gives all the params to WorldGenMinable, which handles the replacing of the ores for us.
	 *
	 * @param block The block you want to spawn
	 * @param world The world
	 * @param random The Random
	 * @param blockXPos the blockXpos of a chunk
	 * @param blockZPos the blockZpos of a chunk
	 * @param minVeinSize min vein
	 * @param maxVeinSize max vein
	 * @param chancesToSpawn the chance to spawn. Usually around 2
	 * @param minY lowest point to spawn
	 * @param maxY highest point to spawn
	 */
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int minY, int maxY ) {

		addOreSpawn(block, Blocks.stone, world, random, blockXPos, blockZPos, minVeinSize, maxVeinSize, chancesToSpawn, minY, maxY);
	}

	public void  addOreSpawn(Block block, Block Replaceable, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int minY, int maxY) {

		if(minVeinSize <= 0) {
			minVeinSize = 1;
		}

		if(maxVeinSize <= 0) {
			maxVeinSize = 1;
		}

		WorldGenMinable minable = new WorldGenMinable(block, (minVeinSize + random.nextInt(maxVeinSize - minVeinSize)), Replaceable);

		for(int i = 0; i < chancesToSpawn; i++)
		{
			int posX = blockXPos + random.nextInt(16);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(16);
			minable.generate(world, random, posX, posY, posZ);
		}
	}
}
