package com.trufflemod.inventory;

import com.trufflemod.utils.IGrindable;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGrindStone extends Slot {

    private InventoryGrindStone inventoryGrindStone;

    public SlotGrindStone(InventoryGrindStone inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        inventoryGrindStone = inventory;
    }


    @Override
    public void onSlotChanged() {
        super.onSlotChanged();

        inventoryGrindStone.grindItem();
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {

        return itemStack.getItem() instanceof IGrindable;
    }
}
