package com.trufflemod.inventory;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGrindStoneOutput extends Slot {


    public SlotGrindStoneOutput(InventoryGrindStone inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }


    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return false;
    }
}
