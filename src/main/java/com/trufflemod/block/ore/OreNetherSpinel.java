package com.trufflemod.block.ore;

import com.trufflemod.block.TMBlock;
import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class OreNetherSpinel extends TMBlock {


    public OreNetherSpinel() {
        super(Material.rock);
        this.setName("netherOreSpinel");
        this.setHardness(3.0f);
        this.setResistance(4.0f);
        this.setHarvestLevel("pickaxe", 2);
        this.setStepSound(soundTypeStone);
    }
}




