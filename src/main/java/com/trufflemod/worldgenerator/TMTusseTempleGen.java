package com.trufflemod.worldgenerator;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;

import java.util.Random;

public class TMTusseTempleGen extends WorldGenerator {


    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {

        int width = 50;

        for (int i = -(width)/2; i < width/2; i++) {
            for (int j = i; j < width/2; j++) {

                if (!(world.getBlock(i, y - 1, j) == Blocks.sand) || !(world.getBlock(x, y, z) == Blocks.sandstone)) {

                    System.out.println("Noe!");
                    return false;
                }
                else System.out.println("Yesh!");
            }
        }

        int large = width;
        int small = width/4;

        this.buildPyramid(world, x, y, z, width);

        x = x + (large/2) - (small/2);
        z = z + (large/2) - (small/2);

        this.buildPyramid(world, x, y, z, small);

        return true;
    }


    private void buildHollowSquare(int xMin, int zMin, int xMax, int zMax, World world, int y) {

        //places walls
        for (int ix = xMin; ix < xMax; ix++) {

            world.setBlock(ix, y, zMin, Blocks.sandstone, 2, 3);
            world.setBlock(ix, y, zMax - 1, Blocks.sandstone, 2, 3);
        }

        for (int iz = zMin + 1; iz < zMax - 1; iz++) {

            world.setBlock(xMin, y, iz, Blocks.sandstone, 2, 3);
            world.setBlock(xMax - 1, y, iz, Blocks.sandstone, 2, 3);
        }

        for (int ix = xMin + 1; ix < xMax - 1; ix++) {
            for (int iz = zMin + 1; iz < zMax - 1; iz++) {

                world.setBlock(ix, y, iz, Blocks.air, 0, 2);
            }
        }
    }


    private void buildSquare(int xMin, int zMin, int xMax, int zMax, World world, int y) {

        for (int ix = xMin; ix < xMax; ix++) {
            for (int iz = zMin; iz < zMax; iz++) {

                Block block = Blocks.sandstone;
                Random random = new Random();

                if (random.nextInt(6) == 0) {

                    block = Blocks.sand;
                }

                world.setBlock(ix, y, iz, block, 2, 3);
            }
        }
    }


    public void buildPyramid(World world, int x, int y, int z, int width) {

        int height = (width + 1) / 2; //will round down the number


        for (int i = height - 1; i > 0; i--) {

            if (y + i < 255) {

                buildHollowSquare(x + i, z + i, x + width - i, z + width - i, world, y + i);
            }
        }
        if (y < 255) {

            buildSquare(x, z, x + width, z + width, world, y);
        }
    }
}
