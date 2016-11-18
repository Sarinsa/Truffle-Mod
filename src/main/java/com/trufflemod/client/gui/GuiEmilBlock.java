package com.trufflemod.client.gui;

import com.trufflemod.inventory.ContainerEmilBlock;
import com.trufflemod.lib.Reference;
import com.trufflemod.tileentity.TileEntityEmilBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuiEmilBlock extends GuiContainer {

    private static final ResourceLocation emilBlockGui = new ResourceLocation(Reference.MODID, "textures/gui/guiEmilBlock.png");
    private TileEntityEmilBlock tileEntityEmilBlock;


    public GuiEmilBlock(InventoryPlayer inventoryPlayer, TileEntityEmilBlock tileEntity) {
        super(new ContainerEmilBlock(inventoryPlayer, tileEntity));
        tileEntityEmilBlock = tileEntity;
    }


    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        if (par1 >= 8 + x && par2 >= 7 + y && par1 <= 23 + x && par2 <= 52 + y) {

            String[] fuelAmount = {tileEntityEmilBlock.getFuel() + "/" + "50 cl"};

            List list = Arrays.asList(fuelAmount);
            drawHoveringText(list, par1, par2, fontRendererObj);
        }
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRendererObj.drawString("Emil Artifact Interface", 34, -8, 4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int U, int V) {

        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(emilBlockGui);

        this.drawTexturedModalRect(x, y - 15, 0, 0, this.xSize, this.ySize + 15);


        if (tileEntityEmilBlock.hasFuel()) {

            int scale = tileEntityEmilBlock.getLiquidScaled(tileEntityEmilBlock.getFuel(), 46);

            this.drawTexturedModalRect(guiLeft + 8, guiTop + 7, 176, 31, 18, 46 - scale);
        }

        this.drawTexturedModalRect(guiLeft + 8, guiTop + 6, 176, 77, 18, 46);
    }
}
