package com.trufflemod.client.gui;

import com.trufflemod.inventory.ContainerLittleSack;
import com.trufflemod.inventory.InventoryLittleSack;
import com.trufflemod.lib.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiLittleSack extends GuiContainer {

    private static final ResourceLocation littleSackGui = new ResourceLocation(Reference.MODID, "textures/gui/guiLittleSack.png");
    private InventoryLittleSack sack;


    public GuiLittleSack(InventoryPlayer inventoryPlayer, InventoryLittleSack inventoryLittleSack) {
        super(new ContainerLittleSack(inventoryPlayer, inventoryLittleSack));
        this.sack = inventoryLittleSack;
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {

        ItemStack itemStack = sack.getItemStack();

        if (itemStack.hasDisplayName()) {

            fontRendererObj.drawString(itemStack.getDisplayName(), 62, 7, 4210752);
        }
        else {
            fontRendererObj.drawString("Little Sack", 62, 7, 4210752);
        }

        fontRendererObj.drawString("Inventory", 8, 73, 4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        mc.renderEngine.bindTexture(littleSackGui);

        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}
