package org.spearedrice.asianmod.rendering.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class CustomWidget extends AbstractWidget {
    public CustomWidget(int x, int y, int width, int height) {
        super(x, y, width, height, Component.empty());
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        int startColor = 0xFF00FF00;
        int endColor = 0xFF0000FF;

        if (isHovered()) {
            startColor = 0xFFFF0000;
            endColor = 0xFF00FFFF;
        }

        graphics.fillGradient(getX(), getY(), getX() + this.width, getY() + this.height, startColor, endColor);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput builder) {
        return;
    }
}