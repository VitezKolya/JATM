package com.vitezkolya.jatm.handler;

import com.vitezkolya.jatm.JATM;
import com.vitezkolya.jatm.reference.Messages;
import com.vitezkolya.jatm.reference.Reference;
import com.vitezkolya.jatm.utility.LogHelper;
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

		if (!oreList.isEmpty()) {

			for (int index = 0; index < oreList.size(); index++) {

				Ore ore = oreList.get(index);

				LogHelper.info(("Config add: " + ore.block.getLocalizedName()));

				ore.enabled = configuration.get(Messages.Configuration.CATEGORY_GENERATION_ORE + "." + ore.block.getLocalizedName().toLowerCase().replace(' ', '_'),
						Messages.Configuration.GENERATION_ORE_ENABLE,
						ore.enabledDefault,
						Messages.Configuration.GENERATION_ORE_ENABLE_COMMENT).getBoolean(ore.enabledDefault);

				ore.minHeight = configuration.get(Messages.Configuration.CATEGORY_GENERATION_ORE + "." + ore.block.getLocalizedName().toLowerCase().replace(' ', '_'),
						Messages.Configuration.ORE_MIN_HEIGHT,
						ore.minHeightDefault,
						Messages.Configuration.ORE_MIN_HEIGHT_COMMENT,
						0,
						255).getInt(ore.minHeightDefault);

				ore.maxHeight = configuration.get(Messages.Configuration.CATEGORY_GENERATION_ORE + "." + ore.block.getLocalizedName().toLowerCase().replace(' ', '_'),
						Messages.Configuration.ORE_MAX_HEIGHT,
						ore.maxHeightDefault,
						Messages.Configuration.ORE_MAX_HEIGHT_COMMENT,
						0,
						255).getInt(ore.maxHeightDefault);

				ore.veinSize = configuration.get(Messages.Configuration.CATEGORY_GENERATION_ORE + "." + ore.block.getLocalizedName().toLowerCase().replace(' ', '_'),
						Messages.Configuration.ORE_VEIN_SIZE,
						ore.veinSizeDefault,
						Messages.Configuration.ORE_VEIN_SIZE_COMMENT,
						0,
						255).getInt(ore.veinSizeDefault);

				ore.veinChance = configuration.get(Messages.Configuration.CATEGORY_GENERATION_ORE + "." + ore.block.getLocalizedName().toLowerCase().replace(' ', '_'),
						Messages.Configuration.ORE_VEIN_CHANCE,
						ore.veinChanceDefault,
						Messages.Configuration.ORE_VEIN_CHANCE_COMMENT,
						0,
						255).getInt(ore.veinChanceDefault);

				ore.veinCount = configuration.get(Messages.Configuration.CATEGORY_GENERATION_ORE + "." + ore.block.getLocalizedName().toLowerCase().replace(' ', '_'),
						Messages.Configuration.ORE_VEIN_COUNT,
						ore.veinCountDefault,
						Messages.Configuration.ORE_VEIN_COUNT_COMMENT,
						0,
						255).getInt(ore.veinCountDefault);

				ore.dimensionListDefault = configuration.get(Messages.Configuration.CATEGORY_GENERATION_ORE + "." + ore.block.getLocalizedName().toLowerCase().replace(' ', '_'),
						Messages.Configuration.ORE_DIMENSION_LIST,
						ore.dimensionListDefault,
						Messages.Configuration.ORE_DIMENSION_LIST_COMMENT).getStringList();

				// Save the config to the ore
				oreList.set(index,ore);
			}

			// Save the configs to the ore list
			JATM.OGLinstance.setOreList(oreList);

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
