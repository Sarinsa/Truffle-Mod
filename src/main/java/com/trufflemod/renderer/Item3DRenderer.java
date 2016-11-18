package com.trufflemod.renderer;

import com.trufflemod.block.BlockCampFire;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class Item3DRenderer implements IItemRenderer {


    private TMTileEntitySpecialRenderer specialRenderer;
    private TileEntity tile;


    public Item3DRenderer(TileEntity tileEntity, TMTileEntitySpecialRenderer renderer) {
        tile = tileEntity;
        specialRenderer = renderer;
    }


    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }


    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }


    @Override
    public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data) {


        //========== ENTITY ==========\\
        if (type == ItemRenderType.ENTITY) {

            GL11.glTranslatef(-0.5F, -0.4F, -0.5F);

            if (specialRenderer instanceof BlockCampFireRenderer) {
                GL11.glScalef(2.5F, 2.5F, 2.5F);
                GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
            }
            specialRenderer.renderBlockAsItem(tile, 0.0D, 0.0D, 0.0D, 0.0F);
        }


        //========== INVENTORY ==========\\
        else if (type == ItemRenderType.INVENTORY) {

            GL11.glTranslatef(-0.5F, -0.4F, -0.5F);

            if (specialRenderer instanceof BlockGrindStoneRenderer) {
                GL11.glScalef(0.75F, 0.75F, 0.75F);
                GL11.glTranslatef(-1.0F, -0.45F, -0.5F);
            }
            specialRenderer.renderBlockAsItem(tile, 0.0D, 0.0D, 0.0D, 0.0F);
        }



        //========== EQUIPPED ==========\\
        else if (type == ItemRenderType.EQUIPPED) {

            GL11.glTranslatef(0.0F, 0.0F, 0.0F);

            if (specialRenderer instanceof BlockGrindStoneRenderer) {

                GL11.glTranslatef(0.0F, -0.5F, 0.0F);
            }

            if (specialRenderer instanceof BlockCampFireRenderer) {

                GL11.glTranslatef(0.0F, 0.5F, 0.0F);
            }

            specialRenderer.renderBlockAsItem(tile, 0.0D, 0.0D, 0.0D, 0.0F);
        }



        //========== EQUIPPED_FIRST_PERSON ==========\\
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {

            GL11.glTranslatef(0.0F, 0.2F, 0.0F);

            if (specialRenderer instanceof BlockCampFireRenderer) {

                GL11.glTranslatef(0.0F, 0.35F, 0.0F);
            }

            specialRenderer.renderBlockAsItem(tile, 0.0D, 0.0D, 0.0D, 0.0F);
        }
    }
}
