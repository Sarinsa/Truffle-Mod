package com.trufflemod.item;

import com.trufflemod.TruffleMod;
import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.inventory.InventoryLittleSack;
import com.trufflemod.lib.Reference;
import com.trufflemod.worldgenerator.BiomeGenPineForest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;

public class ItemLittleSack extends TMItem {


    public ItemLittleSack() {
        this.setUnlocalizedName("itemLittleSack");
        this.setTextureName("itemLittleSack");
        this.setMaxStackSize(1);
    }


    @Override
    public boolean getShareTag() {
        return true;
    }



    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {

        int pos = 0;
        List inventory = entityPlayer.inventoryContainer.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == itemStack) {
                pos = i;
                break;
            }
        }

        if (itemStack.stackTagCompound == null) {

            itemStack.stackTagCompound = new NBTTagCompound();
        }

        if (!itemStack.stackTagCompound.hasKey("Items")) {

            NBTTagList tagList = new NBTTagList();

            for (int slot = 0; slot < 4; slot++) {

                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setByte("Slot", (byte)slot);
                tagList.appendTag(nbtTagCompound);
            }

            itemStack.stackTagCompound.setTag("Items", tagList);

        }

        entityPlayer.openGui(TruffleMod.instance, 1, world, pos, 0, 0);
        return itemStack;
    }
}