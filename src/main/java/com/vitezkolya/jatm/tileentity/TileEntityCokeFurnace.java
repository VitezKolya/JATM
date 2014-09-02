package com.vitezkolya.jatm.tileentity;

import com.vitezkolya.jatm.init.ModBlocks;
import com.vitezkolya.jatm.network.PacketHandler;
import com.vitezkolya.jatm.network.message.MessageTileEntityCokeFurnace;
import com.vitezkolya.jatm.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCokeFurnace extends TileEntityJATM implements ISidedInventory {

	public static final int INVENTORY_SIZE = 3;
	public static final int FUEL_INVENTORY_INDEX = 0;
	public static final int INPUT_INVENTORY_INDEX = 1;
	public static final int OUTPUT_INVENTORY_INDEX = 2;

	public int deviceCookTime;      // How much longer the Coke Furnace will cook
	public int fuelBurnTime;        // The fuel value for the currently burning fuel
	public int itemCookTime;        // How long the current item has been "cooking"

	public ItemStack outputItemStack;

	/**
	 * The ItemStacks that hold the items currently being used in the Coke Furnace
	 */
	private ItemStack[] furnaceInventory;

	public TileEntityCokeFurnace() {

		furnaceInventory = new ItemStack[INVENTORY_SIZE];
	}

	/**
	 * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
	 * block.
	 *
	 * @param side
	 */
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {

		return side == ForgeDirection.DOWN.ordinal() ? new int[]{FUEL_INVENTORY_INDEX, OUTPUT_INVENTORY_INDEX} : new int[]{INPUT_INVENTORY_INDEX, OUTPUT_INVENTORY_INDEX};
	}

	/**
	 * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	 * side
	 *
	 * @param slotIndex
	 * @param itemStack
	 * @param side
	 */
	@Override
	public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side) {

		return false;
	}

	/**
	 * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
	 * side
	 *
	 * @param slotIndex
	 * @param itemStack
	 * @param side
	 */
	@Override
	public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side) {

		return slotIndex == OUTPUT_INVENTORY_INDEX;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
// Read in the ItemStacks in the inventory from NBT
		NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEMS, 10);
		furnaceInventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
			byte slotIndex = tagCompound.getByte("Slot");
			if (slotIndex >= 0 && slotIndex < furnaceInventory.length) {
				furnaceInventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}
		deviceCookTime = nbtTagCompound.getInteger("deviceCookTime");
		fuelBurnTime = nbtTagCompound.getInteger("fuelBurnTime");
		itemCookTime = nbtTagCompound.getInteger("itemCookTime");
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory() {

		return furnaceInventory.length;
	}

	/**
	 * Returns the stack in slot i
	 *
	 * @param slotIndex
	 */
	@Override
	public ItemStack getStackInSlot(int slotIndex) {

		return furnaceInventory[slotIndex];
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	 * new stack.
	 *
	 * @param slotIndex
	 * @param decrementAmount
	 */
	@Override
	public ItemStack decrStackSize(int slotIndex, int decrementAmount) {

		ItemStack itemStack = getStackInSlot(slotIndex);

		if (itemStack != null) {

			if (itemStack.stackSize <= decrementAmount) {

				setInventorySlotContents(slotIndex, null);

			} else {

				itemStack = itemStack.splitStack(decrementAmount);

				if (itemStack.stackSize == 0) {

					setInventorySlotContents(slotIndex, null);
				}
			}
		}

		return itemStack;
	}

	/**
	 * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	 * like when you close a workbench GUI.
	 *
	 * @param slotIndex
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {

		ItemStack itemStack = getStackInSlot(slotIndex);

		if (itemStack != null) {

			setInventorySlotContents(slotIndex, null);
		}

		return itemStack;
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 *
	 * @param slotIndex
	 * @param itemStack
	 */
	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {

		furnaceInventory[slotIndex] = itemStack;

		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {

			itemStack.stackSize = getInventoryStackLimit();
		}
	}

	/**
	 * Returns the name of the inventory
	 */
	@Override
	public String getInventoryName() {

		return this.hasCustomName() ? this.getCustomName() : Names.Containers.COKE_FURNACE;
	}

	/**
	 * Returns if the inventory is named
	 */
	@Override
	public boolean hasCustomInventoryName() {

		return this.hasCustomName();
	}

	/**
	 * Returns the maximum stack size for a inventory slot.
	 */
	@Override
	public int getInventoryStackLimit() {

		return 64;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 *
	 * @param entityplayer
	 */
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {

		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
	 *
	 * @param slotIndex
	 * @param itemStack
	 */
	@Override
	public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack) {

		switch (slotIndex) {

			case FUEL_INVENTORY_INDEX: {

				return TileEntityFurnace.isItemFuel(itemStack);
			}
			case INPUT_INVENTORY_INDEX: {

				if (itemStack.getItem() == Items.coal) {

					return true;
				} else if (itemStack.getItem() == new ItemStack(Blocks.coal_block).getItem()) {

					return true;
				} else if (itemStack.getItem() == new ItemStack(ModBlocks.blockCharcoal).getItem()) {

					return true;
				}
			}
			default: {

				return false;
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);

		// Write the ItemStacks in the inventory to NBT
		NBTTagList tagList = new NBTTagList();
		for (int currentIndex = 0; currentIndex < furnaceInventory.length; ++currentIndex) {
			if (furnaceInventory[currentIndex] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) currentIndex);
				furnaceInventory[currentIndex].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}
		nbtTagCompound.setTag(Names.NBT.ITEMS, tagList);
		nbtTagCompound.setInteger("deviceCookTime", deviceCookTime);
		nbtTagCompound.setInteger("fuelBurnTime", fuelBurnTime);
		nbtTagCompound.setInteger("itemCookTime", itemCookTime);
	}

	@Override
	public Packet getDescriptionPacket() {

		return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityCokeFurnace(this, furnaceInventory[OUTPUT_INVENTORY_INDEX]));
	}

	@Override
	public void updateEntity() {


	}
}
