package tschipp.forgottenitems.util;

import java.util.ArrayList;
import java.util.List;

import tschipp.forgottenitems.FIM;

public class FIConfig {

	//CRAFTING
	public static String[] bannedItems;
	public static boolean useNonVanillaItems;
	public static String[] customCraftingRecipes;
	public static boolean useWhitelist;
	public static String[] whitelist;

	//ITEMS
	public static float explosionPickaxeMultiplier;
	public static float gamblePickaxeChance;
	public static float gamblePickaxeFortuneChance;
	public static int veinPickaxeRadius;
	public static boolean heatTalismanCreatesItems;
	public static float shockTalismanExplosionMultiplier;
	public static float windTalismanMaxVelocity;
	public static float windTalismanVelocityMultiplier;
	public static int enderTalismanRange;
	public static int windTalismanCooldown;
	public static int enderTalismanCooldown;


	//MISC
	public static boolean recipeGivesItems;
	public static String[] customBoundColors;
	public static boolean showRecipeOutput;
	public static boolean showRecipeOutputCreative;




	//BLOCKS
	public static boolean runeReaderEmitsParticles;


	public static void syncConfig()
	{
		try {
			FIM.config.load();

			//CRAFTING
			useNonVanillaItems = FIM.config.getBoolean("useNonVanillaItems", "crafting", true, "True if non-vanilla Items should be part of the Crafting Recipes. False for not.");
			useWhitelist = FIM.config.getBoolean("useWhitelist", "crafting", false, "Use the whitelist instead of all items when creating the recipes.");
			whitelist = FIM.config.getStringList("whitelist", "crafting", new String[0], "List of Items/Blocks that will be used to create the recipes. Insert 'MODID:* ' to disable every item of a certain mod. You can also disable certain categories of a mod, Example: 'harvestcraft:pam*");


			//ITEMS
			explosionPickaxeMultiplier = FIM.config.getFloat("explosionPickaxeMultiplier", "items", 3.5F, 0F, 10F, "How strong the Explosion Pickaxe's explosion should be, relative to the broken block");
			gamblePickaxeChance = FIM.config.getFloat("gamblePickaxeChance", "items", 0.3F, 0F, 1F, "Base Chance for the Gamble Pickaxe to duplicate a block");
			gamblePickaxeFortuneChance = FIM.config.getFloat("gamblePickaxeFortuneChance", "items", 0.1F, 0F, 10F, "How much chance each level of fortune on the Gamble Pickaxe adds");
			veinPickaxeRadius = FIM.config.getInt("veinPickaxeRadius", "items", 6, 0, 15, "The radius in which the Vein Pickaxe breaks blocks");
			heatTalismanCreatesItems = FIM.config.getBoolean("heatTalismanCreatesItems", "items", false, "If the heat talisman should spawn an item if the smelted block has an item output(Like Iron ore -> Iron Ingot)");
			shockTalismanExplosionMultiplier = FIM.config.getFloat("shockTalismanExplosionMultiplier", "items", 0.5F, 0F, 5F, "Explosion Multiplier for Shock Talisman");
			windTalismanMaxVelocity = FIM.config.getFloat("windTalismanMaxVelocity", "items", 5F, 0F, 50F, "Maximum Velocity that the player can have in order to activate the Wind Talisman");
			windTalismanVelocityMultiplier = FIM.config.getFloat("windTalismanVelocityMultiplier", "items", 1.2F, 0F, 10F, "Velocity Multiplier for Wind Talisman");
			enderTalismanRange = FIM.config.getInt("enderTalismanRange", "items", 50, 0, 200, "Range of the Ender Talisman");
			windTalismanCooldown = FIM.config.getInt("windTalismanCooldown", "items", 20, 0, Integer.MAX_VALUE, "Wind Talisman cooldown time (in ticks). Set to 0 to disable");
			enderTalismanCooldown = FIM.config.getInt("enderTalisman", "items", 25, 0, Integer.MAX_VALUE, "Ender Talisman cooldown time (in ticks). Set to 0 to disable");


			//MISC
			recipeGivesItems = FIM.config.getBoolean("recipeGivesItems", "misc", false, "Set to true if shift-clicking a item in creative mode should give the items to craft it");
			customBoundColors = FIM.config.getStringList("customBoundColors", "misc", new String[]{"Tschipp", "16761856", "Janunalai", "65280", "Nerval_", "12517631", "Barbamos", "11337728"}, "Add new custom bound tool colors for your player. First, type the name of the player, then the color (A hex color converted to decimal)");
			showRecipeOutput = FIM.config.getBoolean("showRecipeOutput", "misc", false, "Set to true the output of a crafting rune is always visible");
			showRecipeOutputCreative = FIM.config.getBoolean("showRecipeOutputCreative", "misc", true, "Set to true if the output of a crafting rune is visible in Creative only.");
			

			//BLOCKS
			runeReaderEmitsParticles = FIM.config.getBoolean("runeReaderEmitsParticles", "blocks", true, "Whether the Rune Reader should emit particles");


		} catch (Exception e) {

		} finally {

			if (FIM.config.hasChanged()) FIM.config.save();
		}



		//Banned Item List
		try {
			FIM.bannedItemConfig.load();

			bannedItems = FIM.bannedItemConfig.getStringList("bannedItems", "bannedItems", getBannedItems(), "List of Items/Blocks that are not allowed to be part of a crafting recipe. Insert 'MODID:* ' to disable every item of a certain mod. You can also disable certain categories of a mod, Example: 'harvestcraft:pam*");



		} catch (Exception e) {

		} finally {

			if (FIM.bannedItemConfig.hasChanged()) FIM.bannedItemConfig.save();
		}



		//Custom Recipe Config
		try {
			FIM.customRecipesConfig.load();

			customCraftingRecipes = FIM.customRecipesConfig.getStringList("customCraftingRecipes", "customCraftingRecipes", new String[]{}, "Create crafting recipes for other items! How to do it: On the first line, insert the name of the core item. On the second line, insert the name of the Output");



		} catch (Exception e) {

		} finally {

			if (FIM.customRecipesConfig.hasChanged()) FIM.customRecipesConfig.save();
		}


	}


