package com.trufflemod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLittleSack extends Container {

    private static int numRows;

    public ContainerLittleSack(InventoryPlayer inventoryPlayer, InventoryLittleSack inventoryLittleSack) {

        addSlotToContainer(new Slot(inventoryLittleSack, 0, 71, 36));
        addSlotToContainer(new Slot(inventoryLittleSack, 1, 71, 18));
        addSlotToContainer(new Slot(inventoryLittleSack, 2, 89, 36));
        addSlotToContainer(new Slot(inventoryLittleSack, 3, 89, 18));
        numRows = inventoryLittleSack.getSizeInventory();

        for (int x = 0; x < 3; ++x) {
            for (int z = 0; z < 9; ++z) {
                addSlotToContainer(new InvSlot(inventoryPlayer, z + x * 9 + 9, 8 + z * 18, 84 + x * 18, inventoryLittleSack));
            }
        }

        for (int x = 0; x < 9; ++x) {
            addSlotToContainer(new InvSlot(inventoryPlayer, x, 8 + x * 18, 142, inventoryLittleSack));
        }
    }


    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }


    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int fromSlot) {

        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (fromSlot < numRows)
            {
                if (!this.mergeItemStack(itemstack1, numRows * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, numRows * 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    private static class InvSlot extends Slot {

        InventoryLittleSack sack;

        public InvSlot(IInventory inventory, int slot, int x, int y, InventoryLittleSack inv) {
            super(inventory, slot, x, y);
            this.sack = inv;
        }

        /**
         * Return whether this slot's stack can be taken from this slot.
         */
        public boolean canTakeStack(EntityPlayer player) {

            return this.getStack() != sack.getItemStack();
        }

    }

}

