package com.vitezkolya.jatm.network.message;

import com.vitezkolya.jatm.tileentity.TileEntityCokeFurnace;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by David N on 8/24/2014.
 */
public class MessageTileEntityCokeFurnace implements IMessage, IMessageHandler<MessageTileEntityCokeFurnace, IMessage> {

	public int x, y, z;
	public byte orientation;
	public byte state;
	public String customName, owner;
	public int itemId, metaData, stackSize; //, itemColor;

	public MessageTileEntityCokeFurnace() {


	}

	public MessageTileEntityCokeFurnace(TileEntityCokeFurnace tileEntityCokeFurnace, ItemStack outputItemStack) {

		this.x = tileEntityCokeFurnace.xCoord;
		this.y = tileEntityCokeFurnace.yCoord;
		this.z = tileEntityCokeFurnace.zCoord;

		this.orientation = (byte) tileEntityCokeFurnace.getOrientation().ordinal();
		this.state = (byte) tileEntityCokeFurnace.getState();
		this.customName = tileEntityCokeFurnace.getCustomName();
		this.owner = tileEntityCokeFurnace.getOwner();

		if (outputItemStack != null) {

			this.itemId = Item.getIdFromItem(outputItemStack.getItem());
			this.metaData = outputItemStack.getItemDamage();
			this.stackSize = outputItemStack.stackSize;
			//this.itemColor = ColorHelper.getColor(outputItemStack);
		} else {

			this.itemId = -1;
			this.metaData = 0;
			this.stackSize = 0;
			//this.itemColor = 0;
		}
	}

	/**
	 * Convert from the supplied buffer into your specific message type
	 *
	 * @param buf
	 */
	@Override
	public void fromBytes(ByteBuf buf) {

		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

		this.orientation = buf.readByte();
		this.state = buf.readByte();
		int customNameLength = buf.readInt();
		this.customName = new String(buf.readBytes(customNameLength).array());
		int ownerLength = buf.readInt();
		this.owner = new String(buf.readBytes(ownerLength).array());

		this.itemId = buf.readInt();
		this.metaData = buf.readInt();
		this.stackSize = buf.readInt();
		//this.itemColor = buf.readInt();
	}

	/**
	 * Deconstruct your message into the supplied byte buffer
	 *
	 * @param buf
	 */
	@Override
	public void toBytes(ByteBuf buf) {

		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);

		buf.writeByte(orientation);
		buf.writeByte(state);
		buf.writeInt(customName.length());
		buf.writeBytes(customName.getBytes());
		buf.writeInt(owner.length());
		buf.writeBytes(owner.getBytes());

		buf.writeInt(itemId);
		buf.writeInt(metaData);
		buf.writeInt(stackSize);
		//buf.writeInt(itemColor);
	}

	/**
	 * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
	 * is needed.
	 *
	 * @param message The message
	 * @param ctx
	 * @return an optional return message
	 */
	@Override
	public IMessage onMessage(MessageTileEntityCokeFurnace message, MessageContext ctx) {

		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		if (tileEntity instanceof TileEntityCokeFurnace) {
			((TileEntityCokeFurnace) tileEntity).setOrientation(message.orientation);
			((TileEntityCokeFurnace) tileEntity).setState(message.state);
			((TileEntityCokeFurnace) tileEntity).setCustomName(message.customName);
			((TileEntityCokeFurnace) tileEntity).setOwner(message.owner);
			ItemStack outputItemStack = null;
			if (message.itemId != -1) {
				outputItemStack = new ItemStack(Item.getItemById(message.itemId), message.stackSize, message.metaData);
				/*if (message.itemColor != Integer.parseInt(Colors.PURE_WHITE, 16))
				{
					ColorHelper.setColor(outputItemStack, itemColor);
				}*/
			}

			((TileEntityCokeFurnace) tileEntity).outputItemStack = outputItemStack;
			//NAME UPDATE
			FMLClientHandler.instance().getClient().theWorld.func_147451_t(message.x, message.y, message.z);
		}

		return null;
	}

	@Override
	public String toString() {

		return String.format("MessageTileEntityCokeFurnace - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, owner:%s, itemId: %s, metaData: %s, stackSize: %s", x, y, z, orientation, state, customName, owner, itemId, metaData, stackSize);
	}
}
