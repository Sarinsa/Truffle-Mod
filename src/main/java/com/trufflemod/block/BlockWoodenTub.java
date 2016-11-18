package com.trufflemod.block;

import com.trufflemod.initialize.ModItems;
import com.trufflemod.lib.Reference;
import com.trufflemod.tileentity.TileEntityWoodenTub;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;
import java.util.Random;

public class BlockWoodenTub extends TMBlockContainer {


    public BlockWoodenTub() {
        super(Material.wood);
        this.setTickRandomly(true);
        this.setBlockName("woodenTub");
        this.setBlockTextureName("pinePlanks");
        this.setStepSound(soundTypeWood);
        this.setHardness(1.6f);
        this.setResistance(0.8f);
    }


    @Override
    public boolean isOpaqueCube() {
        return false;
    }


    @Override
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
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
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
    {
        this.setBlockBounds(0f, 0f, 0f, 1f, 0.125f, 1f);
        super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);

        float f = 0.125F;

        this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);

        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);

        this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);

        this.setBlockBoundsForItemRender();
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void setBlockBoundsForItemRender()
    {
        float f = 0.0625F;
        float f1 = 0.825F;

        this.setBlockBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
    }



    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

        float f = (float) y + (6.0F + (float)(3 * world.getBlockMetadata(x, y, z))) / 16.0F;

        if (entity.isBurning() && world.getBlockMetadata(x, y, z) > 1 && entity.boundingBox.minY <= (double) f) {
            entity.extinguish();
        }
    }


    public TileEntity createNewTileEntity(World world, int name) {
        return new TileEntityWoodenTub();
    }


    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(x, z);

        int randomNum;

        if (!world.isRaining() && world.canBlockSeeTheSky(x, y, z)) {

            if (world.getBlockMetadata(x, y, z) > 1 && world.isDaytime()) {

                if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.COLD)) {

                    randomNum = random.nextInt(60);

                    if (randomNum == 0) {

                        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 1, 2);
                    }
                } else if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SAVANNA)) {

                    randomNum = random.nextInt(40);

                    if (randomNum == 0) {

                        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 1, 2);
                    }
                } else if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.HOT)) {

                    randomNum = random.nextInt(20);

                    if (randomNum == 0) {

                        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 1, 2);
                    }
                }
            }
        }
        else if (world.isRaining() && world.canBlockSeeTheSky(x, y, z)) {

            this.fillWithRain(world, x, y, z);
        }
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int meta, float F1, float F2, float F3) {

        ItemStack itemStack = entityPlayer.inventory.getCurrentItem();

        if (itemStack == null) {

            if (world.getBlockMetadata(x, y, z) == 1) {

                if (!world.isRemote) {
                    world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y + 1.5D, z + 0.5D, (new ItemStack(ModItems.salt))));
                }

                world.setBlockMetadataWithNotify(x, y, z, 0, 3);

            }
            return true;
        }

        if (world.getBlockMetadata(x, y, z) == 1) {

            if (!world.isRemote) {
                world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y + 1.5D, z + 0.5D, (new ItemStack(ModItems.salt))));
            }

            world.setBlockMetadataWithNotify(x, y, z, 0, 3);

            return true;
        }

        if (itemStack.getItem() == Items.water_bucket) {

            if (world.getBlockMetadata(x, y, z) == 0) {

                if (world.provider.dimensionId != -1) {

                    world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 4, 3);

                    if (!entityPlayer.capabilities.isCreativeMode) {
                        entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, new ItemStack(Items.bucket));
                        entityPlayer.inventory.markDirty();
                    }
                }
                else
                {
                    for (int i = 0; i < 5; i++) {

                        world.spawnParticle("smoke", x + 0.5, y + 0.5, z + 0.5, 0, 0, 0);
                    }
                    world.playSound(x, y, z, "random.fizz", 1F, 1F, false);
                }
            }
            return true;

        }
        return true;
    }
}


