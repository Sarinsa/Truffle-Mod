package com.trufflemod.block;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPinePlanks extends TMBlock {

    public BlockPinePlanks() {
        super(Material.wood);
        this.setName("pinePlanks");
        this.setCreativeTab(CreativeTabTM.truffleModTab);
        this.setHardness(2.1f);
        this.setResistance(0.3f);
        this.setStepSound(soundTypeWood);
        this.setHarvestLevel("axe", 0);
    }


    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return getFlammability(world, x, y, z, face) > 0;
    }


    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return 35;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return Blocks.fire.getEncouragement(this);
    }
}
