package com.trufflemod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerGrindStone extends Container {

    private InventoryGrindStone inventoryGrindStone;


    public ContainerGrindStone(InventoryPlayer inventoryPlayer, InventoryGrindStone inventory) {
        inventoryGrindStone = inventory;

        addSlotToContainer(new SlotGrindStone(inventory, 0, 52, 38));
        addSlotToContainer(new SlotGrindStoneOutput(inventory, 1, 107, 38));

        for (int x = 0; x < 3; ++x) {
            for (int z = 0; z < 9; ++z) {
                addSlotToContainer(new Slot(inventoryPlayer, z + x * 9 + 9, 8 + z * 18, 84 + x * 18));
            }
        }


        for (int x = 0; x < 9; ++x) {
            addSlotToContainer(new Slot(inventoryPlayer, x, 8 + x * 18, 142));
        }
    }

    //ContainerWorkbench

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return inventoryGrindStone.isUseableByPlayer(entityPlayer);
    }


    @Override
    public void onContainerClosed(EntityPlayer entityPlayer) {

        super.onContainerClosed(entityPlayer);

        ItemStack itemStack1 = inventoryGrindStone.getStackInSlotOnClosing(0);
        ItemStack itemStack2 = inventoryGrindStone.getStackInSlotOnClosing(1);

        if (itemStack1 != null) {

            entityPlayer.dropPlayerItemWithRandomChoice(itemStack1, false);
        }

        if (itemStack2 != null) {

            entityPlayer.dropPlayerItemWithRandomChoice(itemStack2, true);
        }
    }



    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int containerSlot) {

        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(containerSlot);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (containerSlot != 0) {
                return null;
            }

            if (slot.getStack() != null) {

                return itemstack;
            }
        }

        return itemstack;
    }
}