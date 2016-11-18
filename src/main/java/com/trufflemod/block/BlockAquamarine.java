package com.trufflemod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.List;

public class BlockAquamarine extends TMBlock {


    private IIcon[] texture;

    private final static String[] subBlocks = new String[] {"raw", "semiPolished", "polished"};



    public BlockAquamarine() {
        super(Material.rock);
        this.setTickRandomly(true);
        this.setName("blockAquamarine");
        this.setHardness(4.0f);
        this.setResistance(3.5f);
        this.setHarvestLevel("pickaxe", 2);
        this.setStepSound(soundTypeGlass);
    }




    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < subBlocks.length; i++)

        list.add(new ItemStack(block, 1, i));
    }



    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        return true;
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister icon) {

        texture = new IIcon[subBlocks.length];

        for (int i = 0; i  < subBlocks.length; i++) {

            texture[i] = icon.registerIcon(this.getTextureName() + "_" + subBlocks[i]);
        }
    }



    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {

        return texture[meta];
    }




    @Override
    public int damageDropped (int metadata)
    {
        return metadata;
    }
}

