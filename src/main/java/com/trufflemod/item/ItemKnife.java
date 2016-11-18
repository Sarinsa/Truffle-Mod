package com.trufflemod.item;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.lib.Reference;
import com.trufflemod.utils.IGrindable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemKnife extends ItemSword implements IGrindable {

    public ItemKnife() {
        super(ToolMaterial.WOOD);
        this.setUnlocalizedName("knife");
        this.setTextureName(Reference.MODID + ":knife");
        this.setCreativeTab(CreativeTabTM.truffleModTab);
        this.setMaxDamage(245);
        this.setNoRepair();
        this.isDamageable();
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {

        itemStack.setItemDamage(itemStack.getItemDamage() + 1);
        return itemStack;
    }


    @Override
    public boolean canHarvestBlock(Block block, ItemStack itemStack) {

        return block == ModBlocks.pineCone;
    }


    @Override
    public boolean isDamageable()
    {
        return false;
    }


    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings({"unchecked"})
    public void addInformation (ItemStack itemStack, EntityPlayer player, List list, boolean bool) {

        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            list.add("");
            list.add(EnumChatFormatting.GRAY + "A sharp iron knife!");
            list.add(EnumChatFormatting.GRAY + "Can be used as a weapon or to");
            list.add(EnumChatFormatting.GRAY + "cut vegetables and meat. Neat?");
        }
        else
        {
            list.add("");
            list.add(EnumChatFormatting.GRAY + "Press" + EnumChatFormatting.GREEN + " Left CTRL " + EnumChatFormatting.GRAY + "for");
            list.add(EnumChatFormatting.GRAY + "for additional information");
        }
    }




    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
    {
        return false;
    }




    @Override
    public boolean hasContainerItem(ItemStack itemStack)
    {
        return itemStack.getItemDamage() != this.getMaxDamage();
    }


    @Override
    public ItemStack itemOut(ItemStack item) {
        return null;
    }
}
