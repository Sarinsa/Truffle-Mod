package com.trufflemod.block;

import com.trufflemod.initialize.ModItems;
import com.trufflemod.lib.Reference;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class CropArugula extends BlockCrops {

    private Random randomRet = new Random();

    public CropArugula() {
        this.setCreativeTab(null);
        this.setBlockName("arugulaCrop");
        this.setBlockTextureName(Reference.MODID + ":aGrass");
        this.setStepSound(soundTypeGrass);
    }


    protected Item func_149866_i() {

        return ModItems.arugulaSeeds;
    }



    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float F1, float F2, float F3) {

        if (world.getBlockMetadata(x, y, z) == 7) {

            if (!world.isRemote) {

                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ModItems.arugula, 1 + randomRet.nextInt(3))));
            }

            world.setBlockMetadataWithNotify(x, y, z, 0, 3);
            return true;
        }
        return false;
    }



    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList <ItemStack>();


        if (metadata == 7) {

            ret.add(new ItemStack(ModItems.arugula));
            ret.add(new ItemStack(ModItems.arugulaSeeds));

            if (randomRet.nextInt(3) == 1) {
                ret.add(new ItemStack( ModItems.arugula, 2));
            }

            else if (randomRet.nextInt(3) == 2) {
                ret.add(new ItemStack( ModItems.arugula, 1));
            }
        }
        else
        {
            ret.add(new ItemStack(ModItems.arugulaSeeds));
        }

        return ret;
    }
}


