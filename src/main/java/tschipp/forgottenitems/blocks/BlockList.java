package tschipp.forgottenitems.blocks;

import net.minecraft.block.Block;

public class BlockList {

	public static Block runeReader;
	public static Block advancedRuneReader;

	public static void registerBlocks() {
		
		runeReader = new BlockRuneReader();
		advancedRuneReader = new BlockAdvancedRuneReader();
	}

}
