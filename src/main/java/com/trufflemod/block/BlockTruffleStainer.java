package com.trufflemod.block;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;

public class BlockTruffleStainer extends TMBlock {

    protected BlockTruffleStainer() {
        super(Material.wood);
        this.setBlockName("blockTruffleStainer");
        this.setBlockTextureName("truffleStainer");
        this.setHardness(2F);
        this.setResistance(1F);
        this.setStepSound(soundTypeStone);
    }


    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
