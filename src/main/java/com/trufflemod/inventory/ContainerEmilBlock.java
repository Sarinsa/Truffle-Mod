package com.trufflemod.inventory;

import com.trufflemod.tileentity.TileEntityEmilBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEmilBlock extends Container {


    private static int numRows;
    private TileEntityEmilBlock tileEntityEmilBlock;

    public ContainerEmilBlock(InventoryPlayer inventoryPlayer, TileEntityEmilBlock tileEntity) {
        tileEntityEmilBlock = tileEntity;
        numRows = tileEntity.getSizeInventory();

        addSlotToContainer(new SlotEmilBlock(tileEntity, 0, 8, 58));
        addSlotToContainer(new SlotEmilBlock(tileEntity, 1, 29, 7));


        for (int x = 0; x < 3; ++x) {
            for (int z = 0; z < 9; ++z) {
                addSlotToContainer(new Slot(inventoryPlayer, z + x * 9 + 9, 8 + z * 18, 84 + x * 18));
            }
        }


        for (int x = 0; x < 9; ++x) {
            addSlotToContainer(new Slot(inventoryPlayer, x, 8 + x * 18, 142));
        }
    }



    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return tileEntityEmilBlock.isUseableByPlayer(entityPlayer);
    }


    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int fromSlot) {

        ItemStack itemStack = null;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack currentStack = slot.getStack();
            itemStack = currentStack.copy();

            // [...] Custom behaviour

            if (currentStack.stackSize == 0) {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (currentStack.stackSize == itemStack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(entityPlayer, currentStack);
        }
        return itemStack;
    }
}
