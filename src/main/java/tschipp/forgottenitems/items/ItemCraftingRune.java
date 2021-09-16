package tschipp.forgottenitems.items;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.util.FIConfig;
import tschipp.forgottenitems.util.FIHelper;
import tschipp.tschipplib.item.TSItem;

public class ItemCraftingRune extends TSItem {

	public ItemCraftingRune() {
		super("crafting_rune", FIM.MODID);
		this.setCreativeTab(FIM.forgottenItems);
		this.setMaxStackSize(1);
		this.hasSubtypes = true;
	}
	

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems)
	{
		if(!this.isInCreativeTab(tab))
			return;
		
		String[] customRecipes = FIConfig.customCraftingRecipes;

		for(int k = 1; k <= FIM.RECIPE_AMOUNT; k++)
		{	
			if(k == 24 && !Loader.isModLoaded("barkifier"))
				return;
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("id", k);
			ItemStack stack = new ItemStack(this, 1, 0);
			stack.setTagCompound(tag);

			subItems.add(stack);
		}

		for(int i = 0; i < customRecipes.length; i++)
		{
			if((i & 1) == 0 || i == 0)
			{
				if(i != customRecipes.length-1)
				{
					NBTTagCompound tag = new NBTTagCompound();
					tag.setInteger("id", i+1);
					ItemStack stack = new ItemStack(this, 1, 1);
					stack.setTagCompound(tag);

					subItems.add(stack);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		return "" + TextFormatting.LIGHT_PURPLE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name");
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		
		if(player != null && (FIConfig.showRecipeOutput || (FIConfig.showRecipeOutputCreative && player.isCreative())))
		{
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey("id"))
			{
				if(stack.getMetadata() == 0)
				{
					Item output = FIHelper.getOutputItem(stack.getTagCompound().getInteger("id"));

					if(output != null)
						tooltip.add("Output: " + I18n.translateToLocal(output.getUnlocalizedName() + ".name"));
					else
						tooltip.add("Output: " + "None/Error");
				}
				else
				{
					Item output = FIHelper.getOutputItemCustom(stack.getTagCompound().getInteger("id"));

					if(output != null)
						tooltip.add("Output: " + I18n.translateToLocal(output.getUnlocalizedName() + ".name"));
					else
						tooltip.add("Output: " + "None/Error");

				}
			}
		}
	}



	@SideOnly(Side.CLIENT)
	public static class Color implements IItemColor
	{

		@Override
		@SideOnly(Side.CLIENT)
		public int colorMultiplier(ItemStack stack, int tintIndex) {

			if(tintIndex == 1 && stack.hasTagCompound() && stack.getTagCompound().hasKey("id"))
			{
				Random rand = new Random((stack.getTagCompound().getInteger("id") * 3) + FIM.RECIPE_AMOUNT + stack.getMetadata());
				return (rand.nextInt(16777215));
			}
			return -1;
		}

	}



}
