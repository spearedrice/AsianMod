package org.spearedrice.asianmod.rendering.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class TuffScreen extends Screen {
    public Screen parent;
    public TuffScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.parent);
    }

    public TuffScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        Button buttonWidget = Button.builder(Component.literal("Hello World"), (btn) -> {
            this.minecraft.getToastManager().addToast(
                    SystemToast.multiline(this.minecraft, SystemToast.SystemToastId.NARRATOR_TOGGLE, Component.nullToEmpty("Hello World!"), Component.nullToEmpty("This is a toast."))
            );
        }).bounds(40, 40, 120, 20).build();

        this.addRenderableWidget(buttonWidget);

        TuffWidget customWidget = new TuffWidget(40, 80, 120, 20);
        this.addRenderableWidget(customWidget);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);

        graphics.drawString(this.font, "Tuff Button", 40, 40 - this.font.lineHeight - 10, 0xFFFFFFFF, true);
    }
}