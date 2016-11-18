package com.trufflemod.block;

import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class CropNetherTSoil extends TMBlock {

    private IIcon stage0;
    private IIcon stage1;
    private IIcon stage2;


    public CropNetherTSoil() {
        super(Material.sand);
        this.setTickRandomly(true);
        this.setName("netherTSoilCrop");
        this.setCreativeTab(null);
        this.setHardness(1.0f);
        this.setResistance(1.0f);
        this.setHarvestLevel("shovel", 1);
        this.setStepSound(soundTypeSand);


    }



    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {

        if (random.nextInt(100) <= 1) {

            if (world.provider.dimensionId == -1) {

                if (world.getBlockMetadata(x, y, z) < 3) {

                    world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 1, 3);
                }
                else
                {
                    world.setBlock(x, y, z, ModBlocks.netherTSoil);
                }
            }
        }
    }


    @Override
    public Item getItemDropped(int fortune, Random random, int meta)  {

        return null;
    }


    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox((double)x, (double)y, (double)z, (double)(x + 1), (double)((float)(y + 1) - f), (double)(z + 1));
    }



    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister icon) {

        stage0 = icon.registerIcon(this.getTextureName() + "_stage_0");
        stage1 = icon.registerIcon(this.getTextureName() + "_stage_1");
        stage2 = icon.registerIcon(this.getTextureName() + "_stage_2");
    }


    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {

        switch (meta) {

            case 0:
                return stage0;
            case 1:
                return stage1;
            case 2:
                return stage2;
        }

        return stage2;
    }
}

