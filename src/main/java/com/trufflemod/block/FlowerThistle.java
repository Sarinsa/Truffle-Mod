package com.trufflemod.block;

import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.initialize.ModItems;
import com.trufflemod.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class FlowerThistle extends BlockBush {

    private IIcon block;
    private IIcon blockTop;


    public FlowerThistle() {
        super(Material.plants);
        float f = 0.375F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        this.setBlockName("flowerThistle");
        this.setCreativeTab(null);
        this.setBlockTextureName(Reference.MODID + ":flowerThistle");
        this.setHardness(0f);
        this.setResistance(0f);
        this.setStepSound(soundTypeGrass);
    }



    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {

        return meta == 1 ? ModItems.thistle : null;
    }


    @Override
    protected boolean canPlaceBlockOn(Block block) {

        return block == Blocks.grass || block == ModBlocks.flowerThistle;
    }



    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {

        return super.canPlaceBlockAt(world, x, y, z) && this.canBlockStay(world, x, y, z);
    }


    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {

        return world.getBlock(x, y - 1, z) == Blocks.grass || (world.getBlock(x, y - 1, z) == ModBlocks.flowerThistle && world.getBlockMetadata(x, y - 1, z) == 0);
    }



    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {

        if (world.getBlock(x, y - 1, z) == this) {

            world.setBlockToAir(x, y - 1, z);
        }
    }




    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {

        checkAndDropBlock(world, x, y, z);
        world.notifyBlockOfNeighborChange(x, y + 1, z, this);
    }


    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

        entity.attackEntityFrom(DamageSource.cactus, 0.33F);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iIconRegister) {

        this.block = iIconRegister.registerIcon(this.getTextureName());
        this.blockTop = iIconRegister.registerIcon(this.getTextureName() + "_top");
    }


    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {

        if (meta == 0) {
            return block;
        }
        else
        {
            return blockTop;
        }
    }
}
