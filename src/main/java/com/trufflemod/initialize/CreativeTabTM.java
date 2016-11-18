package com.trufflemod.initialize;

import com.trufflemod.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabTM {

    public static final CreativeTabs truffleModTab = new CreativeTabs(Reference.MODID.toLowerCase()) {


        @Override
        public Item getTabIconItem() {
            return ModItems.truffle;
        }
    };
}
