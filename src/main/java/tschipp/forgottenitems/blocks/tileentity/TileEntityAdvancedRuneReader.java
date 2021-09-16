package tschipp.forgottenitems.blocks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tschipp.forgottenitems.items.ItemList;

public class TileEntityAdvancedRuneReader extends TileEntity implements ISidedInventory, ITickable {

	private final ItemStack[] inventoryEmpty = new ItemStack[] {ItemStack.EMPTY, ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY, ItemStack.EMPTY};
	private ItemStack[] inventory;
	private String customName;

	public TileEntityAdvancedRuneReader()
	{
		this.inventory = new ItemStack[] {ItemStack.EMPTY, ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY ,ItemStack.EMPTY};
	}
	

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.advanced_rune_reader";	

	}
	
	@Override
	public boolean hasCustomName() {

	    return this.customName != null && !this.customName.equals("");
	} 
	
	
	@Override
	public ITextComponent getDisplayName() {
	    return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}
	
	@Override
	public int getSizeInventory() {
		return 11;
	}

	public void setCustomName(String name)
	{
		this.customName = name;
	}


	public String getCustomName()
	{
		return this.customName;
	}


	@Override
	public boolean isEmpty() {
		return inventory.equals(inventoryEmpty);
	} 
	
	@Override
	public ItemStack getStackInSlot(int index) {
	    if (index < 0 || index >= this.getSizeInventory())
	        return ItemStack.EMPTY;
	    return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
	    if (!this.getStackInSlot(index).isEmpty()) {
	        ItemStack itemstack;

	        if (this.getStackInSlot(index).getCount() <= count) {
	            itemstack = this.getStackInSlot(index);
	            this.setInventorySlotContents(index, ItemStack.EMPTY);
	            this.markDirty();
	            return itemstack;
	        } else {
	            itemstack = this.getStackInSlot(index).splitStack(count);

	            if (this.getStackInSlot(index).getCount() <= 0) {
	                this.setInventorySlotContents(index, ItemStack.EMPTY);
	            } else {
	                //Just to show that changes happened
	                this.setInventorySlotContents(index, this.getStackInSlot(index));
	            }

	            this.markDirty();
	            return itemstack;
	        }
	    } else {
	        return ItemStack.EMPTY;
	    }
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
	    ItemStack stack = this.getStackInSlot(index);
	    this.setInventorySlotContents(index, ItemStack.EMPTY);
	    return stack;
	} 

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
	    if (index < 0 || index >= this.getSizeInventory())
	        return;

	    if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
	        stack.setCount(this.getInventoryStackLimit());
	        
	    if (!stack.isEmpty() && stack.getCount() == 0)
	        stack = ItemStack.EMPTY;

	    this.inventory[index] = stack;
	    this.markDirty();
	} 
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
	    return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {

	}
	
	@Override
	public void closeInventory(EntityPlayer player) {

	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index < 9 || index == 10)
		{
			return false;
		}
		return false;
		
	}
	
	@Override
	public int getField(int id) {
		return 0;
	}
	
	@Override
	public void setField(int id, int value) {

	}
	
	@Override
	public int getFieldCount() {
		return 0;
	}
	
	
	
	@Override
	public void clear() {
		for (int i = 0; i < this.getSizeInventory(); i++)
	        this.setInventorySlotContents(i, ItemStack.EMPTY);
		
	}
	
	
	
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
	    super.writeToNBT(nbt);

	    NBTTagList list = new NBTTagList();
	    for (int i = 0; i < this.getSizeInventory(); ++i) {
	        if (!this.getStackInSlot(i).isEmpty()) {
	            NBTTagCompound stackTag = new NBTTagCompound();
	            stackTag.setByte("Slot", (byte) i);
	            this.getStackInSlot(i).writeToNBT(stackTag);
	            list.appendTag(stackTag);
	        }
	    }
	    nbt.setTag("Items", list);

	    if (this.hasCustomName()) {
	        nbt.setString("CustomName", this.getCustomName());
	    }
		return nbt;
	}


	@Override
	public void readFromNBT(NBTTagCompound nbt) {
	    super.readFromNBT(nbt);

	    NBTTagList list = nbt.getTagList("Items", 10);
	    for (int i = 0; i < list.tagCount(); ++i) {
	        NBTTagCompound stackTag = list.getCompoundTagAt(i);
	        int slot = stackTag.getByte("Slot") & 255;
	        this.setInventorySlotContents(slot, new ItemStack(stackTag));
	    }

	    if (nbt.hasKey("CustomName", 8)) {
	        this.setCustomName(nbt.getString("CustomName"));
	    }
	}

 

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] {9};
	}



	@Override
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
		return  direction != EnumFacing.DOWN && index == 9;
	}



	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return direction == EnumFacing.DOWN && index == 9 && !stack.isEmpty() && stack.getItem() == ItemList.craftingRune;
	}

	

	@Override
	public void update() {
		if(this.inventory[9].isEmpty())
		{
			this.clear();
		}
				
	}
	





}
