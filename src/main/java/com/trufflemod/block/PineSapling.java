package com.trufflemod.block;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import com.trufflemod.worldgenerator.TMPineTreeGen;
import com.trufflemod.worldgenerator.TMPineTreeGen2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class PineSapling extends BlockBush implements IGrowable {


    public PineSapling() {
        super(Material.plants);
        this.setTickRandomly(true);
        this.setBlockName("pineSapling");
        this.setBlockTextureName(Reference.MODID + ":pineSapling");
        this.setBlockBounds(0.5F - 0.4F, 0.0F, 0.5F - 0.4F, 0.5F + 0.4F, 0.4F * 2.0F, 0.5F + 0.4F);
        this.setHardness(0F);
        this.setResistance(0.1F);
        this.setCreativeTab(CreativeTabTM.truffleModTab);
        this.setStepSound(soundTypeGrass);

    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return getFlammability(world, x, y, z, face) > 0;
    }


    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return 100;
    }


    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return Blocks.fire.getEncouragement(this);
    }



    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }


    public boolean isOpaqueCube() {
        return false;
    }


    public boolean renderAsNormalBlock() {
        return false;
    }


    public int getRenderType() {
        return 1;
    }



    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (!world.isRemote)
        {
            if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(15) == 0) {
                this.growTree(world, x, y, z, random);
            }
        }
    }

    private void growTree(World world, int x, int y, int z, Random random) {
        if (random.nextInt(6) == 0) {

            new TMPineTreeGen2().generate(world, random, x, y, z);
        }
        else
        {
            new TMPineTreeGen().generate(world, random, x, y, z);
        }
    }


    @Override
    protected boolean canPlaceBlockOn(Block block)
    {
        return block == Blocks.farmland || block == Blocks.dirt;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.getBlock(x, y - 1, z) == Blocks.grass || world.getBlock(x, y - 1, z) == Blocks.dirt || world.getBlock(x, y - 1, z) == Blocks.farmland;
    }


    public boolean func_149851_a(World world, int x, int y, int z, boolean bool) {
        return true;
    }

    public boolean func_149852_a(World world, Random random, int x, int y, int z) {
        return (double)world.rand.nextFloat() < 0.30D;
    }

    public void func_149853_b(World world, Random random, int x, int y, int z) {
        this.growTree(world, x, y, z, random);
    }
}
