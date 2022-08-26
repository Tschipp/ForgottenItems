package tschipp.forgottenitems.items;

import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import tschipp.forgottenitems.FIM;

public class ItemList {
	
	public static Item explosionPickaxe;
	public static Item gamblePickaxe;
	public static Item veinPickaxe;
	public static Item hastyPickaxe;
	public static Item obsidianHarvester;
	public static Item boundPickaxe;

	public static Item boundAxe;
	public static Item barkifiedAxe;
	
	public static Item boundShovel;
	
	public static Item craftingRune;
	
	public static Item shockGem;
	public static Item fireGem;
	public static Item waterGem;
	public static Item windGem;
	public static Item enderGem;

	public static Item shockTalisman;
	public static Item fireTalisman;
	public static Item waterTalisman;
	public static Item windTalisman;
	public static Item enderTalisman;

	public static Item golemHelmet;
	public static Item golemChestplate;
	public static Item golemLeggings;
	public static Item golemBoots;
	public static Item cushionedBoots;
	public static Item lifebelt;


	//TODO: POTION SWORDS
	public static ToolMaterial explosionPickMaterial = EnumHelper.addToolMaterial("Explosion Pickaxe", 2, 384, 3.5F, 2.5F, 6);
	public static ToolMaterial gamblePickMaterial = EnumHelper.addToolMaterial("Gamble Pickaxe", 3, 28, 4.5F, 2.35F, 8);
	public static ToolMaterial veinPickMaterial = EnumHelper.addToolMaterial("Vein Pickaxe", 3, 852, 4.3F, 2.56F, 5);
	public static ToolMaterial hastyPickMaterial = EnumHelper.addToolMaterial("Hasty Pickaxe", 2, 547, 25F, 2.2F, 10);
	public static ToolMaterial obsidianHarvesterMaterial = EnumHelper.addToolMaterial("Obsidian Harvester", 3, 672, 5.0F, 2.2F, 7);
	public static ToolMaterial boundToolMaterial = EnumHelper.addToolMaterial("Bound Tools", 3, 1239, 5F, 2.5F, 9);
	public static ToolMaterial barkifiedAxeMaterial = EnumHelper.addToolMaterial("Barkified Axe", 2, 898, 4.5F, 2.5F, 10);

	public static ArmorMaterial golemArmor = EnumHelper.addArmorMaterial("Golem Armor", FIM.MODID + ":golem_armor", 35, new int[]{4,6,8,5}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.5f);
	public static ArmorMaterial cushionedBootsArmor = EnumHelper.addArmorMaterial("Cushioned Boots", FIM.MODID + ":cushioned_boots", 9, new int[]{2,2,2,3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f);
	public static ArmorMaterial lifebeltArmor = EnumHelper.addArmorMaterial("Lifebelt", FIM.MODID + ":lifebelt", 14, new int[]{0,0,0,0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f);

	public static void registerItems() 
	{
		
		explosionPickaxe = new ItemExplosionPickaxe(explosionPickMaterial);
		gamblePickaxe = new ItemGamblePickaxe(gamblePickMaterial);
		veinPickaxe = new ItemVeinPickaxe(veinPickMaterial);
		hastyPickaxe = new ItemHastyPickaxe(hastyPickMaterial);
		obsidianHarvester = new ItemObsidianHarvester(obsidianHarvesterMaterial);
		boundPickaxe = new ItemBoundPickaxe(boundToolMaterial);

		if(Loader.isModLoaded("barkifier"))
			barkifiedAxe = new ItemBarkifiedAxe(barkifiedAxeMaterial);
		boundAxe = new ItemBoundAxe(boundToolMaterial);

		
		boundShovel = new ItemBoundShovel(boundToolMaterial);
		
		shockGem = new ItemGem("shock_gem", 5);
		fireGem = new ItemGem("heat_gem", 7);
		waterGem = new ItemGem("water_gem", 13);
		windGem = new ItemGem("wind_gem", 15);
		enderGem = new ItemGem("ender_gem", 17);

		shockTalisman = new ItemShockTalisman();
		fireTalisman = new ItemHeatTalisman();
		waterTalisman = new ItemWaterTalisman();
		windTalisman = new ItemWindTalisman();
		enderTalisman = new ItemEnderTalisman();

		golemHelmet = new ItemGolemArmor("golem_helmet", golemArmor, 2, EntityEquipmentSlot.HEAD, 9, Items.DIAMOND_HELMET);
		golemChestplate = new ItemGolemArmor("golem_chestplate", golemArmor, 1, EntityEquipmentSlot.CHEST, 10, Items.DIAMOND_CHESTPLATE);
		golemLeggings = new ItemGolemArmor("golem_leggings", golemArmor, 2, EntityEquipmentSlot.LEGS, 11, Items.DIAMOND_LEGGINGS);
		golemBoots = new ItemGolemArmor("golem_boots", golemArmor, 1, EntityEquipmentSlot.FEET, 12, Items.DIAMOND_BOOTS);

		cushionedBoots = new ItemCushionedBoots(cushionedBootsArmor);
		
		lifebelt = new ItemLifebelt(lifebeltArmor);
		
		craftingRune = new ItemCraftingRune();

		
		
	}
	
	

}
