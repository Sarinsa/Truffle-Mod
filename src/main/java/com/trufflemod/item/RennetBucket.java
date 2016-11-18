package com.trufflemod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class RennetBucket extends TMItem {

    private final static String[] subNames = new String[] {"cold", "hot"};

    private IIcon[] texture;

    public RennetBucket() {
        this.setName("rennetBucket");
        this.setMaxStackSize(1);
    }


    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {

        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {

            if (itemStack.getItemDamage() == 1) {

                list.add("");
                list.add("A bucket of rennet enzymes.");
                list.add("Can be used to coagulate milk.");
            }
            else
            {
                list.add("");
                list.add("A bucket of meshed thistle");
                list.add("stamens mixed with water.");
                list.add("When heated up, this");
                list.add("can be used in cheese making.");
            }
        }
        else
        {
            list.add("");
            list.add(EnumChatFormatting.GRAY + "Press" + EnumChatFormatting.GREEN + " Left CTRL " + EnumChatFormatting.GRAY + "for");
            list.add(EnumChatFormatting.GRAY + "for additional information");
        }
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


    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        return texture[meta];
    }
}
