package tschipp.forgottenitems.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.util.FIHelper;
import tschipp.tschipplib.item.TSPickaxe;
import net.minecraft.client.resources.I18n;

public class ItemGamblePickaxe extends TSPickaxe {

	protected ItemGamblePickaxe(ToolMaterial material) {
		super("gamble_pickaxe", material, FIM.MODID);
		this.setCreativeTab(FIM.forgottenItems);
		FIHelper.setOutputCore(2, this, Items.DIAMOND_PICKAXE);
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
		tooltip.add(I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".desc"));
    }
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking() && !world.isRemote && player.isCreative())
		{
			FIHelper.printCraftingRecipe(world, player, 2);
		}
		return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}


}
