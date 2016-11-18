package com.trufflemod.client.gui;

import com.trufflemod.inventory.ContainerGrindStone;
import com.trufflemod.inventory.InventoryGrindStone;
import com.trufflemod.inventory.InventoryLittleSack;
import com.trufflemod.lib.Reference;
import com.trufflemod.tileentity.TileEntityGrindStone;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiGrindStone extends GuiContainer {

    private static final ResourceLocation grindStoneGui = new ResourceLocation(Reference.MODID, "textures/gui/guiGrindStone.png");


    public GuiGrindStone(InventoryPlayer inventoryPlayer, InventoryGrindStone inventory) {
        super(new ContainerGrindStone(inventoryPlayer, inventory));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRendererObj.drawString("Grindstone", 60, 8, 4210752);
        fontRendererObj.drawString("Inventory", 8, 73, 4210752);
    }



    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        mc.renderEngine.bindTexture(grindStoneGui);

        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}
