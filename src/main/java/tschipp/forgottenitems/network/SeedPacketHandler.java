package tschipp.forgottenitems.network;

import tschipp.forgottenitems.util.FIWorldSavedData;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SeedPacketHandler implements IMessageHandler<SeedPacket, IMessage>{

	@Override
	public IMessage onMessage(final SeedPacket message, final MessageContext ctx) {
		IThreadListener mainThread = Minecraft.getMinecraft();


		mainThread.addScheduledTask(new Runnable() {

			@Override
			public void run() {

				FIWorldSavedData.getInstance(Minecraft.getMinecraft().world).setSeed(Long.parseLong(message.seed));

			}});

		return null;
	}



}


