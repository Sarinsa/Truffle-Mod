package com.trufflemod.proxy;

import com.trufflemod.block.BlockCauldronFoundation;
import com.trufflemod.block.BlockEmil;
import com.trufflemod.client.settings.Keybinding;
import com.trufflemod.entity.*;
import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.initialize.ModItems;
import com.trufflemod.renderer.*;
import com.trufflemod.tileentity.TileEntityCampFire;
import com.trufflemod.tileentity.TileEntityGrindStone;
import com.trufflemod.tileentity.TileEntityWoodenTub;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;


public class ClientProxy extends CommonProxy {



	@Override
	public void registerRenderInfo() {

		//Objects
		TMTileEntitySpecialRenderer renderWoodenTub = new BlockWoodenTubRenderer();
		TMTileEntitySpecialRenderer renderGrindStone = new BlockGrindStoneRenderer();
		TMTileEntitySpecialRenderer renderCampFire = new BlockCampFireRenderer();
		ISimpleBlockRenderingHandler emilBlockRender = new BlockCauldronFoundationRenderer();


		//TileEntity Special Renders
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodenTub.class, renderWoodenTub);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrindStone.class, renderGrindStone);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampFire.class, renderCampFire);

		//Entity/Block Renders
		RenderingRegistry.registerEntityRenderingHandler(EntityPotionDouchebaggery.class, new RenderSnowball(ModItems.potionDouche));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(ModItems.unstableHappiness));
		RenderingRegistry.registerEntityRenderingHandler(EntityPineCone.class, new RenderSnowball(ModItems.pineCone));
		RenderingRegistry.registerEntityRenderingHandler(EntityJumperBomb.class, new RenderSnowball(ModItems.jumperBomb));
		RenderingRegistry.registerEntityRenderingHandler(EntityForestGheist.class, new EntityForestGheistRenderer());
		RenderingRegistry.registerBlockHandler(BlockCauldronFoundation.renderID, emilBlockRender);

        //Item Renders
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.woodenTub), new Item3DRenderer(new TileEntityWoodenTub(), renderWoodenTub));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.campFire), new Item3DRenderer(new TileEntityCampFire(), renderCampFire));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.grindStone), new Item3DRenderer(new TileEntityGrindStone(), renderGrindStone));
	}

	@Override
	public void registerKeyBindings() {

		ClientRegistry.registerKeyBinding(Keybinding.toggle_info);
	}
}
