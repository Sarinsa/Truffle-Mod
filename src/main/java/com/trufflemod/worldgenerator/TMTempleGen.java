package com.trufflemod.worldgenerator;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

public class TMTempleGen implements IWorldGenerator {


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);

        if (world.provider.dimensionId == 0) {

            if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.DRY) && BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SANDY)) {

                this.generateInWorld(random, chunkX * 16, chunkZ * 16, world);
            }
        }
    }


    private void generateInWorld(Random random, int chunkX, int chunkZ, World world) {

        this.generateTemple(world, random, chunkX, chunkZ, 85, 65, 5);
    }



    private void generateTemple(World world, Random random, int posX, int posZ, int maxY, int minY, int spawnChance) {

        for (int i = 0; i < spawnChance; i++) {

            int defaultChunkSize = 16;

            int xPos = posX + random.nextInt(defaultChunkSize);
            int yPos = minY + random.nextInt(maxY - minY);
            int zPos = posZ + random.nextInt(defaultChunkSize);

            new TMTusseTempleGen().generate(world, random, xPos, yPos, zPos);
        }
    }
}
