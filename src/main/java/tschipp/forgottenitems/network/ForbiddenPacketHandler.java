package tschipp.forgottenitems.network;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tschipp.forgottenitems.util.FIWorldSavedData;

public class ForbiddenPacketHandler implements IMessageHandler<ForbiddenPacket, IMessage>{

	@Override
	public IMessage onMessage(final ForbiddenPacket message, final MessageContext ctx) {
		IThreadListener mainThread = Minecraft.getMinecraft();


		mainThread.addScheduledTask(new Runnable() {

			@Override
			public void run() {

				String[] array = message.forbidden.replace("[", "").replace("]", "").split(", ");
				FIWorldSavedData.getInstance(Minecraft.getMinecraft().world).setForbidden(new ArrayList<String>(Arrays.asList(array)));

			}});

		return null;
	}



}

