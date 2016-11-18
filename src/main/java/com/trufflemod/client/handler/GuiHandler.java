package com.trufflemod.client.handler;

import com.trufflemod.client.gui.GuiEmilBlock;
import com.trufflemod.client.gui.GuiGrindStone;
import com.trufflemod.client.gui.GuiLittleSack;
import com.trufflemod.inventory.*;
import com.trufflemod.tileentity.TileEntityEmilBlock;
import com.trufflemod.tileentity.TileEntityGrindStone;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {


    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == 1) {
            return new ContainerLittleSack(player.inventory, new InventoryLittleSack(player.inventoryContainer, x));
        }

        //ID 1 first, because you don't need tile entity if it's the bag.

        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (ID == 0) {
            return new ContainerGrindStone(player.inventory, new InventoryGrindStone());
        }

        if (ID == 2) {
            return new ContainerEmilBlock(player.inventory, (TileEntityEmilBlock) tileEntity);
        }

        return null;
    }


    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == 1) {
            return new GuiLittleSack(player.inventory, new InventoryLittleSack(player.inventoryContainer, x));
        }

        //ID 1 first, because you don't need tile entity if it's the bag.

        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (ID == 0) {
            return new GuiGrindStone(player.inventory, new InventoryGrindStone());
        }

        if (ID == 2) {
            return new GuiEmilBlock(player.inventory, (TileEntityEmilBlock) tileEntity);
        }

        return null;
    }
}
