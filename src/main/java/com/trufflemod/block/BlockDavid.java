package com.trufflemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;


public class BlockDavid extends TMBlock {


    public BlockDavid() {
        super(Material.sand);
        this.setName("blockDavid");
        this.setHardness(222.22f);
        this.setResistance(200);
        this.setStepSound(soundTypeGravel);
        this.setHarvestLevel("shovel", 1);

    }


    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if (world.isBlockIndirectlyGettingPowered(x, y, z))
        {
            this.onBlockDestroyedByPlayer(world, x, y, z, 1);
            world.setBlockToAir(x, y, z);
        }
    }



    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);

        if (world.isBlockIndirectlyGettingPowered(x, y, z))
        {
            this.onBlockDestroyedByPlayer(world, x, y, z, 1);
            world.setBlockToAir(x, y, z);
        }
    }


    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
    {
        this.explodeBlock(world, x, y, z, (EntityLivingBase)null);
    }


    public void explodeBlock(World world, int x, int y, int z, EntityLivingBase entity)
    {
        if (!world.isRemote)
        {
                EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), entity);
                world.spawnEntityInWorld(entitytntprimed);
                world.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
        }
    }
}
