package tschipp.forgottenitems.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.blocks.BlockList;
import tschipp.forgottenitems.items.ItemList;
import tschipp.tschipplib.util.TSCrafting;

public class CraftingRegister extends TSCrafting {
	
	public static void registerRecipes()
	{
		RecipeSorter.register("worldSpecific", RecipeWorldSpecific.class, Category.SHAPELESS, "");
		RecipeSorter.register("worldSpecificCustom", RecipeWorldSpecificCustom.class, Category.SHAPELESS, "");

		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(null, new ItemStack(BlockList.runeReader, 1), new Object[] {"BDB", "RQR", "BCB", 'B' , "stone", 'D', "blockDiamond", 'R', ItemList.craftingRune, 'Q', "blockQuartz", 'C', "blockRedstone"}).setRegistryName(FIM.MODID, "rune_reader"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(null, new ItemStack(BlockList.advancedRuneReader, 1), new Object[] {"EGE", "QRQ", "QOQ", 'E' , "blockEmerald", 'G', "blockGold", 'R', BlockList.runeReader, 'Q', "blockQuartz", 'O', "obsidian"}).setRegistryName(FIM.MODID, "advanced_rune_reader"));

		reg(new RecipeWorldSpecific(ItemList.explosionPickaxe, 1));
		reg(new RecipeWorldSpecific(ItemList.gamblePickaxe, 2));
		reg(new RecipeWorldSpecific(ItemList.veinPickaxe, 3));
		reg(new RecipeWorldSpecific(ItemList.hastyPickaxe, 4));
		reg(new RecipeWorldSpecific(ItemList.shockGem, 5));
		reg(new RecipeWorldSpecific(ItemList.shockTalisman, 6));
		reg(new RecipeWorldSpecific(ItemList.fireGem, 7));
		reg(new RecipeWorldSpecific(ItemList.fireTalisman, 8));
		reg(new RecipeWorldSpecific(ItemList.golemHelmet, 9));
		reg(new RecipeWorldSpecific(ItemList.golemChestplate, 10));
		reg(new RecipeWorldSpecific(ItemList.golemLeggings, 11));
		reg(new RecipeWorldSpecific(ItemList.golemBoots, 12));
		reg(new RecipeWorldSpecific(ItemList.waterGem, 13));
		reg(new RecipeWorldSpecific(ItemList.waterTalisman, 14));
		reg(new RecipeWorldSpecific(ItemList.windGem, 15));
		reg(new RecipeWorldSpecific(ItemList.windTalisman, 16));
		reg(new RecipeWorldSpecific(ItemList.enderGem, 17));
		reg(new RecipeWorldSpecific(ItemList.enderTalisman, 18));
		reg(new RecipeWorldSpecific(ItemList.obsidianHarvester,  19));
		reg(new RecipeWorldSpecific(ItemList.cushionedBoots, 20));
		reg(new RecipeWorldSpecific(ItemList.boundPickaxe,21));
		reg(new RecipeWorldSpecific(ItemList.boundAxe, 22));
		reg(new RecipeWorldSpecific(ItemList.boundShovel, 23));
		if(Loader.isModLoaded("barkifier"))
			reg(new RecipeWorldSpecific(ItemList.barkifiedAxe, 24));
		reg(new RecipeWorldSpecific(ItemList.lifebelt, 25));
		
		
		CustomCraftingRecipes.registerCustomRecipes();
		
		
	}
	
	public static void reg(IRecipe recipe)
	{
		ForgeRegistries.RECIPES.register(recipe);
	}

	
	
}
