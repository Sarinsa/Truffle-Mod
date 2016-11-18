package com.trufflemod.worldgenerator;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class TMPlantGen extends WorldGenerator {

    public Block block;
    public int meta;

    public TMPlantGen(Block spawnBlock, int metadata) {
        block = spawnBlock;
        meta = metadata;
    }


    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        for (int spawnTries = 0; spawnTries < 8; spawnTries++) {

            int xPos = (x + random.nextInt(6)) - random.nextInt(6);
            int yPos = (y + random.nextInt(4)) - random.nextInt(4);
            int zPos = (z + random.nextInt(6)) - random.nextInt(6);

            if (world.isAirBlock(xPos, yPos, zPos) && block.canPlaceBlockAt(world, xPos, yPos, zPos) && world.canBlockSeeTheSky(x, y, z)) {
                world.setBlock(xPos, yPos, zPos, block, meta, 3);

                return true;
            }
        }
        return false;
    }
}
