package net.gamerdragon525.sf_plus.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.gamerdragon525.sf_plus.SimpleFireworksPlus;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MortarScreen extends AbstractContainerScreen<MortarMenu> {

    public static final ResourceLocation MORTAR_GUI =
            ResourceLocation.fromNamespaceAndPath(SimpleFireworksPlus.MODID, "textures/screens/mortar_block_gui.png");

    public MortarScreen(MortarMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, MORTAR_GUI);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(MORTAR_GUI, x, y, 0, 0, imageWidth, imageHeight, 176, 166);
    }
}
