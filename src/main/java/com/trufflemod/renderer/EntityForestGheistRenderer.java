package com.trufflemod.renderer;

import com.trufflemod.entity.EntityForestGheist;
import com.trufflemod.lib.Reference;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class EntityForestGheistRenderer extends RenderLiving {

    private static final ResourceLocation ghastTextures = new ResourceLocation(Reference.MODID, "textures/entity/forestGheist/forestGheist.png");
    private static final ResourceLocation ghastShootingTextures = new ResourceLocation(Reference.MODID, "textures/entity/forestGheist/forestGheist_shooting.png");


    public EntityForestGheistRenderer() {
        super(new ModelGhast(), 7F);

    }

    private ResourceLocation getGheistTexture(EntityForestGheist forestGheist)
    {
        return forestGheist.func_110182_bF() ? ghastShootingTextures : ghastTextures;
    }


    private void preRenderCallback(EntityForestGheist entityForestGheist, float size) {

        float f1 = ((float)entityForestGheist.prevAttackCounter + (float)(entityForestGheist.attackCounter - entityForestGheist.prevAttackCounter) * size) / 20.0F;

        if (f1 < 0.0F) {

            f1 = 0.0F;
        }

        f1 = 1.0F / (f1 * f1 * f1 * f1 * f1 * 2.0F + 1.0F);
        float f2 = (8.0F + f1) / 2.0F;
        float f3 = (8.0F + 1.0F / f1) / 2.0F;

        GL11.glScalef(f3 * 2, f2 * 2, f3 * 2);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }



    protected void preRenderCallback(EntityLivingBase entityLivingBase, float size) {

        this.preRenderCallback((EntityForestGheist)entityLivingBase, size);
    }


    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {

        return this.getGheistTexture((EntityForestGheist)entity);
    }



    protected float getDeathMaxRotation(EntityLivingBase entityLivingBase) {

        return 1080;
    }
}
