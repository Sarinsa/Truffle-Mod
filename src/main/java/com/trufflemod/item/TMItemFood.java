package com.trufflemod.item;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TMItemFood extends ItemFood {


    public TMItemFood(int healAmount, float saturation, boolean isWolfsFavoriteMeat) {
        super(healAmount, saturation, isWolfsFavoriteMeat);
        this.setCreativeTab(CreativeTabTM.truffleModTab);
    }


    @Override
    public Item setTextureName(String textureName) {

        this.iconString = Reference.MODID + ":" + textureName;
        return this;
    }
}
