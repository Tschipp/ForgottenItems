package tschipp.forgottenitems.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import tschipp.forgottenitems.blocks.tileentity.TileEntityAdvancedRuneReader;
import tschipp.forgottenitems.blocks.tileentity.TileEntityRuneReader;
import tschipp.forgottenitems.containers.ContainerAdvancedRuneReader;
import tschipp.forgottenitems.containers.ContainerRuneReader;
import tschipp.forgottenitems.containers.GuiAdvancedRuneReader;
import tschipp.forgottenitems.containers.GuiRuneReader;

public class FIGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0)
			return new ContainerRuneReader(player.inventory, (TileEntityRuneReader) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == 1)
			return new ContainerAdvancedRuneReader(player.inventory, (TileEntityAdvancedRuneReader) world.getTileEntity(new BlockPos(x, y, z)));
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0)
			return new GuiRuneReader(player.inventory, (TileEntityRuneReader) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == 1)
			return new GuiAdvancedRuneReader(player.inventory, (TileEntityAdvancedRuneReader) world.getTileEntity(new BlockPos(x, y, z)));
		
		return null;
	}

}
