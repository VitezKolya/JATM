package com.vitezkolya.jatm.item;

import com.vitezkolya.jatm.creativetab.CreativeTabJATM;
import com.vitezkolya.jatm.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemJATM extends Item {

	public ItemJATM() {

		super();
		//this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabJATM.JATM_MAIN_TAB);
	}

	@Override
	public String getUnlocalizedName() {

		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {

		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {

		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	protected String getUnwrappedUnlocalizedName(String unlocallizedName) {

		return unlocallizedName.substring(unlocallizedName.indexOf(".") + 1);
	}
}
