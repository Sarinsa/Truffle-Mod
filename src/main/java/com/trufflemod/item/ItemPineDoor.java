package com.trufflemod.item;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemPineDoor extends ItemDoor {


    public ItemPineDoor() {
        super(Material.wood);
        this.setUnlocalizedName("itemPineDoor");
        this.setTextureName(Reference.MODID + ":itemPineDoor");
        this.setCreativeTab(CreativeTabTM.truffleModTab);
        this.setMaxStackSize(1);
    }


    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int meta, float F1, float F2, float F3) {

        if (meta != 1) {

            return false;
        }
        else
        {
            ++y;
            Block block = ModBlocks.pineDoor;


            if (entityPlayer.canPlayerEdit(x, y, z, meta, itemStack) && entityPlayer.canPlayerEdit(x, y + 1, z, meta, itemStack)) {

                if (!block.canPlaceBlockAt(world, x, y, z)) {

                    return false;
                }
                else
                {
                    int orientation = MathHelper.floor_double((double)((entityPlayer.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;

                    placeDoorBlock(world, x, y, z, orientation, block);
                    --itemStack.stackSize;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }
}
