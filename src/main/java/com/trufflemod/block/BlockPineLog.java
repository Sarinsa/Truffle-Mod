package com.trufflemod.block;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPineLog extends BlockLog {


    public IIcon side;
    public IIcon top;

    public BlockPineLog() {
        super();
        this.setBlockName("pineLog");
        this.setBlockTextureName(Reference.MODID + ":pineLog");
        this.setCreativeTab(CreativeTabTM.truffleModTab);
        this.setHardness(1.8f);
        this.setResistance(0.2f);
    }



    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return getFlammability(world, x, y, z, face) > 0;
    }


    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return 25;
    }



    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return Blocks.fire.getEncouragement(this);
    }


    @Override
    public void registerBlockIcons(IIconRegister icon) {
        side = icon.registerIcon(this.getTextureName() + "_side");
        top = icon.registerIcon(this.getTextureName() + "_top");
    }


    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int side) {
        return this.side;
    }


    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int top) {
        return this.top;
    }


    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float par6, float par7, float par8, int par9) {
        int j = par9 & 3;
        byte meta = 0;

        switch (side) {
            case 0:
            case 1:
                meta = 0;
                break;
            case 2:
            case 3:
                meta = 8;
                break;
            case 4:
            case 5:
                meta = 4;
        }

        return j | meta;
    }


    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }


    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }
}