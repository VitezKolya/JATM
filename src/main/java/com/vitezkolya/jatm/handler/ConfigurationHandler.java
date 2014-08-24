package com.vitezkolya.jatm.handler;

import com.vitezkolya.jatm.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {

	public static Configuration configuration;

	public static boolean configValue = false;

	public static void init(File configFile) {

		// Create the configuration object from the given configuration file
		if(configuration == null) {

			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

		if(event.modID.equalsIgnoreCase(Reference.MOD_ID)) {

			// Resync configs
			loadConfiguration();
		}
	}


	private static void loadConfiguration() {

		// Read in properties from configuration file
		configValue = configuration.get(Configuration.CATEGORY_GENERAL, "configValue", true, "This is an example config value").getBoolean(true);

		if(configuration.hasChanged()) {
			configuration.save();
		}
	}
}
