package tschipp.forgottenitems;

import java.io.File;
import java.util.Random;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tschipp.forgottenitems.crafting.CraftingRegister;
import tschipp.forgottenitems.items.ItemList;
import tschipp.forgottenitems.network.ForbiddenPacket;
import tschipp.forgottenitems.network.ForbiddenPacketHandler;
import tschipp.forgottenitems.network.SeedPacket;
import tschipp.forgottenitems.network.SeedPacketHandler;
import tschipp.forgottenitems.util.CommonProxy;
import tschipp.forgottenitems.util.FIConfig;
import tschipp.forgottenitems.util.FIEvents;
import tschipp.forgottenitems.util.FIHelper;


@Mod(modid = "forgottenitems", name = "Forgotten Items", version = "1.2.1", dependencies = "required-after:tschipplib", acceptedMinecraftVersions = "[1.12,1.13)")

public class FIM 
{
	public final static String MODID = "forgottenitems";

	
	@Instance(value = MODID)
	public static FIM instance;

	public static final String COMMON_PROXY = "tschipp.forgottenitems.util.CommonProxy";
	public static final String CLIENT_PROXY = "tschipp.forgottenitems.util.ClientProxy";

	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
	public static CommonProxy proxy;
	
	public static SimpleNetworkWrapper network;

	public static Configuration config;
	public static Configuration bannedItemConfig;
	public static Configuration customRecipesConfig;

	public static final int RECIPE_AMOUNT = 25;
	
	public static final Random RANDOM = new Random();
	public static Logger LOGGER;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		for(int k = 0; k < RECIPE_AMOUNT + 1; k++)
		{
			FIHelper.OUTPUTS_CORES.add(null);
		}
		LOGGER = event.getModLog();

		config = new Configuration(new File(event.getModConfigurationDirectory(), "ForgottenItems.cfg"));
		bannedItemConfig = new Configuration(new File(event.getModConfigurationDirectory(), "ForgottenItemsBannedList.cfg"));
		customRecipesConfig = new Configuration(new File(event.getModConfigurationDirectory(), "ForgottenItemsCustomRecipes.cfg"));
		
		FIConfig.syncConfig();
		
		proxy.preInit(event);

		network = NetworkRegistry.INSTANCE.newSimpleChannel("ForgottenItems");
		network.registerMessage(SeedPacketHandler.class, SeedPacket.class, 0, Side.CLIENT);
		network.registerMessage(ForbiddenPacketHandler.class, ForbiddenPacket.class, 1, Side.CLIENT);
		

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		MinecraftForge.EVENT_BUS.register(new FIEvents());
		CraftingRegister.registerRecipes();
	}


	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);

	}


	public static CreativeTabs forgottenItems = new CreativeTabs("forgottenItems"){
		@Override
		public ItemStack getTabIconItem(){
			return new ItemStack(ItemList.explosionPickaxe);
		}
	};




}






