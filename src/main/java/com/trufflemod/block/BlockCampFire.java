package com.trufflemod.block;

import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.initialize.ModItems;
import com.trufflemod.tileentity.TileEntityCampFire;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCampFire extends TMBlockContainer {

    private IIcon cobble;
    private IIcon fire;

    public BlockCampFire() {
        super(Material.rock);
        this.setName("campFire");
        this.setHardness(0.3f);
        this.setResistance(0.1f);
        this.setBlockBounds(0.05f, 0.0f, 0.05f, 0.95f, 0.5f, 0.95f);
        this.setStepSound(soundTypeStone);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {

        return Item.getItemFromBlock(this);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {

        if (world.getBlock(x, y + 1, z) == ModBlocks.blockCauldronFoundation) {

            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else
        {
            this.setBlockBounds(0.05f, 0.0f, 0.05f, 0.95f, 0.5f, 0.95f);
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.getBlock(x, y - 1, z).isOpaqueCube() && !(world.getBlock(x, y, z) instanceof BlockLiquid && !world.isAirBlock(x, y- 1, z));
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return this.canBlockStay(world, x, y, z);
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
        return new TileEntityCampFire(world);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister icon) {
        cobble = icon.registerIcon("cobblestone");
        fire = icon.registerIcon("fire_layer_1");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        return ((TileEntityCampFire)world.getTileEntity(x, y, z)).isBurning() ? fire : cobble;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float F1, float F2, float F3) {

        TileEntityCampFire tileEntity = (TileEntityCampFire) world.getTileEntity(x, y, z);
        tileEntity.markDirty();

        return tileEntity.blockActivated(world, x, y, z, player, side, F1, F2, F3);
    }



    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        TileEntityCampFire tile = (TileEntityCampFire)tileEntity;

        if (tile.isBurning() && tile.getBurnTime() > 0) {

            if (random.nextInt(24) == 0) {

                world.playSound((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "fire.fire", 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
            }

            if (random.nextInt(10) <= 9) {

                world.spawnParticle("smoke", (double) x + 0.5, (double) y + random.nextDouble(), (double) z + 0.5, 0D, 0D, 0D);
                world.spawnParticle("smoke", (double) x + 0.5, (double) y + 0.80, (double) z + 0.5, 0D, 0D, 0D);
                world.spawnParticle("smoke", (double) x + 0.55, (double) y + random.nextDouble(), (double) z + 0.5, 0D, 0D, 0D);
                world.spawnParticle("smoke", (double) x + 0.45, (double) y + 0.82, (double) z + 0.56, 0D, 0D, 0D);
                world.spawnParticle("smoke", (double) x + 0.65, (double) y + random.nextDouble(), (double) z + 0.5, 0D, 0D, 0D);
                world.spawnParticle("largesmoke", (double) x + 0.5, (double) y + 0.5, (double) z + 0.5, 0D, 0D, 0D);

            }

            if (random.nextInt(5) == 1) {

                world.spawnParticle("largesmoke", (double) x + 0.5, (double) y + 0.5, (double) z + 0.5, 0D, 0D, 0D);
                world.spawnParticle("largesmoke", (double) x + 0.65, (double) y + 0.5, (double) z + 0.5, 0D, 0D, 0D);
                world.spawnParticle("smoke", (double) x + 0.5, (double) y + 0.75, (double) z + 0.5, 0D, 0D, 0D);
            }

            if (random.nextInt(10) <= 7) {

                world.spawnParticle("flame", (double) x + random.nextDouble(), (double) y + 0.35D, (double) z + 0.4, 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x + 0.5, (double) y + 0.45D, (double) z + random.nextDouble(), 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x + 0.3, (double) y + 0.50D, (double) z + 0.4, 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x + random.nextDouble(), (double) y + 0.35D, (double) z + 0.5, 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x + random.nextDouble(), (double) y + 0.50D, (double) z + random.nextDouble(), 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x + 0.3, (double) y + 0.50D, (double) z + 0.7, 0D, 0D, 0D);
            }

            if (random.nextInt(9) <= 8) {

                world.spawnParticle("flame", (double) x + random.nextDouble(), (double) y + random.nextDouble(), (double) z + 0.5, 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x + 0.6, (double) y + 0.40D, (double) z + 0.3, 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x + 0.7, (double) y + random.nextDouble(), (double) z + 0.6, 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x + 0.5, (double) y + 0.35D, (double) z + 0.75, 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x + random.nextDouble(), (double) y + random.nextDouble(), (double) z + 0.5, 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x + random.nextDouble(), (double) y + random.nextDouble(), (double) z + 0.5, 0D, 0D, 0D);

            }

            if (random.nextInt(20) == 1) {

                world.spawnParticle("lava", (double) x + 0.4, (double) y + 0.60D, (double) z + 0.5, 0D, 0D, 0D);
                world.spawnParticle("lava", (double) x + 0.6, (double) y + 0.40D, (double) z + 0.3, 0D, 0D, 0D);
            }

            if (random.nextInt(10) == 1) {

                world.spawnParticle("flame", (double) x + 0.8, (double) y + 0.30D, (double) z + 0.8, 0D, 0D, 0D);
                world.spawnParticle("flame", (double) x + 0.3, (double) y + 0.35D, (double) z + 0.55, 0D, 0D, 0D);
            }
        }
    }



    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        TileEntityCampFire tile = (TileEntityCampFire)tileEntity;
        if (tile.isBurning() && tile.getBurnTime() > 0) {

            entity.setFire(3);
        }
    }
}
