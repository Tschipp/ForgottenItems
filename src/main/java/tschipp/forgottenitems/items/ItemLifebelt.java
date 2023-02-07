package tschipp.forgottenitems.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.models.ModelLifebelt;
import tschipp.forgottenitems.util.FIHelper;

public class ItemLifebelt extends ItemArmor {

	public ItemLifebelt(ArmorMaterial materialIn) {
		super(materialIn, 1, EntityEquipmentSlot.CHEST);
		registerItem("lifebelt");
		this.setCreativeTab(FIM.forgottenItems);
		FIHelper.setOutputCore(25, this, Item.getItemFromBlock(Blocks.WOOL));

	}
	
	
	private void registerItem(String name)
	{
		super.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(FIM.MODID + ":" + name));
		ForgeRegistries.ITEMS.register(this);
	} 
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		return "" + TextFormatting.DARK_AQUA + I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".name");
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		tooltip.add(I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".desc"));
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	@Nullable
	public ModelBiped getArmorModel(EntityLivingBase living, ItemStack stack, EntityEquipmentSlot slot, ModelBiped defaultModel)
	{
		if(!stack.isEmpty())
		{
			if(stack.getItem() instanceof ItemArmor)
			{
				ModelLifebelt armorModel = FIM.proxy.getLifebeltModel();

				armorModel.bipedBody.showModel = slot == EntityEquipmentSlot.CHEST;

				armorModel.isSneak = defaultModel.isSneak;
				armorModel.isRiding = defaultModel.isRiding;
				armorModel.isChild = defaultModel.isChild;

				armorModel.rightArmPose = defaultModel.rightArmPose;
				armorModel.leftArmPose = defaultModel.leftArmPose;

				return armorModel;
			}
		}
		return null;
	}
	
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return repair.getItem() == Item.getItemFromBlock(Blocks.WOOL);
    }



	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking() && !world.isRemote && player.isCreative())
		{
			FIHelper.printCraftingRecipe(world, player, 25);
			return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));

		}
		else
		{
			super.onItemRightClick(world, player, hand);
			return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));

		}
	}

}
