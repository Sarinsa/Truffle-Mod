package com.trufflemod.block.ore;

import com.trufflemod.block.TMBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;
import java.util.Random;

public class OreSoundAspect extends TMBlock {

    private IIcon[] texture;

    private static final String[] names = new String[] {"treble", "bass"};


    public OreSoundAspect() {
        super(Material.rock);
        this.setName("oreSoundAspect");
        this.setHardness(3F);
        this.setResistance(4F);
        this.setStepSound(soundTypeStone);
        this.setLightLevel(0.6F);
        this.setHarvestLevel("pickaxe", 2);
    }


    @Override
    public Item getItemDropped(int fortune, Random random, int meta) {

        return Item.getItemFromBlock(this);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {

        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }



    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister icon) {

        texture = new IIcon[names.length];

        for (int i = 0; i < names.length; i++) {

            texture[i] = icon.registerIcon(this.getTextureName() + "_" + names[i]);
        }
    }



    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {

        return texture[meta];
    }
}
