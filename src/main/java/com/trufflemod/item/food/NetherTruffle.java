package com.trufflemod.item.food;

import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.item.TMItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class NetherTruffle extends TMItemFood {


    public NetherTruffle(int healAmount, float saturation, Boolean bool) {
        super(healAmount, saturation, bool);
        this.setUnlocalizedName("netherTruffle");
        this.setTextureName("netherTruffle");
    }


    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int metadata, float F1, float F2, float F3) {

        if (world.getBlock(x, y, z) == Blocks.soul_sand) {

            world.setBlock(x, y, z, ModBlocks.netherTSoilCrop);
            world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, ("dig.sand"), 0.8F, 0.8F);
            --itemStack.stackSize;

            return true;
        }

        return false;
    }


    @Override
    public void onFoodEaten (ItemStack item, World world, EntityPlayer entityPlayer) {

        entityPlayer.addPotionEffect(new PotionEffect(Potion.blindness.id, 360, 1));
    }
}

