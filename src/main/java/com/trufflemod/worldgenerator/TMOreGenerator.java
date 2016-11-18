package com.trufflemod.worldgenerator;

import com.trufflemod.initialize.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

public class TMOreGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case -1:
                generateNether(random, chunkX * 16, chunkZ * 16, world);
                break;
            case 0:
                generateWorld(random, chunkX * 16, chunkZ * 16, world);
                break;
        }
    }

    private void addOre(Block block, Block blockSpawn, Random random, World world, int posX, int posZ, int minY, int maxY, int minVein, int maxVein, float spawnChance) {

        for (int i = 0; i < spawnChance; i++) {

            int defaultChunkSize = 16;

            int xPos = posX + random.nextInt(defaultChunkSize);
            int yPos = minY + random.nextInt(maxY - minY);
            int zPos = posZ + random.nextInt(defaultChunkSize);

            new WorldGenMinable(block, (minVein + random.nextInt(maxVein - minVein)), blockSpawn).generate(world, random, xPos, yPos, zPos);
        }
    }


    private void generateWorld(Random random, int chunkX, int chunkZ, World world) {
        BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);

        //Biome-dependent generated ores

        if(BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST)) {
            addOre(ModBlocks.tSoil, Blocks.dirt, random, world, chunkX, chunkZ, 60, 95, 2, 5, 4.5f);
        }
        if(BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SANDY)) {
            addOre(ModBlocks.oreAquamarine, Blocks.stone, random, world, chunkX, chunkZ, 15, 46, 1, 2, 0.5f);
        }
        if(BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.HILLS)) {
            addOre(ModBlocks.oreAquamarine, Blocks.stone, random, world, chunkX, chunkZ, 15, 55, 1, 2, 1.5f);
        }
        if (biome.biomeName.equals("Pine Forest")) {

            addOre(ModBlocks.oreSoundAspect, Blocks.stone, random, world, chunkX, chunkZ, 13, 45, 1, 2, 5F);
        }

        //Non-dependent generated ores

        addOre(ModBlocks.saltOre, Blocks.stone, random, world, chunkX, chunkZ, 30, 110, 4, 8, 0.4f);
    }




    private void generateNether(Random random, int chunkX, int chunkZ, World world) {
        addOre(ModBlocks.netherOreSpinel, Blocks.netherrack, random, world, chunkX, chunkZ, 30, 85, 1, 4, 4.5f);
        addOre(ModBlocks.netherTSoil, Blocks.soul_sand, random, world, chunkX, chunkZ, 30, 80, 1, 5, 6.999f);
    }
}
