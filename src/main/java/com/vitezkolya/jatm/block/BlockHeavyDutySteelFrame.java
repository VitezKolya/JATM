package com.vitezkolya.jatm.block;

import com.vitezkolya.jatm.reference.Names;
import net.minecraft.block.material.Material;

public class BlockHeavyDutySteelFrame extends BlockJATM {

	public BlockHeavyDutySteelFrame() {

		super(Material.iron);
		this.setBlockName(Names.Blocks.HD_STEEL_FRAME);
		this.setBlockTextureName(Names.Blocks.HD_STEEL_FRAME);
		this.setHardness(10.0F);
		this.setResistance(20.0F);
		this.setStepSound(soundTypeMetal);

	}
}
