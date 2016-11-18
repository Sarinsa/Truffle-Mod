package com.trufflemod.worldgenerator;

import com.trufflemod.initialize.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class TMDoublePlantGen extends WorldGenerator {


    private Block block = ModBlocks.flowerThistle;
    private int metadata;

    public TMDoublePlantGen(Block spawnBlock, int meta) {
        block = spawnBlock;
        metadata = meta;
    }


    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {

        for (int l = 0; l < 8; l++) {

            int xPos = (x + random.nextInt(9)) - random.nextInt(9);
            int yPos = (y + random.nextInt(5)) - random.nextInt(5);
            int zPos = (z + random.nextInt(9)) - random.nextInt(9);

            if (world.canBlockSeeTheSky(x, y, z) && world.isAirBlock(xPos, yPos, zPos) && block.canPlaceBlockAt(world, xPos, yPos, zPos))
            {
                world.setBlock(xPos, yPos, zPos, block);
                world.setBlock(xPos, yPos + 1, zPos, block, metadata, 3);
            }
        }
        return true;
    }
}
