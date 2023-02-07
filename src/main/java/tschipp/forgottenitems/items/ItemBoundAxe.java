package tschipp.forgottenitems.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.util.FIConfig;
import tschipp.forgottenitems.util.FIHelper;
import tschipp.tschipplib.item.TSAxe;

public class ItemBoundAxe extends TSAxe {

	public ItemBoundAxe(ToolMaterial material) {
		super("bound_axe", material, FIM.MODID, 8.0F, -3F);
		this.setCreativeTab(FIM.forgottenItems);
		FIHelper.setOutputCore(22, this, Items.DIAMOND_AXE);

	}
	
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		if(player != null && player instanceof EntityPlayer && !(player instanceof FakePlayer))
		{
			NBTTagCompound tag = new NBTTagCompound();
			String uuid = player.getGameProfile().getName();
			tag.setString("owner", uuid);
			stack.setTagCompound(tag);
		}
	}

	@Override
	public int getEntityLifespan(ItemStack itemStack, World world)
	{
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean onEntityItemUpdate(net.minecraft.entity.item.EntityItem item)
	{
		item.setEntityInvulnerable(true);
		return false;
	}


	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			String playername = player.getGameProfile().getName();

			if(stack.hasTagCompound() && stack.getTagCompound().hasKey("owner"))
			{
				String owner = stack.getTagCompound().getString("owner");

				if(!owner.equals(playername))
				{
					if(!world.isRemote)
						player.entityDropItem(stack, 0.5f);
					player.inventory.removeStackFromSlot(itemSlot);

				}
			}
		}
	}



	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		if(player.isSneaking() && !world.isRemote && player.isCreative())
		{
			FIHelper.printCraftingRecipe(world, player, 22);
			return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}
		else if(!stack.hasTagCompound() || !stack.getTagCompound().hasKey("owner"))
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("owner", player.getGameProfile().getName());
			stack.setTagCompound(tag);
			player.setHeldItem(hand, stack);
			return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}
		return new ActionResult(EnumActionResult.PASS, player.getHeldItem(hand));

	}


	@SideOnly(Side.CLIENT)
	public static class Color implements IItemColor 
	{

		@SideOnly(Side.CLIENT)
		@Override
		public int colorMultiplier(ItemStack stack, int tintIndex) 
		{
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey("owner") && tintIndex == 0)
			{
				String owner = stack.getTagCompound().getString("owner");

				String[] boundColors = FIConfig.customBoundColors;
				ArrayList<String> list = new ArrayList<String>();

				for(int i = 0; i < boundColors.length; i++)
				{
					list.add(boundColors[i]);
				}

				if(list.contains(owner))
				{
					int index = list.indexOf(owner);
					String color = list.get(index + 1);
					try
					{
						int colorint = Integer.parseInt(color);
						return colorint;
					} 
					catch (NumberFormatException e)
					{

					}


				}
				else
				{
					Random rand = new Random(owner.hashCode());
					return rand.nextInt(16777215);
				}

			}
			return -1;
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		return "" + TextFormatting.AQUA + I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".name");
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("owner"))
		{
			tooltip.add(I18n.format("tooltip.forgottenitems.bound_tools.bound_to") + " " + stack.getTagCompound().getString("owner"));
		}
		else
		{
			tooltip.add(I18n.format("tooltip.forgottenitems.bound_tools.unbound"));
		}
		tooltip.add(I18n.format("tooltip.forgottenitems.bound_tools.desc"));
	}

}
