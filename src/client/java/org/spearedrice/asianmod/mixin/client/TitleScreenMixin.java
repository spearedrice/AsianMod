package org.spearedrice.asianmod.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;

import org.spearedrice.asianmod.rendering.LiveActionRolePlay;
import org.spearedrice.asianmod.rendering.screens.TuffScreen;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"), cancellable = false)
    private void addTestWidgets(CallbackInfo ci) {
        this.addRenderableWidget(Button.builder(Component.nullToEmpty("LARP"), (btn) -> this.minecraft.setScreen(new LiveActionRolePlay())).bounds(5, 5, 60, 20).build());
        this.addRenderableWidget(Button.builder(Component.nullToEmpty("TuffScreen"), (btn) -> this.minecraft.setScreen(new TuffScreen(Component.empty()))).bounds(5, 5+30, 60, 20).build());
    }
}