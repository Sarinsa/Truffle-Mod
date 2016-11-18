package com.trufflemod.item;

import com.trufflemod.initialize.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemThistle extends TMItem {


    public ItemThistle() {
        this.setUnlocalizedName("itemThistle");
        this.setTextureName("itemThistle");
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int metadata, float F1, float F2, float F3) {

        if (world.getBlock(x, y, z) == Blocks.grass && world.getBlock(x, y + 2, z) == Blocks.air) {

            if (world.getBlock(x, y + 3, z) != Blocks.air) {

                System.out.println("Oh noe!");
            }

            System.out.println("yes");

            world.setBlock(x, y + 1, z, ModBlocks.flowerThistle);
            world.setBlock(x, y + 2, z, ModBlocks.flowerThistle, 1, 4);

            world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, ("dig.grass"), 0.8F, 0.85F);
            --itemStack.stackSize;

            return true;
        }
        return false;
    }
}
