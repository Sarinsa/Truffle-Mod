package com.trufflemod.block;

import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.initialize.ModItems;
import com.trufflemod.lib.Reference;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;


public class BlockPineCone extends BlockBush {

    public BlockPineCone() {
        super(Material.plants);
        this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.7F, 0.7F);
        this.setBlockName("pineCone");
        this.setBlockTextureName(Reference.MODID + ":pineCone");
        this.setHardness(0.4f);
        this.setCreativeTab(null);
        this.setStepSound(soundTypeGrass);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        return null;
    }


    public boolean isOpaqueCube()
    {
        return false;
    }


    public boolean renderAsNormalBlock()
    {
        return false;
    }


    public int getRenderType() {
        return 1;
    }



    @Override
    public Item getItemDropped(int metadata, Random random, int fortune)
    {
        return ModItems.pineCone;
    }




    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.getBlock(x, y + 1, z) == ModBlocks.pineLeaves;
    }


    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return this.canBlockStay(world, x, y, z);

    }
}