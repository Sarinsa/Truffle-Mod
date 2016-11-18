package com.trufflemod.item;

import com.trufflemod.initialize.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class ItemHellishSpice extends TMItem {

    private Random rand = new Random(5);

    public ItemHellishSpice() {
        this.setUnlocalizedName("itemHellishSpice");
        this.setTextureName("itemHellishSpice");
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int metadata, float F1, float F2, float F3) {

        int random = rand.nextInt(6);


        if (world.getBlock(x, y, z) == ModBlocks.netherTSoilCrop) {

            if (random == 5 && world.getBlockMetadata(x, y, z) < 2) {

                world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 2, 3);
                world.spawnParticle("smoke", x + 0.3, y + 1, z + 0.2, 0.0D, 0.0D, 0.0D);
                world.spawnParticle("smoke", x - 0.2, y + 1, z + 0.3, 0.0D, 0.0D, 0.0D);

                if (random <= 2) {

                    world.spawnParticle("largesmoke", x - 0.2, y + 1, z + 0.3, 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("largesmoke", x + 0.3, y + 1, z + 0.2, 0.0D, 0.0D, 0.0D);
                }

                --itemStack.stackSize;
                return true;
            }


            else if (random <= 4 && world.getBlockMetadata(x, y, z) < 3) {

                world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 1, 3);
                world.spawnParticle("smoke", x + 0.3, y + 1, z + 0.2, 0.0D, 0.0D, 0.0D);
                world.spawnParticle("smoke", x - 0.2, y + 1, z + 0.3, 0.0D, 0.0D, 0.0D);

                if (random <= 2) {

                    world.spawnParticle("largesmoke", x - 0.2, y + 1, z + 0.3, 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("largesmoke", x + 0.3, y + 1, z + 0.2, 0.0D, 0.0D, 0.0D);
                }

                --itemStack.stackSize;
                return true;
            }

            else if (world.getBlockMetadata(x, y, z) == 2) {

                world.setBlock(x, y, z, ModBlocks.netherTSoil);
            }

        }
        return false;
    }

}
