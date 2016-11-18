package com.trufflemod.renderer;

import com.trufflemod.lib.Reference;
import com.trufflemod.tileentity.TileEntityGrindStone;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class BlockGrindStoneRenderer extends TMTileEntitySpecialRenderer {


    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/model/modelGrindStone/grindStone.png");
    private static final ResourceLocation grindStoneModel = new ResourceLocation(Reference.MODID, "textures/model/modelGrindStone/ModelGrindStone.obj");

    public IModelCustom model = AdvancedModelLoader.loadModel(grindStoneModel);


    public BlockGrindStoneRenderer() {

    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick) {
        this.renderTileEntity((TileEntityGrindStone)tileEntity, x, y, z);
    }


    private void renderTileEntity(TileEntityGrindStone tileEntity, double x, double y, double z) {

        GL11.glPushMatrix();

        GL11.glTranslatef((float)x + 0.5f, (float)y, (float)z + 0.5f);


        int meta = tileEntity.getBlockMetadata();

            if (meta == 1) {
                GL11.glRotatef(180F, 0F, 10F, 0F);
                GL11.glTranslatef(-1F, 0F, 0F);
            }

            else if (meta == 2) {
                GL11.glTranslatef(-1F, 0F, 0F);
            }

            else if (meta == 3) {
                GL11.glRotatef(270F, 0F, 10F, 0F);
                GL11.glTranslatef(-1F, 0F, 0F);
            }

            else if (meta == 4) {

                GL11.glRotatef(90F, 0F, 10F, 0F);
                GL11.glTranslatef(-1F, 0F, 0F);
            }

        bindTexture(texture);
        model.renderAll();

        GL11.glPopMatrix();
    }


    public void renderBlockAsItem(TileEntity tileEntity, double x, double y, double z, float f) {

        GL11.glPushMatrix();

        GL11.glTranslatef((float)x + 0.5f, (float)y, (float)z + 0.5f);


        bindTexture(texture);
        model.renderAll();

        GL11.glPopMatrix();
    }
}
