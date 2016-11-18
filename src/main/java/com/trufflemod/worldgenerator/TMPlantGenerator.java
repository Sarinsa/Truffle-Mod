package com.trufflemod.worldgenerator;

import com.trufflemod.initialize.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

public class TMPlantGenerator implements IWorldGenerator {


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        if (world.provider.dimensionId == 0) {

            generateWorld(random, chunkX * 16, chunkZ * 16, world);
        }
    }


    private void addPlant(Block spawnBlock, int metadata, Random random, World world, int posX, int posZ, int maxY, int minY, float spawnChance) {

        for (int i = 0; i < spawnChance; i++) {

            int defaultChunkSize = 16;

            int xPos = posX + random.nextInt(defaultChunkSize);
            int yPos = minY + random.nextInt(maxY - minY);
            int zPos = posZ + random.nextInt(defaultChunkSize);

            new TMPlantGen(spawnBlock, metadata).generate(world, random, xPos, yPos, zPos);
        }
    }

    private void addDoublePlant(Block spawnBlock, int metadata, Random random, World world, int posX, int posZ, int maxY, int minY, float spawnChance) {

        for (int i = 0; i < spawnChance; i++) {

            int defaultChunkSize = 16;

            int xPos = posX + random.nextInt(defaultChunkSize);
            int yPos = minY + random.nextInt(maxY - minY);
            int zPos = posZ + random.nextInt(defaultChunkSize);

            new TMDoublePlantGen(spawnBlock, metadata).generate(world, random, xPos, yPos, zPos);
        }
    }






    private void generateWorld(Random random, int chunkX, int chunkZ, World world) {
        BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);

        if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST)) {

            addPlant(ModBlocks.blueberryBush, 1, random, world, chunkX, chunkZ, 90, 55, 4f);
        }

        if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.PLAINS)) {

            addPlant(ModBlocks.flowerGaliumV, 0, random, world, chunkX, chunkZ, 80, 55, 5f);
        }

        if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.PLAINS)) {

            addDoublePlant(ModBlocks.flowerThistle, 1, random, world, chunkX, chunkZ, 80, 45, 2f);
        }
    }
}

