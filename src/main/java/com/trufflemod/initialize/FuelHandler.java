package com.trufflemod.initialize;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

public class FuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack itemStack) {

        if (itemStack.getItem() == ModItems.pineCone) {
            return 200;
        }
        else
        {
            return 0;
        }
    }
}
