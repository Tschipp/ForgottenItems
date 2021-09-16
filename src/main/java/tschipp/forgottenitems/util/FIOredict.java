package tschipp.forgottenitems.util;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tschipp.forgottenitems.items.ItemList;

public class FIOredict {

	public static void registerOreDict() {
		
		
		OreDictionary.registerOre("listAllwater", Items.WATER_BUCKET);
		OreDictionary.registerOre("listAllwater", new ItemStack(ItemList.waterTalisman, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("listAllwater", ItemList.waterTalisman);
		
	}

}
