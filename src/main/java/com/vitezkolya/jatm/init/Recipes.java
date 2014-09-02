package com.vitezkolya.jatm.init;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {


	public static void init() {

		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockCharcoal), new Object[]{"sss", "sss", "sss", 's', new ItemStack(Items.coal, 1, 1)});

		GameRegistry.addSmelting(new ItemStack(Blocks.coal_block, 1, 0), new ItemStack(ModItems.coke, 5, 0), 0.5f);

		GameRegistry.addSmelting(ModBlocks.blockCharcoal, new ItemStack(ModItems.coke, 1, 0), 0.5f);
	}
}
