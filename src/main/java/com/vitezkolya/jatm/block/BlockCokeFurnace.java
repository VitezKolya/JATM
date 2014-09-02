package com.vitezkolya.jatm.block;

import com.vitezkolya.jatm.reference.Names;
import com.vitezkolya.jatm.tileentity.TileEntityCokeFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCokeFurnace extends BlockJATM implements ITileEntityProvider {

	public BlockCokeFurnace() {

		super(Material.rock);
		this.setHardness(5.0f);
		this.setBlockName(Names.Blocks.COKE_FURNACE);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metaData) {

		return new TileEntityCokeFurnace();
	}

	@Override
	public boolean renderAsNormalBlock() {

		return true;
	}

	/*@Override
	public int getRenderType() {

		return RenderIds.cokeFurnace
	}*/

	/**
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int p_149694_2_, int p_149694_3_, int p_149694_4_) {

		return Item.getItemFromBlock(Blocks.furnace);
	}
}
