package com.trufflemod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryLittleSack implements IInventory {

    protected ItemStack[] inventory = new ItemStack[4];
    public Container inventoryPlayer;
    public int position;

    public InventoryLittleSack(Container inventoryPlayer, int position) {
        this.inventoryPlayer = inventoryPlayer;
        this.position = position;
        readFromNBT();
    }

    public ItemStack getItemStack() {
        return (ItemStack) inventoryPlayer.getInventory().get(position);
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
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

        if (inventory[slot] != null) {

            ItemStack itemStack = inventory[slot];
            inventory[slot] = null;
            markDirty();

            return itemStack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        inventory[slot] = itemStack;
        markDirty();
    }


    private void writeToNBT() {

        ItemStack itemStack = getItemStack();

        NBTTagList list = new NBTTagList();

        for (int slots = 0; slots < 4; slots++) {

            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setByte("Slot",(byte)slots);

            //System.out.println(inventory[slots]);
            //System.out.println(inventory[slots] == null);

            if (inventory[slots] != null) {

                tagCompound.setTag("Item", inventory[slots].writeToNBT(new NBTTagCompound()));
            }

            list.appendTag(tagCompound);
        }

        itemStack.getTagCompound().setTag("Items", list);
    }


    public void readFromNBT() {

        ItemStack itemStack = getItemStack();

        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

        NBTTagList list = nbtTagCompound.getTagList("Items", 10);


        inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < list.tagCount(); i++) {

            NBTTagCompound tagCompound1 = list.getCompoundTagAt(i);

            byte slots = tagCompound1.getByte("Slot");

            if (slots >= 0 && slots < inventory.length && tagCompound1.hasKey("Item")) {

                inventory[slots] = ItemStack.loadItemStackFromNBT(tagCompound1.getCompoundTag("Item"));
            }
        }
    }

    @Override
    public String getInventoryName() {
        return "inventoryLittleSack";
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
    public void markDirty() {
        writeToNBT();
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
        return true;
    }
}
