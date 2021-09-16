package tschipp.forgottenitems.blocks;

import tschipp.forgottenitems.FIM;
import tschipp.tschipplib.util.TSBlockRendering;

public class BlockRenderRegister extends TSBlockRendering {

	public BlockRenderRegister() {
		super(FIM.MODID);
	}
	
	public static void registerBlocks()
	{
		reg(BlockList.runeReader);
		reg(BlockList.advancedRuneReader);
	}

}
