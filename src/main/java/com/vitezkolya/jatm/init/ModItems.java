package com.vitezkolya.jatm.init;

import com.vitezkolya.jatm.item.ItemCoke;
import com.vitezkolya.jatm.item.ItemJATM;
import com.vitezkolya.jatm.item.ItemSteelFrameBrace;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by David N on 8/24/2014.
 */
public class ModItems {

	public static final ItemJATM steelFrameBrace = new ItemSteelFrameBrace();
	public static final ItemJATM coke = new ItemCoke();

	public static void init() {

		GameRegistry.registerItem(steelFrameBrace, "steelFrameBrace");

		GameRegistry.registerItem(coke, "coke");

		OreDictionary.registerOre("coke", coke);
	}
}
