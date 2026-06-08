package org.spearedrice.asianmod;

import net.minecraft.client.gui.screens.MenuScreens;

import net.fabricmc.api.ClientModInitializer;

import org.spearedrice.asianmod.recipe.AsianModRecipes;
import org.spearedrice.asianmod.rendering.screens.inventory.UpgradingScreen;

public class AsianModScreens implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MenuScreens.register(AsianModRecipes.UPGRADING_MENU_TYPE, UpgradingScreen::new);
	}
}