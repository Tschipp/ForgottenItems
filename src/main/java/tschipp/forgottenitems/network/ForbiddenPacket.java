package tschipp.forgottenitems.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ForbiddenPacket implements IMessage {
	
	public String forbidden;
	
	public ForbiddenPacket() {
		
	}
	
	public ForbiddenPacket(String forbidden) {
		this.forbidden = forbidden;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.forbidden = ByteBufUtils.readUTF8String(buf);

	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, this.forbidden);
	}

}

