package com.trufflemod.item;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import net.minecraft.item.Item;

public class TMItem extends Item {

    public String name;

    public TMItem() {
        this.setCreativeTab(CreativeTabTM.truffleModTab);
    }

    public void setName(String name) {

        this.setTextureName(name);
        this.setUnlocalizedName(name);
    }


    @Override
    public Item setTextureName(String textureName) {

        this.iconString = Reference.MODID + ":" + textureName;
        return this;
    }
}
