package com.trufflemod.renderer;

import com.trufflemod.lib.Reference;
import com.trufflemod.tileentity.TileEntityCampFire;
import com.trufflemod.tileentity.TileEntityGrindStone;
import net.minecraft.block.BlockFire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

import static net.minecraftforge.common.util.ForgeDirection.*;
import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;

public class BlockCampFireRenderer extends TMTileEntitySpecialRenderer {

    private Tessellator tessellator = Tessellator.instance;

    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/model/modelCampFire/campFire.png");
    private static final ResourceLocation campFireModel = new ResourceLocation(Reference.MODID, "textures/model/modelCampFire/ModelCampFire.obj");

    private IModelCustom model_1 = AdvancedModelLoader.loadModel(campFireModel);




    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick) {

        GL11.glPushMatrix();

        GL11.glTranslatef((float)x + 0.5f, (float)y, (float)z + 0.5f);

        bindTexture(texture);

        model_1.renderAll();


        if (((TileEntityCampFire)tileEntity).isBurning()) {

            RenderBlocks.getInstance().renderBlockFire(Blocks.fire,(int) x,(int) y,(int) z);
        }

        GL11.glPopMatrix();
    }



    public void renderBlockAsItem(TileEntity tileEntity, double x, double y, double z, float F1) {


        GL11.glPushMatrix();

        GL11.glTranslatef((float)x + 0.5f, (float)y, (float)z + 0.5f);

        bindTexture(texture);
        model_1.renderAll();

        GL11.glPopMatrix();
    }
}