	private static String[] getBannedItems()
	{
		List<String> l = new ArrayList<String>();
		l.add("minecraft:air");
		l.add("minecraft:spawn_egg");
		l.add("minecraft:command_block");
		l.add("minecraft:repeating_command_block");
		l.add("minecraft:chain_command_block");
		l.add("minecraft:command_block_minecart");
		l.add("minecraft:bedrock");
		l.add("minecraft:mob_spawner");
		l.add("minecraft:dragon_egg");
		l.add("minecraft:end_portal_frame");
		l.add("minecraft:farmland");
		l.add("minecraft:barrier");
		l.add("minecraft:structure_void");
		l.add("minecraft:monster_egg");
		l.add("minecraft:structure_block");
		l.add("minecraft:potion");
		l.add("minecraft:splash_potion");
		l.add("minecraft:lingering_potion");
		l.add("minecraft:tipped_arrow");
		l.add("minecraft:white_shulker_box");
		l.add("minecraft:orange_shulker_box");
		l.add("minecraft:magenta_shulker_box");
		l.add("minecraft:light_blue_shulker_box");
		l.add("minecraft:yellow_shulker_box");
		l.add("minecraft:lime_shulker_box");
		l.add("minecraft:pink_shulker_box");
		l.add("minecraft:gray_shulker_box");
		l.add("minecraft:silver_shulker_box");
		l.add("minecraft:cyan_shulker_box");
		l.add("minecraft:purple_shulker_box");
		l.add("minecraft:blue_shulker_box");
		l.add("minecraft:brown_shulker_box");
		l.add("minecraft:green_shulker_box");
		l.add("minecraft:red_shulker_box");
		l.add("minecraft:black_shulker_box");
		l.add("minecraf:chorus_plant");
		l.add("minecraft:grass_path");
		l.add("buildingblocks:gravelgrass");
		l.add("harvestcraft:aridgarden");
		l.add("harvestcraft:frostgarden");
		l.add("harvestcraft:shadedgarden");
		l.add("harvestcraft:soggygarden");
		l.add("harvestcraft:tropicalgarden");
		l.add("harvestcraft:windygarden");
		l.add("harvestcraft:pam*");
		l.add("harvestcraft:carrotcake");
		l.add("harvestcraft:cheesecake");
		l.add("harvestcraft:cherrycheesecake");
		l.add("harvestcraft:chocoloatesprinklecake");
		l.add("harvestcraft:holidaycake");
		l.add("harvestcraft:lamingtoncake");
		l.add("harvestcraft:pavlovacake");
		l.add("harvestcraft:pineappleupsidedowncake");
		l.add("harvestcraft:pumpkincheesecake");
		l.add("harvestcraft:redvelvetcake");
		l.add("creative*");
		l.add("rftools:shard_wand");
		l.add("rftools:developers_delight");
		l.add("rftools:teleporter_probe");
		l.add("rftools:support_block");
		l.add("rftools:invisible_shield_block");
		l.add("rftools:notick_invisible_shield_block");
		l.add("rftools:solid_shield_block");
		l.add("rftools:notick_solid_shield_block");
		l.add("rftools:screen_hitblock");
		l.add("forestry:fluid*");
		l.add("chisel:offsettool");
		l.add("chisel:ironpane");
		l.add("bloodmagic:itemsacrificialdagger");
		l.add("bloodmagic:itemactivationcrystal");
		l.add("bloodmagic:itemupgradetome");
		l.add("bloodmagic:blockphantom");
		l.add("actuallyadditions:block_canola_oil");
		l.add("actuallyadditions:block_oil");
		l.add("actuallyadditions:block_crystal_oil");
		l.add("actuallyadditions:block_empowered_oil");
		l.add("actuallyadditions:block_wild");
		l.add("actuallyadditions:block_rice");
		l.add("biomesoplenty:earth");
		l.add("biomesoplenty:turnip_block");
		l.add("biomesoplenty:grass_path");
		l.add("biomesoplenty:farmland_0");
		l.add("biomesoplenty:farmland_1");
		l.add("biomesoplenty:plant_0");
		l.add("biomesoplenty:plant_1");
		l.add("biomesoplenty:flower_0");
		l.add("biomesoplenty:flower_1");
		l.add("biomesoplenty:sapling_0");
		l.add("biomesoplenty:sapling_1");
		l.add("biomesoplenty:sapling_2");
		l.add("biomesoplenty:stone_formations");
		l.add("biomesoplenty:biome_essence");
		l.add("extrautils2:passivegenerator");
		l.add("extrautils2:drum");
		l.add("extrautils2:spotlight");
		l.add("extrautils2:fakecopy");
		l.add("creativeplus:*");
		l.add("forgottenitems:*");


		String[] array = l.toArray(new String[]{});

		return array;
	}




}
