package tschipp.forgottenitems.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.forgottenitems.util.FIConfig;

public class ItemHeatTalisman extends ItemTalisman {

	public ItemHeatTalisman() {
		super("heat_talisman", "Stores extreme amounts of heat", 8, ItemList.fireGem);
		this.setMaxDamage(1000);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems)
	{
		if(!this.isInCreativeTab(tab))
			return;
		
		ItemStack stack = new ItemStack(this, 1, 1000);
		ItemStack stacks = new ItemStack(this, 1, 1);

		subItems.add(stack);
		subItems.add(stacks);

	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		stack.setItemDamage(1000);
	}

	public static void addHeat(ItemStack stack, int heat)
	{
		stack.setItemDamage(stack.getItemDamage() - heat);

	}

	public static void removeHeat(ItemStack stack, int heat)
	{
		stack.setItemDamage(stack.getItemDamage() + heat);
	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		
		if(player != null && player.isCreative())
		{
			tooltip.add(TextFormatting.RED + "It is not possible to charge the Talisman in Creative mode");
		}
		super.addInformation(stack, world, tooltip, flag);
	}



	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		super.onItemRightClick(world, player, hand);
		ItemStack stack = player.getHeldItem(hand);
		RayTraceResult ray = this.rayTrace(world, player, true);
		ItemStack stack2 = stack.copy();

		if(ray != null && ray.getBlockPos() != null)
		{
			BlockPos pos = ray.getBlockPos();
			if(world.getBlockState(pos).getBlock() == Blocks.LAVA)
			{
				addHeat(stack2, 10);
				world.setBlockToAir(ray.getBlockPos());
				player.setHeldItem(hand, stack2);
				return new ActionResult(EnumActionResult.SUCCESS, stack2);


			}
		}

		return new ActionResult(EnumActionResult.PASS, stack);


	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = player.getHeldItem(hand);
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		int meta = block.getMetaFromState(state);
		ItemStack blockstack = new ItemStack(block, 1, meta);
		ItemStack stack2 = stack.copy();
		if(stack.getItemDamage() < 1000)
		{
			if(FurnaceRecipes.instance().getSmeltingResult(blockstack) != null)
			{
				ItemStack output = FurnaceRecipes.instance().getSmeltingResult(blockstack);
				Block outputblock = Block.getBlockFromItem(output.getItem());
				int outputmeta = output.getMetadata();
				if(!FIConfig.heatTalismanCreatesItems)
				{
					//If output is a block
					if(outputblock != Blocks.AIR)
					{
						IBlockState outputstate = outputblock.getStateFromMeta(outputmeta);
						world.setBlockState(pos, outputstate);
						removeHeat(stack2, 1);
						if(!world.isRemote)
							((WorldServer)world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.25D, (double)pos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D, new int[0]);
						world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.3F, 0.8F / (itemRand.nextFloat() * 0.1F + 0.8F));
						player.setHeldItem(hand, stack2);
						return EnumActionResult.SUCCESS;
					}
				}
				else 
				{
					//If output is a block
					if(outputblock != Blocks.AIR)
					{
						IBlockState outputstate = outputblock.getStateFromMeta(outputmeta);
						world.setBlockState(pos, outputstate);
						removeHeat(stack2, 1);
						if(!world.isRemote)
							((WorldServer)world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.25D, (double)pos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D, new int[0]);
						world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.3F, 0.8F / (itemRand.nextFloat() * 0.1F + 0.8F));
						player.setHeldItem(hand, stack2);
						return EnumActionResult.SUCCESS;
					}

					//If output is not a block
					else if(!output.isEmpty() && !world.isRemote)
					{

						removeHeat(stack2, 1);
						output.setCount(1);
						world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, output));
						world.setBlockToAir(pos);
						if(!world.isRemote)
							((WorldServer)world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.25D, (double)pos.getZ() + 0.5D, 8, 0.25D, 0.25D, 0.25D, 0.0D, new int[0]);
						world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.3F, 0.8F / (itemRand.nextFloat() * 0.1F + 0.8F));
						player.setHeldItem(hand, stack2);
						return EnumActionResult.SUCCESS;
					}

				}

			}

		}
		return EnumActionResult.FAIL;
	}




}
