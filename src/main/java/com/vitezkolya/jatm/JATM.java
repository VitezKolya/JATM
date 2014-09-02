package com.vitezkolya.jatm;

import com.vitezkolya.jatm.handler.ConfigurationHandler;
import com.vitezkolya.jatm.handler.OreGenerationListHandler;
import com.vitezkolya.jatm.init.ModBlocks;
import com.vitezkolya.jatm.init.ModItems;
import com.vitezkolya.jatm.init.Recipes;
import com.vitezkolya.jatm.network.PacketHandler;
import com.vitezkolya.jatm.proxy.IProxy;
import com.vitezkolya.jatm.reference.Reference;
import com.vitezkolya.jatm.utility.LogHelper;
import com.vitezkolya.jatm.worldgen.OreGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class JATM {

	@Mod.Instance(Reference.MOD_ID)
	public static JATM instance;

	public static OreGenerationListHandler OGLinstance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		// Network Handling
		// Configs
		// Init items, blocks, recipes

		this.OGLinstance = new OreGenerationListHandler();

		PacketHandler.init();

		ModItems.init();
		ModBlocks.init();

		ConfigurationHandler.init(event.getSuggestedConfigurationFile());

		GameRegistry.registerWorldGenerator(new OreGenerator(), 1);

		LogHelper.info("Pre Initialization Complete");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

		// Register GUIs, tile entities
		// Any other general event handlers
		Recipes.init();

		LogHelper.info("Initialization Complete");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		// Anything to be done after other mods are done with their init.

		LogHelper.info("Post Initialization Complete");
	}
}
