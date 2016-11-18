package com.trufflemod.block.ore;

import com.trufflemod.block.TMBlock;
import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.initialize.ModItems;
import com.trufflemod.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class OreAquamarine extends TMBlock {

    private Random random = new Random();

    public OreAquamarine() {
        super(Material.rock);
        this.setName("oreAquamarine");
        this.setHardness(2.5f);
        this.setResistance(3.5f);
        this.setHarvestLevel("pickaxe", 2);
        this.setStepSound(soundTypeStone);
    }




    @Override
    public int getExpDrop(IBlockAccess iBlockAccess, int metadata, int fortune) {

        if (this.getItemDropped(metadata, random, fortune) != Item.getItemFromBlock(this)) {
            return 1 + random.nextInt(2);

        }
        return 0;
    }




    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        ret.add(new ItemStack(ModItems.aquamarineGem));

        if (fortune == 1 && random.nextInt(2) == 1) {

            ret.add(new ItemStack(ModItems.aquamarineGem));
        }

        else if(fortune == 2 && random.nextInt(3) <= 2) {

            ret.add(new ItemStack(ModItems.aquamarineGem));
        }

        else if (fortune > 2 && random.nextInt(4) <= 2) {

            if (random.nextInt(2) <= 1) {

                ret.add(new ItemStack(ModItems.aquamarineGem));
            }

            else if (random.nextInt(3) == 2) {

                ret.add(new ItemStack(ModItems.aquamarineGem, 2));
            }
        }

        return ret;
    }
}





