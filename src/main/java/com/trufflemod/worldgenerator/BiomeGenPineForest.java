package com.trufflemod.worldgenerator;

import com.trufflemod.initialize.ModBlocks;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenTaiga1;

import java.util.Random;

public class BiomeGenPineForest extends BiomeGenBase {

    private static Random random = new Random();

    private TMPineTreeGen2 pineTreeConeGen = new TMPineTreeGen2();
    private TMPineTreeGen pineTreeGen = new TMPineTreeGen();
    private static final WorldGenTaiga1 spruceTreeGen = new WorldGenTaiga1();

    private static final WorldGenBlockBlob mossyBoulder = new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0);

    @SuppressWarnings("unchecked")
    public BiomeGenPineForest(int biomeID) {
        super(biomeID);

        this.biomeName = "Pine Forest";

        this.theBiomeDecorator.treesPerChunk = 6 - random.nextInt(2);
        this.topBlock = Blocks.grass;

        this.addDefaultFlowers();
        this.setColor(747999);
        this.setHeight(BiomeGenBase.height_MidPlains);

        this.theBiomeDecorator.grassPerChunk = 6;
        this.theBiomeDecorator.mushroomsPerChunk = 4;
        this.waterColorMultiplier = 6654003;
        this.enableSnow = false;
        this.theBiomeDecorator.sandGen = new WorldGenSand(Blocks.gravel, 9);
    }


    @Override
    public void addDefaultFlowers() {

        this.addFlower(ModBlocks.flowerGaliumV, 0, 3);
        this.addFlower(Blocks.yellow_flower, 0, 8);
        this.addFlower(Blocks.red_flower, 0, 12);
        this.addFlower(ModBlocks.blueberryBush, 1, 150);
    }



    private void generateBoulders(World world, Random random, int x, int z) {

        float randomNumber;
        int xPos;
        int zPos;

        randomNumber = random.nextInt(2) - random.nextFloat();

        for (int i = 0; i < randomNumber; ++i) {

            xPos = x + random.nextInt(16) + 8;
            zPos = z + random.nextInt(16) + 8;

            int yPos = world.getHeightValue(xPos, zPos);

            mossyBoulder.generate(world, random, xPos, yPos, zPos);
        }
    }


    @Override
    public void decorate(World world, Random random, int x, int z) {

        this.generateBoulders(world, random, x, z);

        super.decorate(world, random, x, z);
    }



    @Override
    public WorldGenAbstractTree func_150567_a(Random random) {

        return (WorldGenAbstractTree)(random.nextInt(10) == 0 ? spruceTreeGen : (WorldGenAbstractTree)(random.nextInt(8) == 1 ? pineTreeConeGen : pineTreeGen));
    }
}
