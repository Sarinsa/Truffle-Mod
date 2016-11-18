package com.trufflemod.block.ore;

import com.trufflemod.block.TMBlock;
import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.initialize.ModItems;
import com.trufflemod.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

public class NetherTruffleSoil extends TMBlock {

    public NetherTruffleSoil() {
        super(Material.sand);
        this.setName("netherTruffleSoil");
        this.setHardness(1.0f);
        this.setResistance(1.0f);
        this.setHarvestLevel("shovel", 1);
        this.setStepSound(soundTypeSand);
    }


    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        double d = 0.125F;
        return AxisAlignedBB.getBoundingBox((double)x, (double)y, (double)z, (double)(x + 1), ((double)(y + 1) - d), (double)(z + 1));
    }


    @Override
    public int quantityDropped(Random random) {

        return 1 + random.nextInt(2);
    }


    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {

        Random random = new Random();

        world.spawnParticle("smoke", (float)x + random.nextFloat(), (float)y, (float)z, 0, 0.1, 0);
        world.spawnParticle("smoke", (float)x + 0.5, (float)y - random.nextFloat(), (float)z, 0, 0.1, 0);
        world.spawnParticle("largesmoke", (float)x + 0.5 - random.nextFloat(), (float)y + random.nextFloat(), (float)z, 0, 0.1, 0);
        world.spawnParticle("largesmoke", (float)x + 0.5 - random.nextFloat(), (float)y + random.nextFloat(), (float)z - random.nextFloat(), 0, 0.1, 0);
        world.spawnParticle("smoke", (float)x + random.nextFloat(), (float)y - random.nextFloat(), (float)z, 0, 0.1, 0);

        world.spawnParticle("cloud", x + 0.5, y, z + 0.5, 0, 0.4, 0);
    }



    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {

        return ModItems.netherTruffle;
    }
}





