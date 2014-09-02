package com.vitezkolya.jatm.network;

import com.vitezkolya.jatm.network.message.MessageTileEntityCokeFurnace;
import com.vitezkolya.jatm.network.message.MessageTileEntityJATM;
import com.vitezkolya.jatm.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;


public class PacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

	public static void init() {

		INSTANCE.registerMessage(MessageTileEntityJATM.class, MessageTileEntityJATM.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageTileEntityCokeFurnace.class, MessageTileEntityCokeFurnace.class, 1, Side.CLIENT);
	}
}
