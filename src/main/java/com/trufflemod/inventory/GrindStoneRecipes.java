package com.trufflemod.inventory;

import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.initialize.ModItems;
import com.trufflemod.utils.IGrindable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GrindStoneRecipes {

    public static ItemStack getGrindedItems(ItemStack itemInn) {

        return getOutputItem(itemInn);
    }

    private static ItemStack getOutputItem(ItemStack itemInn) {

        if (itemInn.getItem() instanceof IGrindable) {

            if (itemInn.getItem() == ModItems.knife) {

                return new ItemStack(itemInn.getItem());
            }

            if (itemInn.getItem() == Item.getItemFromBlock(ModBlocks.blockAquamarine) && itemInn.getItemDamage() < 2) {

                return new ItemStack(ModBlocks.blockAquamarine, 1, itemInn.getItemDamage() + 1);
            }

            if (itemInn.getItem() == Item.getItemFromBlock(ModBlocks.blockSpinel) && itemInn.getItemDamage() < 2) {

                return new ItemStack(ModBlocks.blockSpinel, 1, itemInn.getItemDamage() + 1);
            }
        }
        return null;
    }
}
