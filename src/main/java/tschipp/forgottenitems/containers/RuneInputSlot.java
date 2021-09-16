package tschipp.forgottenitems.containers;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tschipp.forgottenitems.items.ItemList;
import tschipp.forgottenitems.util.FIHelper;

public class RuneInputSlot extends SingleItemSlot {

	protected IInventory te;
	protected World world; 

	public RuneInputSlot(IInventory inventory, int index, int xPosition, int yPosition, World world) {
		super(inventory, index, xPosition, yPosition);

		te = inventory;
		this.world = world;
	}


	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() == ItemList.craftingRune;
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
