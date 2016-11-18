package com.trufflemod.initialize;

import com.trufflemod.tileentity.TileEntityCampFire;
import com.trufflemod.tileentity.TileEntityEmilBlock;
import com.trufflemod.tileentity.TileEntityGrindStone;
import com.trufflemod.tileentity.TileEntityWoodenTub;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

import static com.trufflemod.initialize.ModBlocks.campFire;

public final class TileEntityHandler {


    public static void registerTileEntities() {

        GameRegistry.registerTileEntity(TileEntityGrindStone.class, "grindStone");
        GameRegistry.registerTileEntity(TileEntityWoodenTub.class, "woodenTub");
        GameRegistry.registerTileEntity(TileEntityCampFire.class, "campFire");
        GameRegistry.registerTileEntity(TileEntityEmilBlock.class, "emilBlock");

    }
}
