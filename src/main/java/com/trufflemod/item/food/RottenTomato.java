package com.trufflemod.item.food;

import com.trufflemod.item.TMItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RottenTomato extends TMItemFood {

    public RottenTomato(int healAmount, int saturation, boolean bool) {
        super(healAmount, saturation, bool);
        this.setUnlocalizedName("rottenTomato");
        this.setTextureName("rottenTomato");
    }

    public void onFoodEaten(ItemStack item, World world, EntityPlayer entityPlayer) {

        entityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, 300, 1));
    }
}
