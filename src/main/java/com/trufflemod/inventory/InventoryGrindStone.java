package com.trufflemod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;

public class InventoryGrindStone implements IInventory {


    private ItemStack[] inventorySlots = new ItemStack[3];


    @Override
    public int getSizeInventory() {
        return inventorySlots.length;
    }



    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventorySlots[slot];
    }



    @Override
    public ItemStack decrStackSize(int index, int count) {

        if (this.getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).stackSize <= count) {

                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, null);
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).stackSize <= 0) {

                    this.setInventorySlotContents(index, null);
                }
                else
                {
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }


    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

        if (inventorySlots[slot] != null) {

            ItemStack itemStack = inventorySlots[slot];
            inventorySlots[slot] = null;
            return itemStack;
        }
        else
        {
            return null;
        }
    }


    @Override
    public void markDirty() {

    }



    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {

        inventorySlots[slot] = itemStack;
    }


    @Override
    public String getInventoryName() {
        return "inventoryGrindStone";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {

        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }


    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return slot == 1;
    }


    public boolean canGrind() {

        if (inventorySlots[0] == null) {
            return false;
        }

        ItemStack itemStack = GrindStoneRecipes.getGrindedItems(inventorySlots[0]);


        if (itemStack == null) {

            return false;
        }


        if (!itemStack.isItemEqual(itemStack)) {

            return false;
        }


        if (inventorySlots[1] == null) {

            return true;
        }

        if (inventorySlots[1].getItem() == inventorySlots[0].getItem() && inventorySlots[1].stackSize < inventorySlots[1].getMaxStackSize()) {

            return true;
        }
        else
        {
            return inventorySlots[1].stackSize < itemStack.getMaxStackSize();
        }
    }



    public void grindItem() {

        if (this.canGrind()) {

            ItemStack itemStack = GrindStoneRecipes.getGrindedItems(inventorySlots[0]);

            if (inventorySlots[1] == null) {

                inventorySlots[1] = itemStack.copy();
            }

            else if (inventorySlots[1].isItemEqual(itemStack)) {

                inventorySlots[1].stackSize += itemStack.stackSize;
            }

            for (int slots = 0; slots < 1; slots++) {

                if (inventorySlots[slots].stackSize == 0) {

                    inventorySlots[slots] = new ItemStack(inventorySlots[slots].getItem().setFull3D());
                }
                else
                {
                    inventorySlots[slots].stackSize--;
                }

                if (inventorySlots[slots].stackSize == 0) {

                    inventorySlots[slots] = null;
                }
            }
        }
    }
}
