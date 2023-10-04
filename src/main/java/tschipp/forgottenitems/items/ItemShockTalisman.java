package tschipp.forgottenitems.items;

import net.minecraft.block.Block;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemShockTalisman extends ItemTalisman {

	public ItemShockTalisman() {
		super("shock_talisman", "item.shock_talisman.lore", 6, ItemList.shockGem);
		this.setMaxDamage(1000);
	}
	
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemList.shockGem;
	}

	



}
