package com.trufflemod.inventory;

import com.trufflemod.initialize.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEmilBlock extends Slot {


    public SlotEmilBlock(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }


    @Override
    public boolean isItemValid(ItemStack itemStack) {

        return this.getSlotIndex() == 0 ? itemStack.getItem() == ModItems.truffle : (this.getSlotIndex() == 1 && (itemStack.getItem() == ModItems.goldenTruffle && itemStack.getItemDamage() == 1));
    }


    @Override
    public int getSlotStackLimit() {
        return getSlotIndex() == 1 ? 1 : 64;
    }
}
