package tschipp.forgottenitems.items;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.util.FIConfig;
import tschipp.forgottenitems.util.FIHelper;
import tschipp.tschipplib.item.TSPickaxe;

public class ItemExplosionPickaxe extends TSPickaxe {

	protected ItemExplosionPickaxe(ToolMaterial material) {
		super("explosion_pickaxe", material, FIM.MODID);
		this.setCreativeTab(FIM.forgottenItems);
		FIHelper.setOutputCore(1, this, Items.DIAMOND_PICKAXE);
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		return "" + TextFormatting.AQUA + I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".name");
	}



	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
	{
		World world = player.world;
		IBlockState state = world.getBlockState(pos);
		
		
		if(!world.isRemote && !player.isSneaking()) {
			if(player.capabilities.disableDamage == false)
			{
				player.capabilities.disableDamage = true;
				world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), state.getBlockHardness(world, pos) * FIConfig.explosionPickaxeMultiplier, true);
				player.capabilities.disableDamage = false;
			}
			
			if(player.capabilities.disableDamage == true)
			{
				
				world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), state.getBlockHardness(world, pos) * FIConfig.explosionPickaxeMultiplier, true);
				

			}

		}


		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
    {
		tooltip.add(I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".desc"));
    }
	

	//TESTING
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking() && !world.isRemote && player.isCreative())
		{
			FIHelper.printCraftingRecipe(world, player, 1);
		}
		return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

}
