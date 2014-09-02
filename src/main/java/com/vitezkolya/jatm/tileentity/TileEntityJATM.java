package com.vitezkolya.jatm.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityJATM extends TileEntity {

	protected ForgeDirection orientation;
	protected byte state;
	protected String customName;
	protected String owner;

	public TileEntityJATM() {

		orientation = ForgeDirection.SOUTH;
		state = 0;
		customName = "";
		owner = "";
	}

	public ForgeDirection getOrientation() {

		return orientation;
	}

	public void setOrientation(ForgeDirection orientation) {

		this.orientation = orientation;
	}

	public void setOrientation(int orientation) {

		this.orientation = ForgeDirection.getOrientation(orientation);
	}

	public short getState() {

		return state;
	}

	public void setState(byte state) {

		this.state = state;
	}

	public String getCustomName() {

		return customName;
	}

	public void setCustomName(String customName) {

		this.customName = customName;
	}

	public String getOwner() {

		return owner;
	}

	public void setOwner(String owner) {

		this.owner = owner;
	}

	public boolean hasCustomName() {

		return customName != null && customName.length() > 0;
	}

	public boolean hasOwner() {

		return owner != null && owner.length() > 0;
	}
}
