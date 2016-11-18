package com.trufflemod.block;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockPineStairs extends BlockStairs {


    public BlockPineStairs(Block block, int meta) {
        super(block, meta);
        this.setBlockName("pineStairs");
        this.setBlockTextureName(Reference.MODID + ":pineStairs");
        this.setCreativeTab(CreativeTabTM.truffleModTab);
        this.setHardness(1.6f);
        this.setResistance(0.3f);
        this.setLightOpacity(0);
    }


    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }


    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }


    @Override
    public int getRenderType()
    {
        return 10;
    }
}
