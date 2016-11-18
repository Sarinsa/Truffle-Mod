package com.trufflemod.block;

import net.minecraft.block.material.Material;

public class BlockCauldronFoundation extends TMBlock {

    public static int renderID = 0;


    public BlockCauldronFoundation() {
        super(Material.wood);
        this.setName("BlockCauldronFoundation");
    }


    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
