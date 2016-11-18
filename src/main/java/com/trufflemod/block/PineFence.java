package com.trufflemod.block;

import com.trufflemod.initialize.CreativeTabTM;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class PineFence extends BlockFence {


    public PineFence(String textureName, Material material) {
        super(textureName, material);
        this.textureName = this.getTextureName();
        this.setBlockName("pineFence");
        this.setCreativeTab(CreativeTabTM.truffleModTab);
        this.setHardness(1.6f);
        this.setResistance(0.3f);
        this.setStepSound(soundTypeWood);
        this.setHarvestLevel("axe", 0);
    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return getFlammability(world, x, y, z, face) > 0;
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return true;
    }


    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return 30;
    }


    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return Blocks.fire.getEncouragement(this);
    }


    @Override
    public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);

        return (block == Blocks.fence_gate || block == this || block.isOpaqueCube());

    }
}