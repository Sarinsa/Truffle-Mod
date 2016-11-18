package com.trufflemod.initialize;

import com.trufflemod.worldgenerator.BiomeGenPineForest;
import com.trufflemod.worldgenerator.TMOreGenerator;
import com.trufflemod.worldgenerator.TMPlantGenerator;
import com.trufflemod.worldgenerator.TMTempleGen;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class WorldGeneratorHandler {


    public static void registerWorldGens() {

        GameRegistry.registerWorldGenerator(new TMPlantGenerator(), 0);
        GameRegistry.registerWorldGenerator(new TMOreGenerator(), 0);
        GameRegistry.registerWorldGenerator(new TMTempleGen(), 0);

        BiomeGenBase biomePineForest = new BiomeGenPineForest(120);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomePineForest, 10));
    }
}
