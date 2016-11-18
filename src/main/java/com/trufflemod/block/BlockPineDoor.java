package com.trufflemod.block;

import com.trufflemod.initialize.ModItems;
import com.trufflemod.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockPineDoor extends BlockDoor {


    private IIcon upper[];
    private IIcon lower[];

    public BlockPineDoor() {
        super(Material.wood);
        this.setBlockName("pineDoor");
        this.setBlockTextureName(Reference.MODID +":pineDoor");
        this.setCreativeTab(null);
        this.setStepSound(soundTypeWood);
        this.setHardness(2.1F);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {

        return this.lower[0];
    }



    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int meta)
    {
        if (meta != 1 && meta != 0)
        {
            int i = func_150012_g(world, x, y, z);
            int j = i & 3;
            boolean flag = (i & 4) != 0;
            boolean flag1 = false;
            boolean flag2 = (i & 8) != 0;

            if (flag) {

                if (j == 0 && meta == 2) {

                    flag1 = !flag1;
                }

                else if (j == 1 && meta == 5) {

                    flag1 = !flag1;
                }

                else if (j == 2 && meta == 3) {

                    flag1 = !flag1;
                }

                else if (j == 3 && meta == 4) {

                    flag1 = !flag1;
                }
            }
            else
            {
                if (j == 0 && meta == 5) {

                    flag1 = !flag1;
                }

                else if (j == 1 && meta == 3) {

                    flag1 = !flag1;
                }

                else if (j == 2 && meta == 4) {

                    flag1 = !flag1;
                }

                else if (j == 3 && meta == 2) {

                    flag1 = !flag1;
                }

                if ((i & 16) != 0) {

                    flag1 = !flag1;
                }
            }

            return flag2 ? this.upper[flag1 ? 1 : 0] : this.lower[flag1 ? 1 : 0];
        }
        else
        {
            return this.lower[0];
        }
    }



    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

        upper = new IIcon[2];
        lower = new IIcon[2];
        upper[0] = iconRegister.registerIcon(this.getTextureName() + "_upper");
        lower[0] = iconRegister.registerIcon(this.getTextureName() + "_lower");
        upper[1] = new IconFlipped(upper[0], true, false);
        lower[1] = new IconFlipped(lower[0], true, false);
    }



    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {

        return (meta & 8) != 0 ? null : ModItems.itemPineDoor;
    }
}
