package com.vitezkolya.jatm.creativetab;

import com.vitezkolya.jatm.init.ModItems;
import com.vitezkolya.jatm.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabJATM {

	public static final CreativeTabs JATM_MAIN_TAB = new CreativeTabs(Reference.MOD_ID + "_main") {

		@Override
		public Item getTabIconItem() {

			return ModItems.steelFrameBrace;
		}
	};

	public static final CreativeTabs JATM_RESOURCES_TAB = new CreativeTabs(Reference.MOD_ID + "_resources") {

		@Override
		public Item getTabIconItem() {

			return ModItems.steelFrameBrace;
		}
	};
}
