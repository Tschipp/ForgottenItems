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
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.util.FIHelper;
import tschipp.tschipplib.item.TSItem;

public class ItemGem extends TSItem {

	private int reicpeID;


	public ItemGem(String name, int id) {
		super(name, FIM.MODID);
		this.reicpeID = id;
		this.setCreativeTab(FIM.forgottenItems);
		FIHelper.setOutputCore(id, this, Items.EMERALD);

	}
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		return "" + TextFormatting.YELLOW + I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".name");
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
    {
		tooltip.add(I18n.format("tooltip.forgottenitems.gems.desc"));
    }
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking() && !world.isRemote && player.isCreative())
		{
			FIHelper.printCraftingRecipe(world, player, this.reicpeID);
		}
		return new ActionResult(EnumActionResult.PASS, player.getHeldItem(hand));
	}
	

	

}
