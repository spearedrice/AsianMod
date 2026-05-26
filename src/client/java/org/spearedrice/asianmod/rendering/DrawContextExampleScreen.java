package org.spearedrice.asianmod.rendering;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import org.spearedrice.asianmod.AsianMod;

public class DrawContextExampleScreen extends Screen {
    public DrawContextExampleScreen() {
        super(Component.empty());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);

        int rectangleX = 10;
        int rectangleY = 10;
        int rectangleWidth = 100;
        int rectangleHeight = 50;
        graphics.fill(rectangleX, rectangleY, rectangleX + rectangleWidth, rectangleY + rectangleHeight, 0xFF0000FF);

        graphics.renderOutline(rectangleX, rectangleY, rectangleWidth, rectangleHeight, 0xFFFF0000);

        graphics.vLine(rectangleX + rectangleWidth / 2, rectangleY, rectangleY + rectangleHeight, 0xFF00FF00);

        int scissorRegionX = 200;
        int scissorRegionY = 20;
        int scissorRegionWidth = 100;

        int scissorRegionHeight = this.height - 40;

        graphics.enableScissor(scissorRegionX, scissorRegionY, scissorRegionX + scissorRegionWidth, scissorRegionY + scissorRegionHeight);

        graphics.fillGradient(0, 0, this.width, this.height, 0xFFFF0000, 0xFF0000FF);

        graphics.disableScissor();

        Identifier texture = Identifier.fromNamespaceAndPath("minecraft", "textures/block/deepslate.png");
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, 90, 90, 0, 0, 16, 16, 16, 16);

        Identifier texture2 = Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "textures/gui/test-uv-drawing.png");
        int u = 10, v = 13, regionWidth = 14, regionHeight = 14;
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture2, 90, 190, u, v, 14, 14, regionWidth, regionHeight, 256, 256);

        graphics.drawString(minecraft.font, "Hello, world!", 10, 200, 0xFFFFFFFF, false);
    }
}