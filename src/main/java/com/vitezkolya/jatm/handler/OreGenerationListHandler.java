package com.vitezkolya.jatm.handler;

import com.vitezkolya.jatm.utility.Ore;
import net.minecraft.block.Block;

import java.util.ArrayList;

public class OreGenerationListHandler {

	private ArrayList<Ore> oreList = new ArrayList<Ore>();

	/**
	 * Add an ore block to the ore generation list
	 *
	 * @param block                Ore block to be generated
	 * @param defaultVeinCount     Default value for vein count
	 * @param defaultVeinSize      Default value for vein size
	 * @param defaultMinHeight     Default value for min height
	 * @param defaultMaxHeight     Default value for max height
	 * @param defaultVeinChance    Default value for vein spawn chance
	 * @param defaultDimensionList Default value list for list of dimensions the ore to spawn in
	 */
	public void addOre(Block block, int defaultVeinCount, int defaultVeinSize,
	                   int defaultMinHeight, int defaultMaxHeight, int defaultVeinChance,
	                   String[] defaultDimensionList) {

		oreList.add(new Ore(block, defaultVeinCount, defaultVeinSize, defaultMinHeight, defaultMaxHeight,
				defaultVeinChance, defaultDimensionList));
	}


	/**
	 * Add ore to generation list and use hard coded default values.
	 *
	 * @param block Ore block to be generated
	 */
	public void addOre(Block block) {

		addOre(block, 5, 7, 4, 75, 50, new String[] {"-1", "0", "1"});
	}

	/**
	 * Returns the list of ores to be generated
	 *
	 * @return oreList - ArrayList of Ore classes
	 */
	public ArrayList<Ore> getOreList() {

		return oreList;
	}
}
