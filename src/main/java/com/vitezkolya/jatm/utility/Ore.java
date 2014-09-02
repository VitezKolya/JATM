package com.vitezkolya.jatm.utility;

import net.minecraft.block.Block;

public class Ore {

	public Block block;
	public int veinCount;
	public int veinCountDefault;
	public int veinSize;
	public int veinSizeDefault;
	public int minHeight;
	public int minHeightDefault;
	public int maxHeight;
	public int maxHeightDefault;
	public int veinChance;
	public int veinChanceDefault;
	public boolean enabled;
	public boolean enabledDefault;
	public String[] dimensionList;
	public String[] dimensionListDefault;


	public Ore(Block block, int veinCount, int veinSize, int minHeight, int maxHeight, int veinChance,
	           String[] dimensionList) {

		this.block = block;
		this.veinCountDefault = veinCount;
		this.veinSizeDefault = veinSize;
		this.minHeightDefault = minHeight;
		this.maxHeightDefault = maxHeight;
		this.veinChanceDefault = veinChance;
		this.enabledDefault = true;
		this.dimensionListDefault = dimensionList;
	}

	public Ore(Block block) {

		this.block = block;
		this.veinCountDefault = 5;
		this.veinSizeDefault = 7;
		this.minHeightDefault = 4;
		this.maxHeightDefault = 75;
		this.veinChanceDefault = 50;

		this.enabledDefault = true;

		String[] dimensionList = {"-1", "0", "1"};

		this.dimensionListDefault = dimensionList;
	}
}
