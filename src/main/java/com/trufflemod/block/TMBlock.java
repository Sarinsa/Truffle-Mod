package com.trufflemod.block;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TMBlock extends Block {


    protected TMBlock(Material material) {
        super(material);
        this.setStepSound(soundTypeStone);
        this.setCreativeTab(CreativeTabTM.truffleModTab);
    }


    public void setName(String name) {

        this.setBlockTextureName(name);
        this.setBlockName(name);
    }


    @Override
    public Block setBlockTextureName(String textureName) {

        this.textureName = Reference.MODID + ":" + textureName;
        return this;
    }
}
