package tschipp.forgottenitems.items;

import java.util.Random;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import tschipp.forgottenitems.util.FIConfig;

public class ItemEnderTalisman extends ItemTalisman {

	public ItemEnderTalisman() {
		super("ender_talisman", "item.ender_talisman.lore", 18, ItemList.enderGem);
		this.setMaxDamage(1000);
	}


	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		super.onItemRightClick(world, player, hand);
		ItemStack stack = player.getHeldItem(hand);
		RayTraceResult ray = this.rayTrace(world, player, false);
		if(ray != null)
		{
			BlockPos pos = ray.getBlockPos().offset(ray.sideHit);
			player.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
			stack.damageItem(new Random().nextInt(20), player);
	        player.getCooldownTracker().setCooldown(this, FIConfig.enderTalismanCooldown);
			if(!world.isRemote)
				((WorldServer)world).spawnParticle(EnumParticleTypes.PORTAL, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.25D, (double)pos.getZ() + 0.5D, 80, 0.0d, 0.5D, 0.0D, 0.0D, new int[0]);
			world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 0.6F, 0.8F / (itemRand.nextFloat() * 0.1F + 0.8F));
			return new ActionResult(EnumActionResult.SUCCESS, stack);
		}
		else
		{
			if(world.isRemote)
				player.sendMessage(new TextComponentTranslation("notif.forgottenitems.ender_talisman.outofrange"));
		}
		
		return new ActionResult(EnumActionResult.PASS, stack);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemList.enderGem;
	}



	protected RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids)
	{
		float f = playerIn.rotationPitch;
		float f1 = playerIn.rotationYaw;
		double d0 = playerIn.posX;
		double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
		double d2 = playerIn.posZ;
		Vec3d vec3d = new Vec3d(d0, d1, d2);
		float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
		float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
		float f4 = -MathHelper.cos(-f * 0.017453292F);
		float f5 = MathHelper.sin(-f * 0.017453292F);
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d3 = FIConfig.enderTalismanRange;

		Vec3d vec3d1 = vec3d.addVector((double)f6 * d3, (double)f5 * d3, (double)f7 * d3);
		return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
	}

}
