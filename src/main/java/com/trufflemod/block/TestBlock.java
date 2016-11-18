package com.trufflemod.block;

import com.trufflemod.lib.Reference;
import com.trufflemod.worldgenerator.TMTusseTempleGen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;


public class TestBlock extends TMBlock {

    private IIcon front;
    private IIcon block;

    private Random random = new Random();

    public TestBlock() {
        super(Material.rock);
        this.setBlockName("testBlock");
        this.setBlockTextureName("testBlock");
    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        int i = MathHelper.floor_double((double)(entityLivingBase.rotationYaw * 4.0F / 360F) + 0.5D) & 3;


        if (i == 0) {

            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        else if (i == 1) {

            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        else if (i == 2) {

            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        else if (i == 3) {

            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
    }



    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {

        world.setBlockToAir(x, y, z);

        if (!world.isRemote) {

            new TMTusseTempleGen().generate(world, random, x, y, z);
            System.out.println("Generated");
        }

        return true;
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iIconRegister) {

        this.front = iIconRegister.registerIcon(this.getTextureName() + "_front");
        this.block = iIconRegister.registerIcon(Reference.MODID + ":pinePlanks");
    }



    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {

        return meta == 0 && side == 3 ? this.front : (side == meta ? this.front : this.block);
    }
}
