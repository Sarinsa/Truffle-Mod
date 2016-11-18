package com.trufflemod.block;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Random;

public class PineLeaves extends BlockLeaves implements IShearable {

    private IIcon fancy;
    private IIcon fast;

    private int foliageColor = ColorizerFoliage.getFoliageColorPine();


    public PineLeaves() {
        super();
        this.setTickRandomly(true);
        this.setBlockName("pineLeaves");
        this.setBlockTextureName(Reference.MODID + ":pineLeaves");
        this.setCreativeTab(CreativeTabTM.truffleModTab);
        this.setHardness(0.3f);
        this.setResistance(0.1f);
        this.setLightOpacity(1);
        this.setStepSound(soundTypeGrass);
    }



    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return getFlammability(world, x, y, z, face) > 0;
    }



    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {

        super.updateTick(world, x, y, z, random);
    }



    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {

        return 180;
    }



    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {

        return Blocks.fire.getEncouragement(this);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public int getBlockColor()
    {
        return foliageColor;
    }



    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int level)
    {
        return foliageColor;
    }



    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {

        return true;
    }



    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        return foliageColor;
    }



    @Override
    public boolean isOpaqueCube() {
        return false;
    }



    @Override
    public int quantityDropped(Random random) {
        if (random.nextInt(15) == 0) {
            return 1;
        }
        return 0;
    }



    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {

        return Item.getItemFromBlock(ModBlocks.pineSapling);
    }



    @Override
    public boolean isShearable(ItemStack itemStack, IBlockAccess world, int x, int y, int z) {
        return true;
    }


    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> returnedBlock = new ArrayList<ItemStack>();

        returnedBlock.add(new ItemStack(Item.getItemFromBlock(ModBlocks.pineLeaves)));

        return returnedBlock;
    }


    @Override
    public boolean isLeaves(IBlockAccess world, int x, int y, int z) {

        return true;
    }


    @Override
    public void registerBlockIcons(IIconRegister icon) {
        fancy = icon.registerIcon(this.getTextureName() + "_fancy");
        fast = icon.registerIcon(this.getTextureName() + "_fast");
    }



    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (Minecraft.isFancyGraphicsEnabled()) {
            return fancy;
        }
        return fast;
    }


    @Override
    public String[] func_150125_e() {
        return new String[0];
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {

        super.randomDisplayTick(world, x, y, z, random);
    }
}