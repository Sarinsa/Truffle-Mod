package com.trufflemod.block;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.lib.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

public class FlowerGaliumV extends BlockBush {

    public FlowerGaliumV() {
        super(Material.grass);
        this.setBlockName("galiumVerum");
        this.setBlockTextureName(Reference.MODID + ":galiumVerum");
        this.setCreativeTab(CreativeTabTM.truffleModTab);
        this.setStepSound(soundTypeGrass);
        this.setResistance(0.1f);
        this.setHardness(0f);
    }


    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.getBlock(x, y - 1, z) == Blocks.grass || world.getBlock(x, y, z) == Blocks.dirt;
    }


    @Override
    public boolean canPlaceBlockOn(Block block) {
        return block == Blocks.grass || block == Blocks.dirt;
    }


    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return super.canPlaceBlockAt(world, x, y, z) && this.canBlockStay(world, x, y, z);
    }
}
