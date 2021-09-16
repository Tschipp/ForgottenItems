package tschipp.forgottenitems.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class SeedPacket implements IMessage {
	
	public String seed;
	
	public SeedPacket() {
		
	}
	
	public SeedPacket(String seed) {
		this.seed = seed;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.seed = ByteBufUtils.readUTF8String(buf);

	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, this.seed);
	}

}
