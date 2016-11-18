package com.trufflemod.block;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.initialize.ModItems;
import com.trufflemod.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlueberryBush extends BlockBush {

    private Random random = new Random();

    private IIcon fruit;
    private IIcon nope;

    public BlueberryBush() {
        this.setBlockName("blueberryBush");
        this.setBlockTextureName(Reference.MODID + ":blueberryBush");
        this.setStepSound(soundTypeGrass);
        this.setHardness(0.1f);
        this.setCreativeTab(CreativeTabTM.truffleModTab);
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {

        if (world.getBlockMetadata(x, y, z) == 1) {

            world.setBlockMetadataWithNotify(x, y, z, 0, 3);

            if (!world.isRemote) {

                world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y + 0.2D, z + 0.2D, (new ItemStack(ModItems.blueberry, random.nextInt(3) + 1))));
            }

            return true;
        }
        return false;
    }


    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {

        list.add(new ItemStack(this, 1, 1));
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

        fruit = iconRegister.registerIcon(Reference.MODID + ":blueberryBush_fruit");
        nope = iconRegister.registerIcon(Reference.MODID + ":blueberryBush_0");
    }


    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {

        if (meta == 1) {
            return fruit;
        }
        else
        {
            return nope;
        }
    }


    @Override
    public int quantityDropped(Random random) {
        return 1 + random.nextInt(4);
    }



    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {

        if (metadata == 1) {

            return ModItems.blueberry;
        }

        return Item.getItemFromBlock(this);
    }




    @Override
    public boolean canPlaceBlockOn(Block block) {
        return block == Blocks.grass || block == Blocks.dirt;
    }


    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {

        return ((world.getBlock(x, y - 1, z) == Blocks.grass) || world.getBlock(x, y - 1, z) == Blocks.dirt);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {

        if (world.getBlockMetadata(x, y, z) == 0) {

            if (world.getBlockLightValue(x, y + 1, z) >= 8 && random.nextInt(20) == 2) {

                world.setBlockMetadataWithNotify(x, y, z, 1, 3);
            }
        }
    }
}

