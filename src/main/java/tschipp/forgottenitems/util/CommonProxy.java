package tschipp.forgottenitems.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.blocks.BlockList;
import tschipp.forgottenitems.blocks.tileentity.TileEntityAdvancedRuneReader;
import tschipp.forgottenitems.blocks.tileentity.TileEntityRuneReader;
import tschipp.forgottenitems.items.ItemList;
import tschipp.forgottenitems.models.ModelCushionedBoots;
import tschipp.forgottenitems.models.ModelLifebelt;

public class CommonProxy {
	
	public static final Map<Item, ModelBiped> golemArmorModels = new HashMap<Item, ModelBiped>();

	
	public void preInit(FMLPreInitializationEvent event) {

		BlockList.registerBlocks();
		ItemList.registerItems();
		GameRegistry.registerTileEntity(TileEntityRuneReader.class, FIM.MODID + ":firunereader");
		GameRegistry.registerTileEntity(TileEntityAdvancedRuneReader.class, FIM.MODID + ":fiadvancedrunereader");

	}
	
	public void init(FMLInitializationEvent event) {
		
		FIOredict.registerOreDict();
	    NetworkRegistry.INSTANCE.registerGuiHandler(FIM.instance, new FIGuiHandler());

	}

	public void postInit(FMLPostInitializationEvent event) {
	
	}


	public ModelCushionedBoots getCushionedBootsModel() {
		return null;
	}
	
	
	public ModelLifebelt getLifebeltModel() {
		return null;
	}

	public Map<Item, ModelBiped> getGolemArmor() {
		return null;
	}

}
