package com.trufflemod.item;

import com.trufflemod.entity.EntityJumperBomb;
import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemJumperBomb extends TMItem {

    public ItemJumperBomb() {
        this.setUnlocalizedName("jumperBomb");
        this.setTextureName("jumperBomb");
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {

        if (!entityPlayer.capabilities.isCreativeMode) {
            --itemStack.stackSize;
        }

        world.playSoundAtEntity(entityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {

            world.spawnEntityInWorld(new EntityJumperBomb(world, entityPlayer));
        }

        return itemStack;
    }
}
