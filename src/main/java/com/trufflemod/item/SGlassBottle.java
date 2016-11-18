package com.trufflemod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class SGlassBottle extends TMItem {

    private final static String[] subNames = new String[] {"tomatoSauce", "pestoSauce"};

    private IIcon[] texture;

    public SGlassBottle() {
        super();
        this.setUnlocalizedName("sGlassBottle");
        this.setTextureName("sGlass");
        this.setMaxStackSize(16);
        this.setHasSubtypes(true);
    }


    @Override
    public boolean isDamageable()
    {
        return false;
    }


    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        int itemstack = itemStack.getItemDamage();

        if (itemstack < 0 || itemstack >= subNames.length) {
            itemstack = 0;
        }

        return super.getUnlocalizedName() + "_" + subNames[itemstack];
    }


    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {

        texture = new IIcon[subNames.length];

        for (int i = 0; i  < subNames.length; i++) {
            texture[i] = iconRegister.registerIcon(this.getIconString() + "_" + subNames[i]);

        }
    }


    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta) {

        return texture[meta];
    }
}
