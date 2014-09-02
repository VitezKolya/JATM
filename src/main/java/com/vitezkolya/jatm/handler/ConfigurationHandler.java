package com.vitezkolya.jatm.handler;

import com.vitezkolya.jatm.JATM;
import com.vitezkolya.jatm.reference.Messages;
import com.vitezkolya.jatm.reference.Reference;
import com.vitezkolya.jatm.utility.Ore;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;

public class ConfigurationHandler {

	public static Configuration configuration;

	public static boolean configValue = false;

	public static ArrayList oreList;

	public static void init(File configFile) {

		// Create the configuration object from the given configuration file
		if (configuration == null) {

			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}

	private static void loadConfiguration() {

		// Read in properties from configuration file
		configValue = configuration.get(Configuration.CATEGORY_GENERAL, "configValue", true, "This is an example config value").getBoolean(true);

		ArrayList<Ore> oreList = JATM.OGLinstance.getOreList();

		if (oreList != null) {
			for (int index = 0; index < oreList.size() - 1; index++) {

				Ore ore = oreList.get(index);

				ore.enabled = configuration.getBoolean(ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.GENERATION_ORE_ENABLE,
						Messages.Configuration.CATEGORY_GENERATION_ORE,
						ore.enabledDefault,
						StatCollector.translateToLocal(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.GENERATION_ORE_ENABLE_COMMENT),
						Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.GENERATION_ORE_ENABLE_LABEL);

				ore.minHeight = configuration.getInt(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_MIN_HEIGHT,
						Messages.Configuration.CATEGORY_GENERATION_ORE,
						ore.minHeightDefault,
						0,
						255,
						StatCollector.translateToLocal(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_MIN_HEIGHT_COMMENT),
						Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_MIN_HEIGHT_LABEL);

				ore.maxHeight = configuration.getInt(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_MAX_HEIGHT,
						Messages.Configuration.CATEGORY_GENERATION_ORE,
						ore.maxHeightDefault,
						0,
						255,
						StatCollector.translateToLocal(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_MAX_HEIGHT_COMMENT),
						Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_MAX_HEIGHT_LABEL);

				ore.veinSize = configuration.getInt(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_VEIN_SIZE,
						Messages.Configuration.CATEGORY_GENERATION_ORE,
						ore.veinSizeDefault,
						0,
						255,
						StatCollector.translateToLocal(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_VEIN_SIZE_COMMENT),
						Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_VEIN_SIZE_LABEL);

				ore.veinChance = configuration.getInt(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_VEIN_CHANCE,
						Messages.Configuration.CATEGORY_GENERATION_ORE,
						ore.veinChanceDefault,
						0,
						255,
						StatCollector.translateToLocal(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_VEIN_CHANCE_COMMENT),
						Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_VEIN_CHANCE_LABEL);

				ore.veinCount = configuration.getInt(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_VEIN_COUNT,
						Messages.Configuration.CATEGORY_GENERATION_ORE,
						ore.veinCountDefault,
						0,
						255,
						StatCollector.translateToLocal(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_VEIN_COUNT_COMMENT),
						Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_VEIN_COUNT_LABEL);

				/**
				 *
				 */
				ore.dimensionListDefault = configuration.getStringList(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_DIMENSION_LIST,
						Messages.Configuration.CATEGORY_GENERATION_ORE,
						ore.dimensionListDefault,
						StatCollector.translateToLocal(Messages.Configuration.CATEGORY_GENERATION_ORE + ore.block.getUnlocalizedName().toLowerCase() + Messages.Configuration.ORE_DIMENSION_LIST_COMMENT));
			}
		}

		if (configuration.hasChanged()) {

			configuration.save();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

		if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {

			// Resync configs
			loadConfiguration();
		}
	}
}
