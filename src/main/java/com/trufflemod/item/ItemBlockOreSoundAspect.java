package com.trufflemod.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockOreSoundAspect extends ItemBlock {


    public ItemBlockOreSoundAspect(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }


    public int getMetadata(int meta)
    {
        return meta;
    }
}
