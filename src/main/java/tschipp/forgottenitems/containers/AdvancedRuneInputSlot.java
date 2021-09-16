package tschipp.forgottenitems.containers;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tschipp.forgottenitems.items.ItemList;
import tschipp.forgottenitems.util.FIHelper;

public class AdvancedRuneInputSlot extends RuneInputSlot {

	public AdvancedRuneInputSlot(IInventory inventory, int index, int xPosition, int yPosition, World world) {
		super(inventory, index, xPosition, yPosition, world);
	}


	@Override
	public void onSlotChanged() {
		ItemStack rune = te.getStackInSlot(9);

		if(!rune.isEmpty() && rune.getItem() == ItemList.craftingRune && rune.hasTagCompound() && rune.getTagCompound().hasKey("id"))
		{

			int id = rune.getTagCompound().getInteger("id");

			if(rune.getMetadata() == 0)
			{
				ArrayList<Item> recipe = FIHelper.getItemsForRecipe(world, id);

				if(recipe.get(4) != null)
				{

					for(int i = 0; i < 9; i++)
					{
						te.setInventorySlotContents(i, new ItemStack(recipe.get(i)));
					}

					te.setInventorySlotContents(10, new ItemStack(FIHelper.getOutputItem(id)));

				}
				else
				{
					te.clear();
				}
			}
			else
			{
				ArrayList<Item> recipe = FIHelper.getItemsForCustomRecipe(world, id);

				if(recipe.get(4) != null)
				{
					for(int i = 0; i < 9; i++)
					{
						te.setInventorySlotContents(i, new ItemStack(recipe.get(i)));
					}

					te.setInventorySlotContents(10, new ItemStack(FIHelper.getOutputItemCustom(id)));
				}
				else
				{
					te.clear();
				}
			}

		}
		else
		{
			te.clear();
		}
	}

}
