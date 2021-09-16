package tschipp.forgottenitems.crafting;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.Level;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.util.FIConfig;
import tschipp.forgottenitems.util.FIHelper;

public class CustomCraftingRecipes {


	public static void registerCustomRecipes()
	{
		final String[] customRecipes = FIConfig.customCraftingRecipes;

		for(int i = 0; i < customRecipes.length; i++)
		{
			for(int k = 0; k < customRecipes.length; k++)
			{
				FIHelper.CUSTOM_OUTPUTS_CORES.add(null);
			}
			
			if((i & 1) == 0 || i == 0)
			{
				//EVEN
				Item core = Item.getByNameOrId(customRecipes[i]);
				if(core == null)
				{
					FIM.LOGGER.error(new IllegalArgumentException("The name for core item: " + customRecipes[i] + "is invalid. Please double check"));
				}

				if(i != customRecipes.length-1)
				{
					Item output = Item.getByNameOrId(customRecipes[i+1]);
					if(output == null)
					{
						FIM.LOGGER.error(new IllegalArgumentException("The name for output item: " + customRecipes[i+1] + "is invalid. Please double check"));
					}

					if(output != null && core != null)
					{
						FIHelper.setOutputCoreCustom(i+1, output, core);
						ForgeRegistries.RECIPES.register(new RecipeWorldSpecificCustom(output, i+1));
					}
				}

			}
			else
			{
				//ODD
				//DOES NOTHING
			}
		}



	}

}
