package com.trufflemod.renderer;

import com.trufflemod.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class BlockWoodenTubRenderer extends TMTileEntitySpecialRenderer {

    private Tessellator tessellator = Tessellator.instance;

    private static final ResourceLocation texture_empty = new ResourceLocation(Reference.MODID, "textures/model/modelWoodenTub/woodenTub_empty.png");
    private static final ResourceLocation woodenTubModel = new ResourceLocation(Reference.MODID, "textures/model/modelWoodenTub/ModelWoodenTub.obj");

    public IModelCustom model;

    public BlockWoodenTubRenderer() {
        model = AdvancedModelLoader.loadModel(woodenTubModel);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {

        int meta = tileEntity.getBlockMetadata();

        GL11.glPushMatrix();

        GL11.glTranslated(x + 0.5, y, z + 0.5);
        GL11.glRotated(0D, 0D, 0D, 0D);

        bindTexture(texture_empty);

        model.renderAll();

        switch (meta) {

            case 0:
                break;
            case 1:
                makeFlatSquare(0.15f, true);
                break;
            case 2:
                makeFlatSquare(0.35f, false);
                break;
            case 3:
                makeFlatSquare(0.6f, false);
                break;
            case 4:
                makeFlatSquare(0.85f, false);
                break;

            default:
        }

        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
    }


    /** Used to render the tub's surface */
    private void makeFlatSquare(float height, boolean bool) {

        ResourceLocation resourceLocation;

        if (bool) {

            resourceLocation = new ResourceLocation(Reference.MODID, "textures/model/modelWoodenTub/woodenTub_salt.png");

            tessellator.startDrawingQuads();

            Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);

            GL11.glTranslatef(-0.4f, 0.0f, -0.4f);
            GL11.glScalef(0.8f, 0.8f, 0.8f);

            tessellator.setNormal(0, 1, 0);
            tessellator.addVertexWithUV(1.0, height, 1.0, 0.0, 1.0);
            tessellator.addVertexWithUV(1.0, height, 0.0, 1.0, 1.0);
            tessellator.addVertexWithUV(0.0, height, 0.0, 1.0, 0.0);
            tessellator.addVertexWithUV(0.0, height, 1.0, 0.0, 0.0);

            tessellator.draw();
        }
        else
        {
            resourceLocation = new ResourceLocation("textures/blocks/water_still.png");

            tessellator.startDrawingQuads();

            Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);

            GL11.glTranslatef(-0.4f, 0.0f, -0.4f);
            GL11.glScalef(0.8f, 0.8f, 0.8f);

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            tessellator.setNormal(0, 1, 0);
            tessellator.addVertexWithUV(1.0, height, 1.0, 0.2, 0.03);
            tessellator.addVertexWithUV(1.0, height, 0.0, -0.6, 0.03);
            tessellator.addVertexWithUV(0.0, height, 0.0, -0.6, 0.0);
            tessellator.addVertexWithUV(0.0, height, 1.0, 0.2, 0.0);

            tessellator.draw();
        }
    }



    public void renderBlockAsItem(TileEntity tileEntity, double x, double y, double z, float f) {

        GL11.glPushMatrix();

        GL11.glTranslated(x + 0.5, y, z + 0.5);
        GL11.glRotated(0D, 0D, 0D, 0D);

        this.bindTexture(texture_empty);

        model.renderAll();

        GL11.glPopMatrix();
    }
}