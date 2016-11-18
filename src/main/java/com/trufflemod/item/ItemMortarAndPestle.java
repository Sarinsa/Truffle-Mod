package com.trufflemod.item;

import net.minecraft.item.ItemStack;


public class ItemMortarAndPestle extends TMItem {



    public ItemMortarAndPestle() {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName("mortar_and_pestle");
        this.setTextureName("mortar");
    }


    @Override
    public boolean hasContainerItem(ItemStack itemStack) {
        return true;
    }


    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
        return false;
    }
}
