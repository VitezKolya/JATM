package com.vitezkolya.jatm.proxy;

import com.vitezkolya.jatm.handler.ConfigurationHandler;
import com.vitezkolya.jatm.reference.Names;
import com.vitezkolya.jatm.tileentity.TileEntityCokeFurnace;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy {

	public void registerEventHandlers() {

		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
	}

	public void registerTileEntities() {

		GameRegistry.registerTileEntityWithAlternatives(TileEntityCokeFurnace.class, Names.Blocks.COKE_FURNACE, "tile." + Names.Blocks.COKE_FURNACE);
	}
}
