package tschipp.forgottenitems.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.util.FIConfig;
import tschipp.forgottenitems.util.FIHelper;
import tschipp.tschipplib.item.TSPickaxe;

public class ItemVeinPickaxe extends TSPickaxe {

	protected ItemVeinPickaxe(ToolMaterial material) {
		super("vein_pickaxe", material, FIM.MODID);
		this.setCreativeTab(FIM.forgottenItems);
		FIHelper.setOutputCore(3, this, ItemList.explosionPickaxe);
	}


	public ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
	public ArrayList<BlockPos> tempPositions = new ArrayList<BlockPos>();
	public IBlockState mat;



	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		return "" + TextFormatting.AQUA + I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".name");
	}


	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player)
	{
		World world = player.world;
		if(!world.isRemote && !player.isCreative() && !player.isSneaking()) 
		{

			mat = world.getBlockState(pos);
			positions.add(pos);

			for(int i = 0; i < FIConfig.veinPickaxeRadius; i++) 
			{

				for(int j = 0; j < positions.size(); j++) 
				{

					addPositions(j, world);

				}

				positions.addAll(tempPositions);

				tempPositions.clear();
			}

			boolean shoulddrop = mat.getMaterial() != Material.CRAFTED_SNOW && mat.getMaterial() != Material.SNOW;
			int maxDam = stack.getMaxDamage();

			for(int k = 0; k < positions.size(); k++) {

				while(!stack.isEmpty() && stack.getItemDamage() <= maxDam)
				{

					if(stack.isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack) > 0)
					{
						if(shoulddrop)
						world.getBlockState(positions.get(k)).getBlock().dropBlockAsItem(world, positions.get(k), world.getBlockState(positions.get(k)), EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack));
						world.destroyBlock(positions.get(k), false);
					}
					else if (stack.isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0)
					{
						if(shoulddrop)
						world.getBlockState(positions.get(k)).getBlock().harvestBlock(world, player, positions.get(k), world.getBlockState(positions.get(k)), null, stack);
						world.destroyBlock(positions.get(k), false);
					}
					else
						world.destroyBlock(positions.get(k), shoulddrop);

					stack.damageItem(1, player);
					break;
				}
			}


			positions.clear();
			tempPositions.clear();

			return true;


		}

		return false;
	}



	public void addPositions(int i, World world) {
		if(world.getBlockState(positions.get(i).north()) == mat) {
			if(!positions.contains(positions.get(i).north()) && !tempPositions.contains(positions.get(i).north())) {
				tempPositions.add(positions.get(i).north());
			}
		}
		if(world.getBlockState(positions.get(i).east()) == mat) {
			if(!positions.contains(positions.get(i).east()) && !tempPositions.contains(positions.get(i).east())) {
				tempPositions.add(positions.get(i).east());
			}

		}
		if(world.getBlockState(positions.get(i).south()) == mat) {
			if(!positions.contains(positions.get(i).south()) && !tempPositions.contains(positions.get(i).south())) {
				tempPositions.add(positions.get(i).south());
			}

		}
		if(world.getBlockState(positions.get(i).west()) == mat) {
			if(!positions.contains(positions.get(i).west()) && !tempPositions.contains(positions.get(i).west())) {
				tempPositions.add(positions.get(i).west());
			}

		}
		if(world.getBlockState(positions.get(i).up()) == mat) {
			if(!positions.contains(positions.get(i).up()) && !tempPositions.contains(positions.get(i).up())) {
				tempPositions.add(positions.get(i).up());
			}

		}
		if(world.getBlockState(positions.get(i).down()) == mat) {
			if(!positions.contains(positions.get(i).down()) && !tempPositions.contains(positions.get(i).down())) {
				tempPositions.add(positions.get(i).down());
			}

		}
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
			FIHelper.printCraftingRecipe(world, player, 3);
		}
		return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}



}
