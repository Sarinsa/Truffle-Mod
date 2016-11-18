package com.trufflemod.block;

import com.trufflemod.initialize.ModItems;
import com.trufflemod.lib.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class BlockOlaf extends TMBlock {


    private Random random = new Random();


    public BlockOlaf() {
        super(Material.cake);
        this.setName("blockOlaf");
        this.setHardness(3F);
        this.setStepSound(soundTypeLadder);
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        ItemStack itemStack = player.getHeldItem();

        if (itemStack != null) {

            if (itemStack.getItem() == Item.getItemFromBlock(Blocks.dirt) && itemStack.getItemDamage() < 2) {

                --itemStack.stackSize;

                if (!world.isRemote) {

                    if (random.nextInt(20) == 4) {

                        world.spawnEntityInWorld(new EntityItem(world, x + random.nextFloat() - random.nextFloat(), y + 0.5, z + random.nextFloat() - random.nextFloat(), new ItemStack(Items.potato)));
                    }
                    else
                    {
                        world.spawnEntityInWorld(new EntityItem(world, x + random.nextFloat() - random.nextFloat(), y + 0.5, z + random.nextFloat() - random.nextFloat(), new ItemStack(itemStack.getItem(), 1, itemStack.getItemDamage() + 2)));
                    }
                }

                return true;
            }

            if (itemStack.getItem() == Items.diamond || itemStack.getItem() == Item.getItemFromBlock(Blocks.diamond_block)) {

                itemStack.stackSize = 0;
                world.playSoundEffect(x, y, z, (Reference.MODID + ":olaf_consume"), 0.5F, 1.1F);

                if (random.nextInt(50) == 0) {

                    world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ModItems.potionDouche)));
                }
            }
        }
        else
        {
            return false;
        }

        return true;
    }
}
