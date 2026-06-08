package org.spearedrice.asianmod.rendering.blockentity;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

public class AbacusBlockEntityRenderState extends BlockEntityRenderState {
    private int clicks = 0;

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }
}