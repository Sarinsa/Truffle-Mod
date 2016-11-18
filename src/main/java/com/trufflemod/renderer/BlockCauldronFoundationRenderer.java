package com.trufflemod.renderer;

import com.trufflemod.block.BlockCauldronFoundation;
import com.trufflemod.lib.Reference;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class BlockCauldronFoundationRenderer implements ISimpleBlockRenderingHandler {


    private Tessellator tessellator = Tessellator.instance;

    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/model/modelCauldronFoundation/cauldronFoundation.png");
    private static final ResourceLocation model = new ResourceLocation(Reference.MODID, "textures/model/modelCauldronFoundation/ModelCauldronFoundation.obj");

    private IModelCustom model_1 = AdvancedModelLoader.loadModel(model);



    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }


    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        GL11.glPushMatrix();

        GL11.glTranslatef((float)x + 0.5f, (float)y, (float)z + 0.5f);


        model_1.renderAll();

        return true;
    }


    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return modelId == BlockCauldronFoundation.renderID;
    }


    @Override
    public int getRenderId() {
        return BlockCauldronFoundation.renderID;
    }
}
