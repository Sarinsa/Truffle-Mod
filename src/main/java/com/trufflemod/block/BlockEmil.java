package com.trufflemod.block;

import com.trufflemod.TruffleMod;
import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import com.trufflemod.tileentity.TileEntityEmilBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

import static net.minecraft.block.Block.soundTypeMetal;

public class BlockEmil extends TMBlockContainer {

    private IIcon tier1;
    private IIcon tier2;

    public static int renderID = 0;

    public BlockEmil() {
        super(Material.rock);
        this.setName("blockEmil");
        this.setHardness(200.2F);
        this.setResistance(20.0F);
        this.setLightLevel(1.0F);
        this.setLightOpacity(225);
        this.setStepSound(soundTypeMetal);
    }


    @Override
    public int getRenderType() {
        return renderID;
    }



    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float F1, float F2, float F3) {

        if (world.getBlockMetadata(x, y, z) > 0) {

            entityPlayer.openGui(TruffleMod.instance, 2, world, x, y, z);
        }

        world.playSoundEffect(x, y, z, (Reference.MODID + ":emilblock"), 0.5F, 0.6F);
        return true;
    }


    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {

        if (world.isBlockIndirectlyGettingPowered(x, y, z)) {

            if (!world.isRemote) {

                world.playSoundEffect(x, y, z, (Reference.MODID + ":emilblock"), 0.5F, 0.6F);
            }
        }
    }


    @Override
    public boolean isOpaqueCube() {
        return false;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 5));
    }


    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {

        tier1 = iconRegister.registerIcon(this.getTextureName());
        tier2 = iconRegister.registerIcon(this.getTextureName() + "_tier2");
    }



    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {

        return meta == 0 ? this.tier1 : (side == meta ? this.tier1 : this.tier2);
    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        int i = MathHelper.floor_double((double)(entityLivingBase.rotationYaw * 4.0F / 360F) + 0.5D) & 3;


        if (itemStack.getItemDamage() != 0) {

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
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {

        return meta > 0 ? new TileEntityEmilBlock() : null;
    }
}



