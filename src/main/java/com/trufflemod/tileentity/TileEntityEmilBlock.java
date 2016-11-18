package com.trufflemod.tileentity;

import com.trufflemod.initialize.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

public class TileEntityEmilBlock extends TileEntity implements IInventory {

    public boolean hasUpgradeStack;
    public ItemStack upgradeType;

    public static int maxLiquid = 50;
    public int fuel;
    public int oil;

    private ItemStack[] inventorySlots = new ItemStack[2];


    public TileEntityEmilBlock() {

        markDirty();
    }


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



    public boolean hasFuel() {

        return this.fuel > 0;
    }


    public boolean hasUpgrade() {

        return upgradeType != null;
    }


    public boolean hasOil() {

        return this.oil > 0;
    }


    public int getFuel() {

        return this.fuel;
    }

    public int getOil() {

        return this.oil;
    }


    public int getLiquidScaled(int liquid, int value) {

        return (liquid * value) / maxLiquid;
    }


    public void markDirty() {

        super.markDirty();
    }


    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        inventorySlots[slot] = itemStack;
    }


    @Override
    public String getInventoryName() {
        return "inventoryEmilBlock";
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
    public void readFromNBT(NBTTagCompound tagCompound) {

        super.readFromNBT(tagCompound);

        this.fuel = tagCompound.getInteger("Fuel");
        this.hasUpgradeStack = tagCompound.getBoolean("HasUpgradeStack");
        this.oil = tagCompound.getInteger("Oil");

        if (tagCompound.hasKey("HasUpgradeStack") && tagCompound.getBoolean("HasUpgradeStack")) {

            this.setInventorySlotContents(1, new ItemStack(ModItems.goldenTruffle, 1, 1));
        }

        System.out.println(tagCompound.getInteger("Fuel"));
    }


    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {

        super.writeToNBT(tagCompound);

        tagCompound.setBoolean("HasUpgradeStack", this.hasUpgradeStack);
        tagCompound.setInteger("Oil", this.oil);
        tagCompound.setInteger("Fuel", this.fuel);
    }



    @Override
    public void openInventory() {

    }


    @Override
    public void closeInventory() {

    }


    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {

        if (slot == 0 && itemStack.getItem() == ModItems.truffle) {

            return true;
        }

        else if (slot == 1 && itemStack.getItem() == ModItems.goldenTruffle && itemStack.getItemDamage() == 1) {

            return true;
        }

        return false;
    }


    @Override
    public void updateEntity() {

        super.updateEntity();

        ItemStack itemStack = this.getStackInSlot(0);
        int currentFuel = this.getFuel();

        if (itemStack != null && itemStack.getItem() == ModItems.truffle) {

            if (currentFuel != maxLiquid) {

                --itemStack.stackSize;
                ++this.fuel;

                if (itemStack.stackSize == 0 || itemStack.stackSize < 1) {

                    setInventorySlotContents(0, null);
                }
            }
        }
    }
}
