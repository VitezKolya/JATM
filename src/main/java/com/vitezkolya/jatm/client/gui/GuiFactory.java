package com.vitezkolya.jatm.client.gui;

import com.vitezkolya.jatm.handler.ConfigurationHandler;
import com.vitezkolya.jatm.reference.Messages;
import com.vitezkolya.jatm.reference.Reference;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GuiFactory implements IModGuiFactory {


	@Override
	public void initialize(Minecraft minecraftInstance) {

	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {

		return ModGuiConfig.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {

		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {

		return null;
	}

	/*public static class JATMConfigGui extends GuiConfig {

		public JATMConfigGui(GuiScreen parentScreen) {

			super(parentScreen, getConfigElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
		}

		private static List<IConfigElement> getConfigElements() {

			List<IConfigElement> list = new ArrayList<IConfigElement>();

			list.add(new DummyConfigElement.DummyCategoryElement("jatmCfg", "test.configgui.jatmGeneralConfig", jatmGeneralConfig.class));

			return list;
		}

		public static class jatmGeneralConfig extends GuiConfigEntries.CategoryEntry{


			public jatmGeneralConfig(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {

				super(owningScreen, owningEntryList, prop);
			}

			@Override
			protected GuiScreen buildChildScreen() {

				return new GuiConfig(this.owningScreen,
						new ConfigElement(ForgeModContainer.getConfig().getCategory(Messages.Configuration.CATEGORY_GENERATION_ORE)).getChildElements(),
						this.owningScreen.modID,
						Messages.Configuration.CATEGORY_GENERATION_ORE,
						false,
						false,
						GuiConfig.getAbridgedConfigPath(ForgeModContainer.getConfig().toString()));
			}
		}
	}*/
}
