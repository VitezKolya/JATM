package com.vitezkolya.jatm.network.message;

import com.vitezkolya.jatm.tileentity.TileEntityJATM;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityJATM implements IMessage, IMessageHandler<MessageTileEntityJATM, IMessage> {

	public int x, y, z;
	public byte orientation, state;
	public String customName, owner;

	public MessageTileEntityJATM() {


	}

	public MessageTileEntityJATM(TileEntityJATM tileEntityJATM) {

		this.x = tileEntityJATM.xCoord;
		this.y = tileEntityJATM.yCoord;
		this.z = tileEntityJATM.zCoord;

		this.orientation = (byte) tileEntityJATM.getOrientation().ordinal();
		this.state = (byte) tileEntityJATM.getState();
		this.customName = tileEntityJATM.getCustomName();
		this.owner = tileEntityJATM.getOwner();
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
	public IMessage onMessage(MessageTileEntityJATM message, MessageContext ctx) {

		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

		if (tileEntity instanceof TileEntityJATM) {

			((TileEntityJATM) tileEntity).setOrientation(message.orientation);
			((TileEntityJATM) tileEntity).setState(message.state);
			((TileEntityJATM) tileEntity).setCustomName(message.customName);
			((TileEntityJATM) tileEntity).setOwner(message.owner);
		}

		return null;
	}

	@Override
	public String toString() {

		return String.format("MessageTileEntityJATM - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, owner:%s", x, y, z, orientation, state, customName, owner);
	}
}
