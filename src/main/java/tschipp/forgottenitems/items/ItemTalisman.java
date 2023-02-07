package tschipp.forgottenitems.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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

public class ItemTalisman extends TSItem {
	
	private String lore;
	private int reicpeID;
	
	public ItemTalisman(String name, String lore, int recipeID, Item core) {
		super(name, FIM.MODID);
		this.lore = lore;
		this.reicpeID = recipeID;
		this.setMaxStackSize(1);
		this.setCreativeTab(FIM.forgottenItems);
		FIHelper.setOutputCore(recipeID, this, core);

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
		tooltip.add(this.lore);
    }
	
	
	@SideOnly(Side.CLIENT)
	@Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking() && !world.isRemote && player.isCreative())
		{
			FIHelper.printCraftingRecipe(world, player, this.reicpeID);
		}
		return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	

}
