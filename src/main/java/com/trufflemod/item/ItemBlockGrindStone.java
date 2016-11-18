package com.trufflemod.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockGrindStone extends ItemBlock {

    public ItemBlockGrindStone(Block block) {
        super(block);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
    }


    public int getMetadata(int meta) {
        return meta;
    }
}
