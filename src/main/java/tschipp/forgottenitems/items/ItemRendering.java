package tschipp.forgottenitems.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import tschipp.forgottenitems.FIM;
import tschipp.tschipplib.util.TSItemRendering;

public class ItemRendering extends TSItemRendering {

	
	public static void registerItemRenders()
	{
		
		reg(ItemList.explosionPickaxe);
		reg(ItemList.gamblePickaxe);
		reg2(ItemList.craftingRune);
		reg(ItemList.veinPickaxe);
		reg(ItemList.hastyPickaxe);
		reg(ItemList.shockGem);
		reg(ItemList.shockTalisman);
		reg(ItemList.fireGem);
		reg(ItemList.fireTalisman);
		reg(ItemList.waterGem);
		reg(ItemList.waterTalisman);
		reg(ItemList.windGem);
		reg(ItemList.windTalisman);
		reg(ItemList.enderGem);
		reg(ItemList.enderTalisman);
		reg(ItemList.obsidianHarvester);
		reg(ItemList.cushionedBoots);
		reg(ItemList.golemBoots);
		reg(ItemList.golemChestplate);
		reg(ItemList.golemHelmet);
		reg(ItemList.golemLeggings);
		reg(ItemList.boundPickaxe);
		reg(ItemList.boundAxe);
		reg(ItemList.boundShovel);
		reg(ItemList.lifebelt);
		
		if(Loader.isModLoaded("barkifier"))
			reg(ItemList.barkifiedAxe);
	}
	
	
	
	public static void reg2(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(item, 1, new ModelResourceLocation(item.getRegistryName(), "inventory"));		

	}
	

}
