package com.trufflemod.item;

import com.trufflemod.entity.EntityGrenade;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

public class UnstableHappiness extends TMItem {

    public UnstableHappiness() {
        super();
        this.setUnlocalizedName("unstableHappiness");
        this.setTextureName("unstableHappiness");
        this.setHasSubtypes(true);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {


        if (!entityPlayer.capabilities.isCreativeMode) {
            --itemStack.stackSize;
        }

        world.playSoundAtEntity(entityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {

            world.spawnEntityInWorld(new EntityGrenade(world, entityPlayer));
        }
        return itemStack;
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) {

        if (itemStack.getItemDamage() == 1) {

            list.add(EnumChatFormatting.GRAY + "Note: this thing is quite heavy..");
        }
    }


    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName() + "_" + itemStack.getItemDamage();
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int pass) {

        return itemStack.getItemDamage() == 1;
    }
}

