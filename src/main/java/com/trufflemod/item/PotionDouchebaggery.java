package com.trufflemod.item;

import com.trufflemod.entity.EntityPotionDouchebaggery;
import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PotionDouchebaggery extends TMItem {

    public PotionDouchebaggery() {
        this.setUnlocalizedName("potionDouchebaggery");
        this.setTextureName("potionDouchebaggery");
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {

        if (!entityPlayer.capabilities.isCreativeMode) {
            --itemStack.stackSize;
        }

        world.playSoundAtEntity(entityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {

            world.spawnEntityInWorld(new EntityPotionDouchebaggery(world, entityPlayer));
        }

        return itemStack;
    }


    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int pass) {

        return true;
    }
}
