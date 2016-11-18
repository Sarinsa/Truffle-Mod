package com.trufflemod;

import com.trufflemod.client.handler.GuiHandler;
import com.trufflemod.client.handler.KeyInputEventHandler;
import com.trufflemod.initialize.*;
import com.trufflemod.lib.Reference;
import com.trufflemod.proxy.IProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = Reference.MODID , name = Reference.NAME , version = Reference.VERSION)
public class TruffleMod {

    @Mod.Instance(Reference.MODID)
    public static TruffleMod instance;

	@SidedProxy(clientSide = "com.trufflemod.proxy.ClientProxy", serverSide = "com.trufflemod.proxy.ServerProxy")
	public static IProxy proxy;

    private static IGuiHandler guiHandler = new GuiHandler();
	public static Logger logger = LogManager.getLogger("TruffleMod");


	@EventHandler
	public static void preLoad(FMLPreInitializationEvent preEvent){

		DungeonLootHandler.registerDungeonLoot();

        ModBlocks.registerBlocks();
        ModItems.registerItems();

		proxy.registerKeyBindings();
		proxy.registerRenderInfo();

		MinecraftForge.addGrassSeed(new ItemStack(ModItems.tomatoSeeds), 1);
		MinecraftForge.addGrassSeed(new ItemStack(ModItems.arugulaSeeds), 1);
		MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
	}


	@EventHandler
	public static void load(FMLInitializationEvent event){

		CraftingManager.registerCrafting();
        EntityHandler.registerEntities();
        TileEntityHandler.registerTileEntities();

		GameRegistry.registerFuelHandler(new FuelHandler());
		FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
		WorldGeneratorHandler.registerWorldGens();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, guiHandler);
	}


	@EventHandler
	public static void postLoad(FMLPostInitializationEvent postEvent){

	}


	@EventHandler
	public static void onLoaded(FMLLoadCompleteEvent loadEvent) {

		logger.info("Successfully loaded " + Reference.NAME + " VERSION " + Reference.VERSION);
	}
}
