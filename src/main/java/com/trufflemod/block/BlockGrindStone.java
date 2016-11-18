package com.trufflemod.block;

import com.trufflemod.TruffleMod;
import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import com.trufflemod.tileentity.TileEntityGrindStone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGrindStone extends TMBlockContainer {



    public BlockGrindStone() {
        super(Material.wood);
        this.setBlockName("blockGrindStone");
        this.setBlockTextureName("pine_planks");
        this.setHardness(1.5F);
        this.setResistance(1.0F);
        this.setStepSound(soundTypeWood);
    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {

        int i = MathHelper.floor_double((double)(entityLivingBase.rotationYaw * 4.0F / 360F) + 0.5D) & 3;


        if (i == 0) {

            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }

        else if (i == 1) {

            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        else if (i == 2) {

            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        else if (i == 3) {

            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        TileEntityGrindStone tile = (TileEntityGrindStone) world.getTileEntity(x, y, z);
        tile.setCanvasBlock();
    }


    public int getMobilityFlag() {

        return 2;
    }


    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {

        EntityPlayer player = world.getClosestPlayer((double) x, (double) y, (double) z, -1);
        int i = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360F) + 0.5D) & 3;

        super.canPlaceBlockAt(world, x, y, z);

        if (i == 0) {
            return world.getBlock(x + 1, y, z).isReplaceable(world, x, y, z);
        }

        else if (i == 1){
            return world.getBlock(x, y, z + 1).isReplaceable(world, x, y, z);
        }

        else if (i == 2) {
            return world.getBlock(x - 1, y, z).isReplaceable(world, x, y, z);
        }

        else if (i == 3) {
            return world.getBlock(x, y, z - 1).isReplaceable(world, x, y, z);
        }
        return false;
    }



    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {

        if (this.hasTileEntity(meta) && meta != 0) {

            TileEntityGrindStone tileEntity = (TileEntityGrindStone) world.getTileEntity(x, y, z);

            tileEntity.removeCanvas();
            world.removeTileEntity(x, y, z);
        }
    }




    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return meta > 0 ? Item.getItemFromBlock(this) : null;
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
    public int getRenderType() {
        return -1;
    }


    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return meta > 0 ? new TileEntityGrindStone(world) : null;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int meta, float hitX, float hitY, float hitZ) {

        TileEntity tileEntity = world.getTileEntity(x, y, z);

        System.out.println(tileEntity);

        if (tileEntity == null || entityPlayer.isSneaking()) {
            return false;
        }

        entityPlayer.openGui(TruffleMod.instance, 0, world, x, y, z);
        return true;
    }
}



