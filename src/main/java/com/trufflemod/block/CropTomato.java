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

public class CropTomato extends BlockCrops {

    private Random randomRet = new Random();

    public CropTomato() {
        super();
        this.setBlockName("tomatoCrop");
        this.setBlockTextureName(Reference.MODID + ":tomatoCrop");
    }


    @Override
    public int getRenderType() {
        return 1;
    }



    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float F1, int par7) {

        super.dropBlockAsItemWithChance(world, x, y, z, meta, F1, par7);
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float F1, float F2, float F3) {

        if (world.getBlockMetadata(x, y, z) == 7) {

            if (!world.isRemote) {

                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ModItems.tomato, 1 + randomRet.nextInt(3))));
            }

            world.setBlockMetadataWithNotify(x, y, z, 0, 3);
            return true;
        }
        return false;
    }




    protected Item func_149866_i() {

        return ModItems.tomatoSeeds;
    }



    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList <ItemStack> ret = new ArrayList <ItemStack>();

        if (metadata == 7) {
            ret.add(new ItemStack(ModItems.tomato));

            if (randomRet.nextInt(2) == 0) {
                ret.add(new ItemStack(ModItems.tomato, 2));
            }
            else if (randomRet.nextInt(2) == 1) {
                ret.add(new ItemStack(ModItems.tomato, 1));
            }
            else if (randomRet.nextInt(2) == 2) {
                ret.add(new ItemStack(ModItems.tomato, 3));
            }

        } else if (metadata < 7) {

            ret.add(new ItemStack(ModItems.tomatoSeeds));
        }

        return ret;
    }
}
