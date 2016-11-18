package com.trufflemod.item;

import com.trufflemod.entity.EntityPineCone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TestItem extends TMItem {

    public TestItem() {
        this.setUnlocalizedName("testItem");
        this.setTextureName("ninjaStar");
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        if (!player.capabilities.isCreativeMode) {

            --itemStack.stackSize;
        }

        world.playSoundAtEntity(player, "random.bow", 0.7F, 0.8F);

        if (!world.isRemote) {

            world.spawnEntityInWorld(new EntityPineCone(world, player));
        }

        return itemStack;
    }
}
