package tschipp.forgottenitems.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tschipp.forgottenitems.blocks.BlockRenderRegister;
import tschipp.forgottenitems.items.ItemBoundAxe;
import tschipp.forgottenitems.items.ItemBoundPickaxe;
import tschipp.forgottenitems.items.ItemBoundShovel;
import tschipp.forgottenitems.items.ItemCraftingRune;
import tschipp.forgottenitems.items.ItemList;
import tschipp.forgottenitems.items.ItemRendering;
import tschipp.forgottenitems.models.ModelCushionedBoots;
import tschipp.forgottenitems.models.ModelGolemArmor;
import tschipp.forgottenitems.models.ModelLifebelt;

public class ClientProxy extends CommonProxy {
	
	private static final ModelGolemArmor golemArmor = new ModelGolemArmor(1.0f);
	private static final ModelGolemArmor golemArmorLegs = new ModelGolemArmor(0.5f);
	
	
	private static final ModelCushionedBoots cushionedBoots = new ModelCushionedBoots(0.5F);
	
	private static final ModelLifebelt lifebelt = new ModelLifebelt(0.5F);

	public static final Map<Item, ModelBiped> golemArmorModels = new HashMap<Item, ModelBiped>();

	
	
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		
		ItemRendering.registerItemRenders();
		BlockRenderRegister.registerBlocks();

		golemArmorModels.put(ItemList.golemHelmet, golemArmor);
		golemArmorModels.put(ItemList.golemChestplate, golemArmor);
		golemArmorModels.put(ItemList.golemLeggings, golemArmorLegs);
		golemArmorModels.put(ItemList.golemBoots, golemArmor);

	}

	public void init(FMLInitializationEvent event) {
		super.init(event);
		
		FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new ItemCraftingRune.Color(), ItemList.craftingRune);
		FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new ItemBoundPickaxe.Color(), ItemList.boundPickaxe);
		FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new ItemBoundAxe.Color(), ItemList.boundAxe);
		FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new ItemBoundShovel.Color(), ItemList.boundShovel);


	}

	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);

	}
	
	@Override
	public Map<Item, ModelBiped> getGolemArmor()
	{
		return golemArmorModels;
	}

	
	@Override
	public ModelCushionedBoots getCushionedBootsModel()
	{
		return cushionedBoots;
	}
	
	
	
	@Override
	public ModelLifebelt getLifebeltModel() {
		return lifebelt;
	}
	
	

}
