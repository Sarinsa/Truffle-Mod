package com.trufflemod.item;

import com.trufflemod.tileentity.TileEntityEmilBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemBlockEmil extends ItemBlock {

    public ItemBlockEmil(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }


    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) {

        TileEntity tileEntity = entityPlayer.worldObj.getTileEntity((int)entityPlayer.posX, (int)entityPlayer.posY, (int)entityPlayer.posZ);

        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {

            list.add(((TileEntityEmilBlock)tileEntity).getFuel() + "/");
        }
    }

    public int getMetadata(int meta) {
        return meta;
    }
}
