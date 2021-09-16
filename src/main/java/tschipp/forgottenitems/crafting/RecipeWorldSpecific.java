package tschipp.forgottenitems.crafting;

import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.util.FIHelper;

public class RecipeWorldSpecific implements IRecipe
{

	private long seed;
	private Item output;
	private Item coreItem;
	private int recipeID;
	private int count;
	private int meta;

	public RecipeWorldSpecific(Item output, int id)
	{
		this.output = output;
		this.coreItem = FIHelper.getCoreItem(id);
		this.recipeID = id;
		this.count = 1;
		this.meta = 0;
	}

	public RecipeWorldSpecific(Item output, int id, int count, int meta)
	{
		this.output = output;
		this.coreItem = FIHelper.getCoreItem(id);
		this.recipeID = id;
		this.count = count;
		this.meta = meta;
	}

	@Override
	public boolean isDynamic()
	{
		return true;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world)
	{
		if (inv != null && world != null)
		{
			Item item1 = null;
			Item item2 = null;
			Item item3 = null;
			Item item4 = null;
			Item item5 = null;
			Item item6 = null;
			Item item7 = null;
			Item item8 = null;

			ItemStack stack1 = ItemStack.EMPTY;
			ItemStack stack2 = ItemStack.EMPTY;
			ItemStack stack3 = ItemStack.EMPTY;
			ItemStack stack4 = ItemStack.EMPTY;
			ItemStack stack5 = ItemStack.EMPTY;
			ItemStack stack6 = ItemStack.EMPTY;
			ItemStack stack7 = ItemStack.EMPTY;
			ItemStack stack8 = ItemStack.EMPTY;

			ItemStack core = ItemStack.EMPTY;

			seed = FIHelper.getSeed(world);

			item1 = FIHelper.getItemBySeed(seed, 1, recipeID, world);
			item2 = FIHelper.getItemBySeed(seed, 2, recipeID, world);
			item3 = FIHelper.getItemBySeed(seed, 3, recipeID, world);
			item4 = FIHelper.getItemBySeed(seed, 4, recipeID, world);
			item5 = FIHelper.getItemBySeed(seed, 5, recipeID, world);
			item6 = FIHelper.getItemBySeed(seed, 6, recipeID, world);
			item7 = FIHelper.getItemBySeed(seed, 7, recipeID, world);
			item8 = FIHelper.getItemBySeed(seed, 8, recipeID, world);

			for (int i = 0; i < inv.getSizeInventory(); ++i)
			{
				ItemStack currentstack = inv.getStackInSlot(i);

				if (!currentstack.isEmpty())
				{
					Item currentitem = currentstack.getItem();
					if (stack1.isEmpty() && currentitem == item1)
					{
						stack1 = currentstack;
					}
					if (stack2.isEmpty() && currentitem == item2)
					{
						stack2 = currentstack;
					}
					if (stack3.isEmpty() && currentitem == item3)
					{
						stack3 = currentstack;
					}
					if (stack4.isEmpty() && currentitem == item4)
					{
						stack4 = currentstack;
					}
					if (stack5.isEmpty() && currentitem == item5)
					{
						stack5 = currentstack;
					}
					if (stack6.isEmpty() && currentitem == item6)
					{
						stack6 = currentstack;
					}
					if (stack7.isEmpty() && currentitem == item7)
					{
						stack7 = currentstack;
					}
					if (stack8.isEmpty() && currentitem == item8)
					{
						stack8 = currentstack;
					}
					if (core.isEmpty() && currentitem == this.coreItem)
					{
						core = currentstack;
					}

				}
			}

			boolean bool = m(core) && m(stack1) && m(stack2) && m(stack3) && m(stack4) && m(stack5) && m(stack6) && m(stack7) && m(stack8);
			
			return bool;
		}
		
		return false;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Nullable
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		return new ItemStack(this.output, this.count, this.meta);
	}

	/**
	 * Checks if a Stack is empty. True means it is not empty.
	 * 
	 * @param stack
	 * @return
	 */

	private boolean m(ItemStack stack)
	{
		return !stack.isEmpty();
	}

	/**
	 * Returns the size of the recipe area
	 */

	public int getRecipeSize()
	{
		return 9;
	}

	public Item getCoreItem()
	{
		return this.coreItem;
	}

	public int getRecipeID()
	{
		return this.recipeID;
	}

	public ItemStack getRecipeOutput()
	{
		return new ItemStack(this.output, this.count, this.meta);
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
	{
		NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);

		return nonnulllist;
	}

	@Override
	public IRecipe setRegistryName(ResourceLocation name)
	{
		return this;
	}

	@Override
	public ResourceLocation getRegistryName()
	{
		return new ResourceLocation(FIM.MODID, "world_specific_" + recipeID);
	}

	@Override
	public Class<IRecipe> getRegistryType()
	{
		return null;
	}

	@Override
	public boolean canFit(int width, int height)
	{
		if (width > 2 && height > 2)
			return true;
		return false;
	}
}
