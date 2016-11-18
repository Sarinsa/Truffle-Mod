package com.trufflemod.worldgenerator;

import com.trufflemod.initialize.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class TMPineTreeGen extends WorldGenAbstractTree {

    public TMPineTreeGen() {
        super(false);
    }


    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {

        Block block = world.getBlock(x, y - 1, z);

        if (block != Blocks.grass && block != Blocks.dirt && block != Blocks.farmland || y <= 6) {
            return false;


        }

        for (int height = 3; height < 12; height++) {

            for (int width = -3; width <= 3 ; width++) {

                for (int length = -3; length <= 3; length++) {


                    if (!isReplaceable(world, x + width, y + height, z + length)) {
                        return false;
                    }
                }
            }
        }



        int baseLength = 1 + random.nextInt(6);
        int branches = 1 + random.nextInt(3);

        int g = 0;

        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        if (baseLength < 3) {

            for (int i = 0; i < baseLength; i++) {

                setBlock(world, x, y + i, z, ModBlocks.pineLog);
                g++;
            }

            generateTop(world, x, y, z);
        }
        else if (baseLength >= 3)
        {

            for (int i = 0; i < baseLength; i++) {

                setBlock(world, x, y + i, z, ModBlocks.pineLog);
                g++;
            }


            int branchAmount = 1;
            for (int i = 0; i < branches; i++) {

                generateBranch(world, x, y + g, z, branchAmount);
                branchAmount++;
                g += 2;
            }

            generateTop(world, x, y + g, z);

            if (random.nextInt(3) == 0) {

                generateTop(world, x, y + g + 1, z);
            }
        }
        return true;
    }




    private void generateTop(World world, int x, int y, int z) {

        setBlock(world, x, y + 1, z, ModBlocks.pineLog);
        setBlock(world, x, y - 2, z, ModBlocks.pineLog);

        setBlock(world, x, y + 2, z, ModBlocks.pineLeaves);
        setBlock(world, x + 1, y + 1, z, ModBlocks.pineLeaves);
        setBlock(world, x - 1, y + 1, z, ModBlocks.pineLeaves);
        setBlock(world, x, y + 1, z - 1, ModBlocks.pineLeaves);
        setBlock(world, x, y + 1, z + 1, ModBlocks.pineLeaves);
    }


    private void generateBranch(World world, int x, int y, int z, int ammount) {

        for (int i = -1; i < 2; i++) {
            for (int j = -1; i < 2; i++) {

                setBlock(world, x + i, y, z + j, ModBlocks.pineLeaves);
                setBlock(world, x - i, y, z - j, ModBlocks.pineLeaves);
                setBlock(world, x + i, y, z, ModBlocks.pineLeaves);
                setBlock(world, x, y, z, ModBlocks.pineLog);
            }
        }

        setBlock(world, x, y + 1, z, ModBlocks.pineLog);
        setBlock(world, x, y + 2, z, ModBlocks.pineLog);
        setBlock(world, x + 1, y - 2, z, ModBlocks.pineLeaves);
        setBlock(world, x - 1, y - 2, z, ModBlocks.pineLeaves);
        setBlock(world, x, y - 2, z -1, ModBlocks.pineLeaves);
        setBlock(world, x, y - 2, z + 1, ModBlocks.pineLeaves);



        setBlock(world, x + 1, y - 2, z + 1, ModBlocks.pineLeaves);
        setBlock(world, x - 1, y - 2, z - 1, ModBlocks.pineLeaves);
        setBlock(world, x + 1, y - 2, z - 1, ModBlocks.pineLeaves);
        setBlock(world, x - 1, y - 2, z + 1, ModBlocks.pineLeaves);

        setBlock(world, x + 2, y - 2, z, ModBlocks.pineLeaves);
        setBlock(world, x + 2, y - 2, z - 1, ModBlocks.pineLeaves);
        setBlock(world, x + 2, y - 2, z + 1, ModBlocks.pineLeaves);
        setBlock(world, x - 2, y - 2, z, ModBlocks.pineLeaves);
        setBlock(world, x - 2, y - 2, z + 1, ModBlocks.pineLeaves);
        setBlock(world, x - 2, y - 2, z - 1, ModBlocks.pineLeaves);

        setBlock(world, x, y - 2, z + 2, ModBlocks.pineLeaves);
        setBlock(world, x - 1, y - 2, z + 2, ModBlocks.pineLeaves);
        setBlock(world, x + 1, y - 2, z + 2, ModBlocks.pineLeaves);
        setBlock(world, x, y - 2, z - 2, ModBlocks.pineLeaves);
        setBlock(world, x - 1, y - 2, z - 2, ModBlocks.pineLeaves);
        setBlock(world, x + 1, y - 2, z - 2, ModBlocks.pineLeaves);

        setBlock(world, x + 1, y - 1, z, ModBlocks.pineLeaves);
        setBlock(world, x - 1, y - 1, z, ModBlocks.pineLeaves);
        setBlock(world, x, y - 1, z -1, ModBlocks.pineLeaves);
        setBlock(world, x, y - 1, z + 1, ModBlocks.pineLeaves);



        setBlock(world, x + 1, y - 3, z, ModBlocks.pineLeaves);
        setBlock(world, x - 1, y - 3, z, ModBlocks.pineLeaves);
        setBlock(world, x, y - 3, z -1, ModBlocks.pineLeaves);
        setBlock(world, x, y - 3, z + 1, ModBlocks.pineLeaves);

    }


    private void setBlock(World world, int x, int y, int z, Block block) {

        if (isReplaceable(world, x, y, z)) {
            world.setBlock(x, y, z, block);
        }
    }


    @Override
    public boolean isReplaceable(World world, int x, int y, int z) {

        Block block = world.getBlock(x, y, z);
        return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z) || block == ModBlocks.pineSapling || block == Blocks.sapling || block == Blocks.vine;
    }
}
