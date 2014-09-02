package com.vitezkolya.jatm.block;

import com.vitezkolya.jatm.creativetab.CreativeTabJATM;
import com.vitezkolya.jatm.reference.Names;

public class BlockCharcoal extends BlockJATM {

	public BlockCharcoal() {

		this.setCreativeTab(CreativeTabJATM.JATM_RESOURCES_TAB);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypePiston);
		this.setBlockName(Names.Blocks.CHARCOAL_BLOCK);
		this.setBlockTextureName(Names.Blocks.CHARCOAL_BLOCK);
	}
}
