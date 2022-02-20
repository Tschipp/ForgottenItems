package tschipp.forgottenitems.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetDamage;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraft.world.storage.loot.functions.SetNBT;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.items.ItemList;
import tschipp.forgottenitems.network.ForbiddenPacket;
import tschipp.forgottenitems.network.SeedPacket;

public class FIEvents {

	//Stores the bound tools
	//TODO: Find another way to do this
	public static Map<String, List<ItemStack>> playerandtools = new HashMap<String, List<ItemStack>>();


	/**
	 * Called when a block is broken and a drop appears
	 * @param event
	 */
	@SubscribeEvent
	public void onHarvestDrop(HarvestDropsEvent event)
	{
		World world = event.getWorld();
		EntityPlayer player = event.getHarvester();
		IBlockState state = event.getState();
		List<ItemStack> drops = event.getDrops();
		List<ItemStack> logs = OreDictionary.getOres("logWood");
		Block block = state.getBlock();

		//Gamble Pickaxe
		if(player != null && !player.getHeldItemMainhand().isEmpty())
		{
			if(!drops.isEmpty() && player.getHeldItemMainhand().getItem() == ItemList.gamblePickaxe)
			{
				ItemStack harveststack = player.getHeldItemMainhand();

				int fortunelevel = event.getFortuneLevel();
				Random rand = new Random();

				if(rand.nextFloat() < FIConfig.gamblePickaxeChance + (fortunelevel * FIConfig.gamblePickaxeFortuneChance))
				{
					ItemStack drop = drops.get(0);
					drop.grow(drop.getCount());
					drops.set(0, drop);
				}
				else
				{
					drops.clear();
				}

			}
			//Barkified Axe
			else if(Loader.isModLoaded("barkifier") && player.getHeldItemMainhand().getItem() == ItemList.barkifiedAxe)
			{
				ItemStack heldItem = player.getHeldItemMainhand();

				if(state.getProperties().containsKey(BlockLog.LOG_AXIS))
				{
					int meta = block.getMetaFromState(state.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.NONE));

					ItemStack stack = new ItemStack(Item.getByNameOrId("barkifier:barkified_log"), 1);
					NBTTagCompound tag = new NBTTagCompound();
					tag.setString("block", block.getRegistryName().toString());
					tag.setInteger("meta", meta);
					stack.setTagCompound(tag);

					drops.clear();
					drops.add(stack);
				}
				else 
				{
					for(int i = 0; i < logs.size(); i++)
					{
						if(logs.get(i).getItem() == Item.getItemFromBlock(state.getBlock()))
						{

							int meta = block.getMetaFromState(state);


							if(meta <= 3) {
								meta = meta + 12;
							} 
							else if(meta >= 4 && meta <= 7) {
								meta = meta + 8;

							} 
							else if(meta >= 8 && meta <= 11) {
								meta = meta + 4;

							} 


							ItemStack stack = new ItemStack(Item.getByNameOrId("barkifier:barkified_log"), 1);
							NBTTagCompound tag = new NBTTagCompound();
							tag.setString("block", block.getRegistryName().toString());
							tag.setInteger("meta", meta);
							stack.setTagCompound(tag);

							drops.clear();
							drops.add(stack);


						}
					}
				}

			}
		}


	}




	/**
	 * Fired when an entity updates
	 * @param event
	 */
	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase living = event.getEntityLiving();

		if(living instanceof EntityPlayer && !(living instanceof FakePlayer))
		{
			EntityPlayer player = (EntityPlayer) living;
			IBlockState block = player.world.getBlockState(player.getPosition());

			if((block.getMaterial() == Material.WATER || block.getMaterial() == Material.LAVA) && !player.isCreative())
			{
				if(FIHelper.hasArmorItem(ItemList.golemHelmet, player))
				{
					player.addVelocity(0, -0.01, 0);
				}
				if(FIHelper.hasArmorItem(ItemList.golemChestplate, player))
				{
					player.addVelocity(0, -0.01, 0);
				}
				if(FIHelper.hasArmorItem(ItemList.golemLeggings, player))
				{
					player.addVelocity(0, -0.01, 0);
				}
				if(FIHelper.hasArmorItem(ItemList.golemBoots, player))	
				{
					player.addVelocity(0, -0.01, 0);
				}
				if(FIHelper.hasArmorItem(ItemList.lifebelt, player))	
				{
					player.addVelocity(0, 0.05, 0);
				}

			}


		}
	}






	/**
	 * Gets fired when a living dies
	 * @param event
	 */
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event)
	{
		EntityLivingBase living = event.getEntityLiving();

		if(living instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) living;
			List<ItemStack> boundTools = new ArrayList<ItemStack>();
			String playername = player.getGameProfile().getName();

			while(FIHelper.hasItem(ItemList.boundPickaxe, player))
			{
				int slot = FIHelper.getSlotForItem(ItemList.boundPickaxe, player);
				ItemStack pick = player.inventory.getStackInSlot(slot);
				boundTools.add(pick);		
				player.inventory.removeStackFromSlot(slot);
			}

			while(FIHelper.hasItem(ItemList.boundAxe, player))
			{
				int slot = FIHelper.getSlotForItem(ItemList.boundAxe, player);
				ItemStack axe = player.inventory.getStackInSlot(slot);
				boundTools.add(axe);		
				player.inventory.removeStackFromSlot(slot);
			}

			while(FIHelper.hasItem(ItemList.boundShovel, player))
			{
				int slot = FIHelper.getSlotForItem(ItemList.boundShovel, player);
				ItemStack shovel = player.inventory.getStackInSlot(slot);
				boundTools.add(shovel);		
				player.inventory.removeStackFromSlot(slot);
			}

			playerandtools.put(playername, boundTools);
		}
	}


	/**
	 * Gets called when a player respawns
	 * @param event
	 */
	@SubscribeEvent
	public void onRespawn(PlayerEvent.Clone event)
	{
		EntityPlayer newPlayer = event.getEntityPlayer();
		EntityPlayer oldPlayer = event.getOriginal();

		if(event.isWasDeath() && !(newPlayer instanceof FakePlayer))
		{
			String playername = newPlayer.getGameProfile().getName();

			List<ItemStack> tools = playerandtools.get(playername);

			if(tools != null)
			{
				for(int i = 0; i < tools.size(); i++)
				{
					newPlayer.inventory.addItemStackToInventory(tools.get(i));
				}

				playerandtools.remove(playername);
			}

		}
	} 



	/**
	 * Called when a living takes damage
	 * @param event
	 */
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		DamageSource source = event.getSource();
		EntityLivingBase entity = event.getEntityLiving();
		Random rand = new Random();


		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			float amount = event.getAmount();

			if(FIHelper.hasItem(ItemList.shockTalisman, player) && (source == DamageSource.FALL || source == DamageSource.FLY_INTO_WALL))
			{
				if(!player.world.isRemote)
				{
					player.capabilities.disableDamage = true;
					if(FIHelper.hasArmorItem(ItemList.cushionedBoots, player))
						player.world.createExplosion(player, player.posX, player.posY, player.posZ, amount * FIConfig.shockTalismanExplosionMultiplier * 0.25f, true);
					else
						player.world.createExplosion(player, player.posX, player.posY, player.posZ, amount * FIConfig.shockTalismanExplosionMultiplier, true);
					player.capabilities.disableDamage = false;

					int slot = FIHelper.getSlotForItem(ItemList.shockTalisman, player);
					ItemStack stack = player.inventory.getStackInSlot(slot);
					stack.damageItem((int) amount, player);
					player.inventory.setInventorySlotContents(slot, stack);
				}
				event.setCanceled(true);
			}
			if(FIHelper.hasArmorItem(ItemList.cushionedBoots, player)  && (source == DamageSource.FALL || source == DamageSource.FLY_INTO_WALL))
			{
				event.setAmount(amount * 0.25f);
				if(rand.nextFloat() > 0.35)
				{
					int slot = FIHelper.getSlotForItem(ItemList.cushionedBoots, player);
					ItemStack stack = player.inventory.getStackInSlot(slot);
					stack.damageItem((int) (amount * rand.nextFloat()), player);
					player.inventory.setInventorySlotContents(slot, stack);
				}
			}

		}
	}




	/**
	 * Called when a player picks up an Item
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerPickupItem(EntityItemPickupEvent event)
	{
		EntityItem item = event.getItem();
		EntityPlayer player = event.getEntityPlayer();
		ItemStack stack = item.getItem();

		if(!stack.isEmpty() && player != null && stack.getItem() == ItemList.boundPickaxe)
		{
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey("owner"))
			{
				String owner = stack.getTagCompound().getString("owner");
				String playername = player.getGameProfile().getName();

				if(!owner.equals(playername))
					event.setCanceled(true);

			}
		}

		if(!stack.isEmpty() && player != null && stack.getItem() == ItemList.boundAxe)
		{
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey("owner"))
			{
				String owner = stack.getTagCompound().getString("owner");
				String playername = player.getGameProfile().getName();

				if(!owner.equals(playername))
					event.setCanceled(true);

			}
		}

		if(!stack.isEmpty() && player != null && stack.getItem() == ItemList.boundShovel)
		{
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey("owner"))
			{
				String owner = stack.getTagCompound().getString("owner");
				String playername = player.getGameProfile().getName();

				if(!owner.equals(playername))
					event.setCanceled(true);

			}
		}
	}


	/**
	 * Gets called when loot tables get loaded
	 * Used to add the Crafting runes to the loot tables
	 * @param event
	 */
	@SubscribeEvent
	public void onLootTableLoad(LootTableLoadEvent event)
	{
		LootTable table = event.getTable();
		String name = event.getName().toString();
		ArrayList<HashMap<Item, Item>> custom = FIHelper.CUSTOM_OUTPUTS_CORES;
		int customRecipeAmount = 0;
		for(int k = 0; k < custom.size(); k++)
		{
			if(custom.get(k) != null)
			{
				customRecipeAmount++;
			}
		}

		if(name.equals("minecraft:chests/igloo_chest") || name.equals("minecraft:chests/end_city_treasure") || name.equals("minecraft:chests/stronghold_library") || name.equals("minecraft:chests/woodland_mansion") || name.equals("minecraft:chests/jungle_temple") || name.equals("minecraft:chests/desert_pyramid"))
		{
			LootEntry[] runes = new LootEntry[FIM.RECIPE_AMOUNT + customRecipeAmount + 1];
			for(int i = 1; i <= FIM.RECIPE_AMOUNT; i++)
			{
				if(i == 24 && Loader.isModLoaded("barkifier"))
				{
					NBTTagCompound tag = new NBTTagCompound();
					tag.setInteger("id", 23);
					LootEntry loot = new LootEntryItem(ItemList.craftingRune, 1, 0, new LootFunction[]{new SetNBT(new LootCondition[]{}, tag) }, new LootCondition[]{}, "crafting_rune_" + i);
					runes[i-1] = loot;
				}
				else
				{
					NBTTagCompound tag = new NBTTagCompound();
					tag.setInteger("id", i);
					LootEntry loot = new LootEntryItem(ItemList.craftingRune, 1, 0, new LootFunction[]{new SetNBT(new LootCondition[]{}, tag) }, new LootCondition[]{}, "crafting_rune_" + i);
					runes[i-1] = loot;
				}

			}
			for(int k = 0; k < custom.size(); k++)
			{
				if((k & 1) == 1)
				{
					if(custom.get(k) != null)
					{
						NBTTagCompound tag = new NBTTagCompound();
						tag.setInteger("id", k);
						LootEntry loot = new LootEntryItem(ItemList.craftingRune, 1, 0, new LootFunction[]{new SetNBT(new LootCondition[]{}, tag), new SetMetadata(new LootCondition[]{}, new RandomValueRange(1))}, new LootCondition[]{}, "crafting_rune_custom_" + k);
						runes[FIM.RECIPE_AMOUNT + k/2] = loot;
					}
				}
			}
			LootEntry air = new LootEntryItem(ItemStack.EMPTY.getItem(), (FIM.RECIPE_AMOUNT + customRecipeAmount) * 2, 0, new LootFunction[]{}, new LootCondition[]{}, "air");
			runes[runes.length-1] = air;
			table.addPool(new LootPool(runes, new LootCondition[]{}, new RandomValueRange(1f, 3f), new RandomValueRange(0.0f,0.0f), "crafting_runes"));
			event.setTable(table);

		}

	}



	/**
	 * Called when the world loads
	 * @param event
	 */
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event)
	{
		World world = event.getWorld();
		if(!world.isRemote)
		{
			long seed = Math.abs(world.getSeed());
			FIWorldSavedData.getInstance(world).setSeed(seed);
			FIWorldSavedData.getInstance(world).setForbidden(FIHelper.getForbiddenList());
			FIM.network.sendToAll(new SeedPacket(String.valueOf(seed)));
			FIM.network.sendToAll(new ForbiddenPacket(FIHelper.getForbiddenList().toString()));
			FIWorldSavedData.getInstance(world).markDirty();
		}

	}

	/**
	 * Called when a player joins the world
	 * @param event
	 */
	@SubscribeEvent 
	public void onPlayerJoin(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
	{	
		World world = event.player.world;
		if(!world.isRemote)
		{
			long seed = Math.abs(world.getSeed());
			FIWorldSavedData.getInstance(world).setSeed(seed);
			FIWorldSavedData.getInstance(world).setForbidden(FIHelper.getForbiddenList());
			FIM.network.sendToAll(new SeedPacket(String.valueOf(seed)));
			FIM.network.sendToAll(new ForbiddenPacket(FIHelper.getForbiddenList().toString()));
			FIWorldSavedData.getInstance(world).markDirty();
		}
	}


	/**
	 * Called when the player changes dimensions
	 * @param event
	 */
	@SubscribeEvent 
	public void onPlayerChangeDimension(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event)
	{
		World world = event.player.world;
		if(!world.isRemote)
		{
			long seed = Math.abs(world.getSeed());
			FIWorldSavedData.getInstance(world).setSeed(seed);
			FIWorldSavedData.getInstance(world).setForbidden(FIHelper.getForbiddenList());
			FIM.network.sendToAll(new SeedPacket(String.valueOf(seed)));
			FIM.network.sendToAll(new ForbiddenPacket(FIHelper.getForbiddenList().toString()));
			FIWorldSavedData.getInstance(world).markDirty();
		}
	}
}
