package com.trufflemod.item;

import com.trufflemod.utils.IGrindable;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockAquamarine extends ItemBlock implements IGrindable {

    private final static String[] subBlocks = new String[] {"raw", "poolished", "semiPoolished"};

    public ItemBlockAquamarine(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }


    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int itemstack = itemStack.getItemDamage();
        if (itemstack < 0 || itemstack >= subBlocks.length)
        {
            itemstack = 0;
        }

        return super.getUnlocalizedName() + "_" + subBlocks[itemstack];
    }


    public int getMetadata(int meta) {
        return meta;
    }


    @Override
    public ItemStack itemOut(ItemStack item) {
        return null;
    }
}
