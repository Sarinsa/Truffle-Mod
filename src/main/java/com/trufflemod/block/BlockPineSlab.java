package com.trufflemod.block;

import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.lib.Reference;
import com.trufflemod.utils.ShadowUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

public class BlockPineSlab extends BlockSlab {

    public BlockPineSlab(boolean bool) {
        super(bool, Material.wood);
        opaque = bool;

        this.setBlockTextureName(Reference.MODID + ":pinePlanks");
        this.setHardness(1.6F);
        this.setResistance(0.3F);
        this.setLightOpacity(0);
        this.setStepSound(soundTypeWood);
    }


    @Override
    public String func_150002_b(int meta) {

        return null;
    }


    @Override
    public boolean isOpaqueCube() {

        return opaque;
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        if (!opaque && player.getHeldItem().getItem() == Item.getItemFromBlock(ModBlocks.pineSlab)) {

            if (side == 1 || side == 0) {

                if (ShadowUtil.isBlockPlaceable(world, x, y, z, ModBlocks.double_pineSlab)) {
                    world.setBlock(x, y, z, ModBlocks.double_pineSlab);

                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {

        return Item.getItemFromBlock(ModBlocks.pineSlab);
    }


    @Override
    public int quantityDropped(int meta, int fortune, Random random) {

        return opaque ? 2 : 1;
    }


    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {

        return opaque ? meta : (side != 0 && (side == 1 || hitY <= 0.5F) ? meta : meta | 8);
    }



    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World world, int x, int y, int z) {

        return Item.getItemFromBlock(ModBlocks.pineSlab);
    }
}
