package tschipp.forgottenitems.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.blocks.tileentity.TileEntityAdvancedRuneReader;
import tschipp.forgottenitems.util.FIConfig;
import tschipp.tschipplib.block.TSBlock;

public class BlockAdvancedRuneReader extends TSBlock {
	
	public static final AxisAlignedBB bb = new AxisAlignedBB( 0.0, 0.0, 0.0 , 1.0, 0.625, 1.0 );


	public BlockAdvancedRuneReader() {
		super("advanced_rune_reader", Material.ROCK, MapColor.GRAY, FIM.MODID);
		this.setCreativeTab(FIM.forgottenItems);
		this.setHardness(1.8F);
		this.setResistance(10F);
		this.setLightLevel(5F);
		this.hasTileEntity = true;
	}
	
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}


	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return bb;
	}


	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return bb;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityAdvancedRuneReader();

	}


	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if(FIConfig.runeReaderEmitsParticles)
		{

			super.randomDisplayTick(stateIn, worldIn, pos, rand);

			for (int i = -2; i <= 2; ++i)
			{
				for (int j = -2; j <= 2; ++j)
				{
					if (i > -2 && i < 2 && j == -1)
					{
						j = 2;
					}

					if (rand.nextInt(16) == 0)
					{
						for (int k = 0; k <= 1; ++k)
						{
							BlockPos blockpos = pos.add(i, k, j);

							worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double)pos.getX() + 0.5D, (double)pos.getY() + 2.0D, (double)pos.getZ() + 0.5D, (double)((float)i + rand.nextFloat()) - 0.5D, (double)((float)k - rand.nextFloat() - 1.0F), (double)((float)j + rand.nextFloat()) - 0.5D, new int[0]);

						}
					}
				}
			}
		}
	}


	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
		TileEntityAdvancedRuneReader te = (TileEntityAdvancedRuneReader) world.getTileEntity(pos);
		InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), te.getStackInSlot(9));
		super.breakBlock(world, pos, blockstate);
	}


	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (stack.hasDisplayName()) {
			((TileEntityAdvancedRuneReader) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
		}
	}


	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(FIM.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}


	
}
