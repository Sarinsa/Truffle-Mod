package com.trufflemod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCheese extends TMBlock {

    public IIcon top;
    public IIcon inner;
    public IIcon side;
    public IIcon bottom;

    public BlockCheese() {
        super(Material.cake);
        this.setTickRandomly(true);
        this.setName("cheeseBlock");
        this.setHardness(0.6f);
        this.setResistance(0.4f);
        this.setStepSound(soundTypeStone);
    }


    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }


    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return !world.isAirBlock(x, y - 1, z);
    }


    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return this.canBlockStay(world, x, y, z);
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int meta, float F1, float F2, float F3) {
        this.eatBlock(world, x, y, z, entityPlayer);
        System.out.println(meta);
        return true;
    }



    private void eatBlock(World world, int x, int y, int z, EntityPlayer entityPlayer)
    {
        if (entityPlayer.canEat(false))
        {
            entityPlayer.getFoodStats().addStats(2, 0.1F);


            int meta = world.getBlockMetadata(x, y, z);

            if (meta >= 6) {
                world.setBlockToAir(x, y, z);
            }

            else
            {
                world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 1, 2);
            }
        }
    }



    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        float f = 0.0625F;
        float f1 = (float)(1 + meta * 2) / 16.0F;
        this.setBlockBounds(f1, 0.0F, f, 1.0F - f, 0.5f, 1.0F - f);
    }


    @Override
    public void setBlockBoundsForItemRender()
    {
        float f = 0.0625F;
        float f1 = 0.5F;
        this.setBlockBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {

        return side == 1 ? this.top : (side == 0 ? this.bottom : (meta > 0 && side == 4 ? this.inner : this.blockIcon));
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iIconRegister) {

        this.blockIcon = iIconRegister.registerIcon(this.getTextureName() + "_side");
        this.inner = iIconRegister.registerIcon(this.getTextureName() + "_inner");
        this.top = iIconRegister.registerIcon(this.getTextureName() + "_top");
        this.bottom = iIconRegister.registerIcon(this.getTextureName() + "_bottom");
    }



    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        if (meta > 0) {

            return null;

        } else {

            return Item.getItemFromBlock(this);
        }
    }
}
