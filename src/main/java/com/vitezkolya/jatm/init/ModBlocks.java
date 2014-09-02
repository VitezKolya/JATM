package com.vitezkolya.jatm.init;

import com.vitezkolya.jatm.JATM;
import com.vitezkolya.jatm.block.BlockCharcoal;
import com.vitezkolya.jatm.block.BlockHeavyDutySteelFrame;
import com.vitezkolya.jatm.block.BlockJATM;
import com.vitezkolya.jatm.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks {

	public static final BlockJATM heavyDutySteelFrame = new BlockHeavyDutySteelFrame();

	public static final BlockJATM blockCharcoal = new BlockCharcoal();

	public static void init() {

		GameRegistry.registerBlock(heavyDutySteelFrame, Names.Blocks.HD_STEEL_FRAME);

		GameRegistry.registerBlock(blockCharcoal, Names.Blocks.CHARCOAL_BLOCK);

		OreDictionary.registerOre(Names.Blocks.CHARCOAL_BLOCK, blockCharcoal);

		JATM.OGLinstance.addOre(blockCharcoal);
	}
}
